package com.nauticana.purchase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.model.ViewTreeData;
import com.nauticana.basis.service.BasisService;
import com.nauticana.basis.utils.Utils;

@Repository
public class PurchaseJbdcRepository {

	private static final String purchaseGroups = 
			"SELECT MATERIAL_GROUP_ID, CAPTION, PARENT_ID FROM MATERIAL_GROUP WHERE PURPOSE = 'PURCHASE'";
	
	private static final String rfpProposalSql =
			"SELECT * FROM V_RFP_ITEM_PROPOSAL WHERE RFP_ID = ? ORDER BY PROPOSAL_ID, LINE";
			
	private static final String rfpPublishSql =
			"INSERT INTO RFP_PUBLISHMENT (RFP_ID, VENDOR_ID, BEGDA) VALUES (?,?,?)";

	private static final String insertAddressSql =
			"INSERT INTO PURCHASE_DELIVERY_ADDRESS (PURCHASE_ORDER_ID, BUSINESS_PARTNER_ID, ADDRESS_ID) VALUES (?,?,?)";
	
	private static final String insertPurchaseReasonSql =
			"INSERT INTO PURCHASE_REASON (PURCHASE_ORDER_ID, LINE, REASON_TYPE, REASON_ID, REASON_LINE, BEGDA) VALUES (?,?,?,?,?,?)";

	private static final String purchaseByReasonSql =
			"SELECT PURCHASE_ORDER_ID, REASON_LINE FROM PURCHASE_REASON WHERE REASON_TYPE=? AND REASON_ID=? ORDER BY REASON_LINE";

	@Autowired
    private JdbcTemplate j;

	@Autowired
    private BasisService basisService;
	
//	class ProductsForCartRowMapper implements RowMapper<ViewProductsForCart> {
//	    @Override
//	    public ViewProductsForCart mapRow(ResultSet rs, int rowNum) throws SQLException {
//	        return new ViewProductsForCart(rs.getInt(1), rs.getString(2), "", "", rs.getBigDecimal(3), null, 'N');
//	    }
//	}

	@Transactional(readOnly=true)
    public List<ViewTreeData> purchaseGroups() {
    	return basisService.treeData(purchaseGroups);
    }

	@Transactional(readOnly=true)
    public Object[] rfpProposalChart(int rfpId) {
		
		Object[] o = j.query(rfpProposalSql, new ArgumentPreparedStatementSetter(new Object[]{rfpId}), new ResultSetExtractor<Object[]>() {
			@Override
			public Object[] extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<ArrayList<String>> rt = new ArrayList<ArrayList<String>>();
				ArrayList<Integer> proposals = new ArrayList<Integer>();
				DecimalFormat df = new DecimalFormat("#,##0.00");
				String empty = "";
				int gProposalId = 0;
				short maxline = 0;
				int maxcol =4;

				ArrayList<String> newLine = new ArrayList<String>();
				newLine.add("MATERIAL_TYPE");
				newLine.add("BRAND");
				newLine.add("PART_NUMBER");
				newLine.add("QUANTITY");
				newLine.add("UNIT");
//				newLine.add("Description");
				rt.add(newLine);
				for (int i = 0; i < 5; i++) {
					proposals.add(0);
				}

				while (rs.next()) {
//					int    rfpId         = rs.getInt(1);
					short  line          = rs.getShort(2);
					String materialType  = rs.getString(3);
					String rManufacturer = rs.getString(4);
					String rMaterial     = rs.getString(5);
//					String rPartnumber   = rs.getString(6);
					double quantity      = rs.getDouble(7);
					String unit          = rs.getString(8);
//					String specification = rs.getString(9);
//					String material      = rs.getString(10);
					String manufacturer  = rs.getString(11);
//					String partnumber    = rs.getString(12);
					int    proposalId    = rs.getInt(13);
					double unitPrice     = rs.getDouble(14);
					short  discountPct   = rs.getShort(15);
					short  taxPct        = rs.getShort(16);
					String currency      = rs.getString(17);

					if (manufacturer == null) manufacturer = "";
					
					if (maxline < line) {
						maxline++;
						newLine = new ArrayList<String>();
						newLine.add(materialType);
						newLine.add(rManufacturer);
						newLine.add(rMaterial);
						newLine.add(quantity+"");
						newLine.add(unit);
//						newLine.add(specification);
						for (int i = 4; i < maxcol; i++) {
							newLine.add(empty);
						}
						rt.add(newLine);
					}
					
					if (proposalId != gProposalId) {
						gProposalId = proposalId;
						maxcol = maxcol+3;
						proposals.add(gProposalId);
						rt.get(0).add("BRAND");
						rt.get(0).add("UNIT_PRICE - %DISCOUNT");
						rt.get(0).add("Cur");
						for (int i = 1; i <= maxline; i++) {
							rt.get(i).add(empty);
							rt.get(i).add(empty);
							rt.get(i).add(empty);
						}
					}
					if (unitPrice>0) {
						rt.get(line).set(maxcol-2, manufacturer);
						rt.get(line).set(maxcol-1, df.format(unitPrice) + " - %"+discountPct + " + %"+taxPct);
						rt.get(line).set(maxcol, currency);
					}
				}
				return new Object[] {proposals, rt};
			}
		});
		return o;
	}
	
	public int publishRfp(int rfpId, int vendorId) {
		return j.update(rfpPublishSql, rfpId, vendorId, new Date());
	}

	public int addDeliveryAddress(int purchaseOrderId, int businessPartnerId, short addressId) {
		return j.update(insertAddressSql, purchaseOrderId, businessPartnerId, addressId);
	}

	public int addPurchaseReason(int purchaseOrderId, short line, String reasonType, int reasonId, short reasonLine, Date begda) {
		int i = j.update(insertPurchaseReasonSql, purchaseOrderId, line, reasonType, reasonId, reasonLine, begda);
		return i;
	}

	public int completePurchaseReason(char reasonType, int reasonId) {
		switch (reasonType) {
			case 'R': return j.update("UPDATE REQUEST_FOR_PROPOSAL SET STATUS='C' WHERE RFP_ID=" + reasonId);
			default: return 0;
		}
	}
	
	public int addPurchaseRef(char refType, int refId, int orderId) {
		switch (refType) {
			case 'P': return j.update("UPDATE PROPOSAL_TO_RFP SET PURCHASE_ORDER_ID=" + orderId + " WHERE PROPOSAL_ID=" + refId);
			default: return 0;
		}
	}
	
	public List<int[]> purchaseByReason(String reasonTtype, int reasonId) {
		
		ResultSetExtractor<ArrayList<int[]>> rse = new ResultSetExtractor<ArrayList<int[]>>() {
			@Override
			public ArrayList<int[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<int[]> r = new ArrayList<int[]>();
				while (rs.next()) {
					int i[] = new int[2];
					i[0] = rs.getInt(1);
					i[1] = rs.getShort(2);
					r.add(i);
				}
				return r;
			}
		};	
		
		return j.query(purchaseByReasonSql, new ArgumentPreparedStatementSetter(new Object[]{reasonTtype,reasonId}), rse);
	}

	public List<String[]> allPublishedByCity(int client, String country, String state, String city) {
		String sql =
				"SELECT A.COUNTRY, A.STATE, A.CITY, COUNT(*) FROM REQUEST_FOR_PROPOSAL T, PARTNER_ADDRESS A" +
				" WHERE ( (T.STATUS = 'G' AND PURCHASE_AREA IN (SELECT NODE_ID FROM VENDOR_BUSINESS WHERE BUSINESS_PARTNER_ID=" + client + "))" +
				"    OR   (T.STATUS = 'P' AND T.RFP_ID IN (SELECT RFP_ID FROM RFP_PUBLISHMENT WHERE VENDOR_ID=" + client + ")))" + 
				"   AND A.BUSINESS_PARTNER_ID=T.OWNER_ID" +
				"   AND A.ADDRESS_ID=T.DELIVERY_ADDRESS";
		if (!Utils.emptyStr(country))
			sql = sql + " AND A.COUNTRY='" + country + "'";
		if (!Utils.emptyStr(state))
			sql = sql + " AND A.STATE='" + state + "'";
		if (!Utils.emptyStr(city))
			sql = sql + " AND A.STATE='" + city + "'";
		sql = sql + " GROUP BY A.COUNTRY, A.STATE, A.CITY ORDER BY 1,2,3";

		return j.query(sql, new ResultSetExtractor<List<String[]>>() {
			@Override
			public List<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String[]> r = new ArrayList<String[]>();
				while (rs.next()) {
					String[] item = new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
					r.add(item);
				}
				return r;
			}
		});
	}
}
