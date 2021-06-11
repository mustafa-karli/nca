package com.nauticana.sales.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.sales.model.ViewCommitment;
import com.nauticana.sales.model.ViewCommitmentOrder;
import com.nauticana.sales.model.ViewProductGroupsForCart;
import com.nauticana.sales.model.ViewProductsForCart;

@Repository
public class SalesJbdcRepository {

	private static final String productsForCart    = 
			"SELECT M.MATERIAL_ID, M.CAPTION, P.PRICE" +
			"  FROM MATERIAL M, MATERIAL_SALE_PRICE P" +
			" WHERE M.MATERIAL_ID=P.MATERIAL_ID " +
			"   AND ? BETWEEN P.BEGDA AND P.ENDDA " +
			" ORDER BY M.CAPTION ";
	
	private static final String productGroupsForCart    = 
//			"SELECT G.MATERIAL_GROUP_ID" +
//			"  FROM MATERIAL_GROUP G" +
//			" WHERE G.PURPOSE = ? AND PARENT_ID IS NULL";
			  "SELECT G.MATERIAL_GROUP_ID, G.CAPTION AS MATERIAL_GROUP_CAPTION, M.MATERIAL_ID, M.CAPTION, P.PRICE"
			+ "  FROM MATERIAL_GROUP G, MATERIAL_GROUP_ASSIGNMENT A, MATERIAL M, MATERIAL_SALE_PRICE P"
			+ " WHERE G.PURPOSE = ?"
			+ "   AND G.MATERIAL_GROUP_ID = A.MATERIAL_GROUP_ID"
			+ "   AND A.MATERIAL_ID       = M.MATERIAL_ID "
			+ "   AND A.BEGDA            >= ? "
			+ "   AND M.MATERIAL_ID       = P.MATERIAL_ID "
			+ "   AND ? BETWEEN P.BEGDA AND P.ENDDA "
			+ " ORDER BY G.PARENT_ID NULLS FIRST, G.MATERIAL_GROUP_ID, M.CAPTION ";
	
	private static final String productSalesGroups = 
			"SELECT G.MATERIAL_GROUP_ID" +
			"  FROM MATERIAL_GROUP G" +
			" WHERE G.PURPOSE = ? AND PARENT_ID IS NULL";
	
	private static final String commitmentSql =
			"SELECT * FROM V_PRODUCTION_COMMITMENT WHERE ORDER_DEAD_LINE >= ? AND STATUS='A' ORDER BY ORDER_DEAD_LINE, CURRENT_ORDER, BUSINESS_PARTNER_ID, MATERIAL_ID, QUANTITY";

	private static final String myCommitmentsSql =
			"SELECT * FROM V_PRODUCTION_COMMITMENT WHERE OWNER_ID = ? ORDER BY ORDER_DEAD_LINE DESC, CURRENT_ORDER, BUSINESS_PARTNER_ID, MATERIAL_ID, QUANTITY";

	private static final String commitmentSalesSql =
			"SELECT * FROM V_PRODUCTION_COMMITMENT_SALES WHERE CUSTOMER_ID = ? AND STATUS = 'A' ORDER BY ORDER_DEAD_LINE, CURRENT_ORDER, BUSINESS_PARTNER_ID, MATERIAL_ID, SALES_ORDER_ID, QUANTITY";

	private static final String salesAddressSql =
			"INSERT INTO SALES_DELIVERY_ADDRESS (SALES_ORDER_ID, ADDRESS, CITY, STATE, COUNTRY, LONGITUDE, LATITUDE, ALTITUDE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String commitmentOrdersSql =
			"SELECT C.INITIAL_SEQUENCE, I.QUANTITY, I.UNIT_PRICE, I.DISCOUNT, I.CURRENCY, I.STATUS," + 
			"       O.SALES_ORDER_ID, O.ORDER_DATE, O.ADVANCE_PAYMENT, O.DESCRIPTION," + 
			"       B.CAPTION, O.USERNAME, U.FIRST_NAME, U.LAST_NAME, U.EMAIL_ADDRESS," + 
			"       A.ADDRESS, A.CITY, A.STATE, A.COUNTRY" + 
			"  FROM SALES_ORDER_COMMITMENT C, SALES_ORDER_ITEM I, BUSINESS_PARTNER B, USER_ACCOUNT U, SALES_ORDER O LEFT JOIN SALES_DELIVERY_ADDRESS A ON A.SALES_ORDER_ID=O.SALES_ORDER_ID" + 
			" WHERE I.SALES_ORDER_ID=C.SALES_ORDER_ID" + 
			"   AND I.LINE=C.LINE" + 
			"   AND O.SALES_ORDER_ID=I.SALES_ORDER_ID" + 
			"   AND O.STATUS<>'F'" + 
			"   AND B.BUSINESS_PARTNER_ID=O.CUSTOMER_ID" + 
			"   AND U.USERNAME=O.USERNAME" + 
			"   AND C.MATERIAL_ID=?" + 
			"   AND C.BUSINESS_PARTNER_ID=?" + 
			"   AND C.ORDER_DEAD_LINE=?" +
			" ORDER BY C.INITIAL_SEQUENCE"; 
			
	
	private static final String discountSalesOrderItemSql = "UPDATE SALES_ORDER_ITEM SET DISCOUNT=? WHERE SALES_ORDER_ID=? AND LINE=?";
	private static final String discountSalesOrderSql     = "UPDATE SALES_ORDER_ITEM SET DISCOUNT=DISCOUNT+? WHERE SALES_ORDER_ID=?";
	
	private static final String salesOrderItemSql    = "SELECT * FROM SALES_ORDER_ITEM WHERE SALES_ORDER_ID=? AND LINE=?";
	private static final String updSalesOrderItemSql = "UPDATE SALES_ORDER_ITEM SET QUANTITY=?, UNIT_PRICE=?, DISCOUNT=? WHERE SALES_ORDER_ID=? AND LINE=?";
	private static final String insSalesOrderItemSql = "INSERT INTO SALES_ORDER_ITEM (SALES_ORDER_ID, LINE, MATERIAL_ID, QUANTITY, UNIT, UNIT_PRICE, DISCOUNT, CURRENCY, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Autowired
    private JdbcTemplate j;

	class ProductsForCartRowMapper implements RowMapper<ViewProductsForCart> {
	    @Override
	    public ViewProductsForCart mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new ViewProductsForCart(rs.getInt(1), rs.getString(2), "", "", rs.getBigDecimal(3), null, 'N');
	    }
	}

	class IntegerRowMapper implements RowMapper<Integer> {
	    @Override
	    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getInt(1);
	    }
	}

	@Transactional(readOnly=true)
    public List<ViewProductsForCart> productsForCart(Date salesDate) {
    	return j.query(productsForCart, new ArgumentPreparedStatementSetter(new Object[]{salesDate}), new ProductsForCartRowMapper());
    }

	@Transactional(readOnly=true)
    public List<Integer> productGroupsForCart(String purpose) {
    	return j.query(productSalesGroups, new ArgumentPreparedStatementSetter(new Object[]{purpose}), new IntegerRowMapper());
    }

	@Transactional(readOnly=true)
    public List<ViewProductGroupsForCart> viewProductGroupsForCart(String purpose, Date date) {
		
		List<ViewProductGroupsForCart> pgfc = j.query(productGroupsForCart, new ArgumentPreparedStatementSetter(new Object[]{purpose, date, date}), new ResultSetExtractor<List<ViewProductGroupsForCart>>() {
			@Override
			public List<ViewProductGroupsForCart> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<ViewProductGroupsForCart> rt = new ArrayList<ViewProductGroupsForCart>();
				ViewProductGroupsForCart group = null;
				int oldg = -1;
				while (rs.next()) {
					ViewProductsForCart p = new ViewProductsForCart();
					p.setId(rs.getInt(1));
					p.setCaption(rs.getString(2));
					p.setPrice(rs.getBigDecimal(3));
					int g = rs.getInt(1);
					String gc = rs.getString(2);
					if (group == null || oldg != g) {
						group = new ViewProductGroupsForCart(g, gc);
						rt.add(group);
					}
					group.getItems().add(p);
				}
				return rt;
			}
		});
		return pgfc;
    }

	
	private List<ViewCommitment> extractCommitments(ResultSet rs) throws SQLException, DataAccessException {
		ArrayList<ViewCommitment> rt = new ArrayList<ViewCommitment>();
		ViewCommitment cmt = null;
		int oldm = -1;
		int oldb = -1;
		Date oldd = new Date(System.currentTimeMillis()-86400000);
		while (rs.next()) {
			int        materialId        = rs.getInt(1);
			int        manufacturerId    = rs.getInt(2);
			int        businessPartnerId = rs.getInt(3);
			int        ownerId           = rs.getInt(4);
			String     caption           = rs.getString(5);
			String     vendorCaption     = rs.getString(6);
			String     partNumber        = rs.getString(7);
			Date       orderDeadLine     = rs.getDate(8);
			Date       deliveryPromise   = rs.getDate(9);
			int        minQuantity       = rs.getInt(10);
			int        maxQuantity       = rs.getInt(11);
			String     unit              = rs.getString(12);
			BigDecimal startPrice        = rs.getBigDecimal(13);
			String     currency          = rs.getString(14);
			int        currentOrder      = rs.getInt(15);
			BigDecimal currentPrice      = rs.getBigDecimal(16);		
			int        quantity          = rs.getInt(17);
			BigDecimal price             = rs.getBigDecimal(18);
			String     status            = rs.getString(19);
			
			if (oldm == materialId && oldb == businessPartnerId && oldd.getTime() == orderDeadLine.getTime()) {
				cmt.addDiscount(quantity, price);
			} else {
				cmt = new ViewCommitment(materialId, manufacturerId, businessPartnerId, ownerId, caption, vendorCaption, partNumber, orderDeadLine, deliveryPromise, minQuantity, maxQuantity, unit, startPrice, currency, currentOrder, currentPrice, status);
				rt.add(cmt);
			}
			oldm = materialId;
			oldb = businessPartnerId;
			oldd = orderDeadLine;
		}
		return rt;
	}
	
	@Transactional(readOnly=true)
    public List<ViewCommitment> viewCommitments(Date date) {
		
		List<ViewCommitment> list = j.query(commitmentSql, new ArgumentPreparedStatementSetter(new Object[]{date}), new ResultSetExtractor<List<ViewCommitment>>() {
			@Override
			public List<ViewCommitment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				return extractCommitments(rs);
			}
		});
		return list;
    }

	@Transactional(readOnly=true)
    public List<ViewCommitment> viewCommitmentSales(int client) {
		
		List<ViewCommitment> list = j.query(commitmentSalesSql, new ArgumentPreparedStatementSetter(new Object[]{client}), new ResultSetExtractor<List<ViewCommitment>>() {
			@Override
			public List<ViewCommitment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<ViewCommitment> rt = new ArrayList<ViewCommitment>();
				ViewCommitment cmt = null;
				int olds = -1;
				int oldm = -1;
				int oldb = -1;
				Date oldd = new Date(System.currentTimeMillis()-86400000);
				while (rs.next()) {
					int        materialId        = rs.getInt(1);
					int        manufacturerId    = rs.getInt(2);
					int        businessPartnerId = rs.getInt(3);
					int        ownerId           = rs.getInt(4);
					String     caption           = rs.getString(5);
					String     vendorCaption     = rs.getString(6);
					String     partNumber        = rs.getString(7);
					Date       orderDeadLine     = rs.getDate(8);
					Date       deliveryPromise   = rs.getDate(9);
					int        minQuantity       = rs.getInt(10);
					int        maxQuantity       = rs.getInt(11);
					String     unit              = rs.getString(12);
					BigDecimal startPrice        = rs.getBigDecimal(13);
					String     currency          = rs.getString(14);
					int        currentOrder      = rs.getInt(15);
					BigDecimal currentPrice      = rs.getBigDecimal(16);		
					int        quantity          = rs.getInt(17);
					BigDecimal price             = rs.getBigDecimal(18);
					
					int        salesOrderId      = rs.getInt(20);
					int        salesQuantity     = rs.getInt(21);
					BigDecimal unitPrice         = rs.getBigDecimal(22);
					int        initialSequence   = rs.getInt(23);
					BigDecimal initialPrice      = rs.getBigDecimal(24);
					String     status            = rs.getString(25);
					
					if (oldm == materialId && oldb == businessPartnerId && oldd.getTime() == orderDeadLine.getTime() && olds == salesOrderId) {
						cmt.addDiscount(quantity, price);
					} else {
						cmt = new ViewCommitment(materialId, manufacturerId, businessPartnerId, ownerId, caption, vendorCaption, partNumber, orderDeadLine, deliveryPromise, minQuantity, maxQuantity, unit, startPrice, currency, currentOrder, currentPrice, status);
						rt.add(cmt);
						cmt.setSalesOrderId(salesOrderId);
						cmt.setSalesQuantity(salesQuantity);
						cmt.setUnitPrice(unitPrice);
						cmt.setInitialPrice(initialPrice);
						cmt.setInitialSequence(initialSequence);
//						cmt.setStatus(status);
						oldm = materialId;
						oldb = businessPartnerId;
						oldd = orderDeadLine;
						olds = salesOrderId;
					}
				}
				return rt;
			}
		});
		return list;
    }

	@Transactional
	public int addSalesAddress(int id, String address, String city, String state, String country, double longitude, double latitude, double altitude) {
		return j.update(salesAddressSql, id, address, city, state, country, longitude, latitude, altitude);
	}

	@Transactional(readOnly=true)
    public List<ViewCommitment> viewMyCommitments(int client) {
		
		List<ViewCommitment> list = j.query(myCommitmentsSql, new ArgumentPreparedStatementSetter(new Object[]{client}), new ResultSetExtractor<List<ViewCommitment>>() {
			@Override
			public List<ViewCommitment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				return extractCommitments(rs);
			}
		});
		return list;
    }

	@Transactional(readOnly=true)
    public List<ViewCommitmentOrder> viewCommitmentOrders(int materialId, int client, Date date) {
		
		List<ViewCommitmentOrder> list = j.query(commitmentOrdersSql, new ArgumentPreparedStatementSetter(new Object[]{materialId, client, date}), new ResultSetExtractor<List<ViewCommitmentOrder>>() {
			@Override
			public List<ViewCommitmentOrder> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<ViewCommitmentOrder> rt = new ArrayList<ViewCommitmentOrder>();
				while (rs.next()) {
					rt.add(new ViewCommitmentOrder(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8), rs.getDouble(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19)));
				}
				return rt;
			}
		});
		return list;
    }

	public int modifyOrder(int salesOrderId, int line, double quantity, double price, double discount, int materialId, String unit, String currency) {
		int i = j.query(salesOrderItemSql, new ArgumentPreparedStatementSetter(new Object[]{salesOrderId, line}), new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return j.update(updSalesOrderItemSql, new Object[]{quantity, price, salesOrderId, line});
				} else {
					return j.update(insSalesOrderItemSql, new Object[]{salesOrderId, line, materialId, quantity, unit, price, discount, currency, "O"} );
				}
			}
		});
		return i;
	}

	public void discountOrder(int salesOrderId, int line, double quantity, double discount) {
		j.update(discountSalesOrderItemSql, new Object[]{discount, salesOrderId, line});
		j.update(discountSalesOrderSql, new Object[]{quantity*discount, salesOrderId} );
	}

	
}
