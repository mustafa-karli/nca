package com.nauticana.business.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.Utils;

@Repository
public class BusinessJdbcRepository {

	private static final String insertVendorSql =
			"INSERT INTO VENDOR (BUSINESS_PARTNER_ID, BEGDA, APPROVED) VALUES (?, ?, ?)";
	
	private static final String insertCustomerSql =
			"INSERT INTO CUSTOMER (BUSINESS_PARTNER_ID, BEGDA) VALUES (?, ?)";

	private static final String insertOwnerSql =
			"INSERT INTO BUSINESS_OWNER (BUSINESS_PARTNER_ID, BEGDA) VALUES (?, ?)";

	private static final String insertSubcontractorSql =
			"INSERT INTO SUBCONTRACTOR (BUSINESS_PARTNER_ID, BEGDA) VALUES (?, ?)";

	private static final String partnerDateSql =
			"SELECT B.CAPTION, B.HQ_COUNTRY, B.TAX_CENTER, B.TAX_NUMBER, O.BEGDA AS OWNER_DATE, S.BEGDA AS SUBCONTRACTOR_DATE, V.BEGDA AS VENDOR_DATE, C.BEGDA AS CUSTOMER_DATE" + 
			"  FROM (((BUSINESS_PARTNER AS B" + 
			"  LEFT JOIN BUSINESS_OWNER AS O ON B.BUSINESS_PARTNER_ID = O.BUSINESS_PARTNER_ID)" + 
			"  LEFT JOIN SUBCONTRACTOR AS S  ON B.BUSINESS_PARTNER_ID = S.BUSINESS_PARTNER_ID)" + 
			"  LEFT JOIN VENDOR AS V         ON B.BUSINESS_PARTNER_ID = V.BUSINESS_PARTNER_ID)" + 
			"  LEFT JOIN CUSTOMER AS C       ON B.BUSINESS_PARTNER_ID = C.BUSINESS_PARTNER_ID" + 
			" WHERE B.BUSINESS_PARTNER_ID=?";

	private static final String vendorBusinessSql = "SELECT A.NODE_ID, B.CAPTION FROM VENDOR_BUSINESS A, TREE_DATA B WHERE A.BUSINESS_PARTNER_ID=? AND B.NODE_ID=A.NODE_ID ORDER BY 1";

	private static final String insertVendorBusinessSql =
			"INSERT INTO VENDOR_BUSINESS (BUSINESS_PARTNER_ID, NODE_ID, BEGDA) VALUES (?, ?, ?)";
	
	private static final String deleteVendorBusinessSql =
			"DELETE FROM VENDOR_BUSINESS WHERE BUSINESS_PARTNER_ID=? AND NODE_ID=?";

	private static final String getPartnerCreditSql =
			"SELECT BALANCE PARTNER_CREDIT WHERE BUSINESS_PARTNER_ID=? AND CREDIT_TYPE = ?";

	private static final String insPartnerCreditSql =
			"INSERT INTO PARTNER_CREDIT (BUSINESS_PARTNER_ID, CREDIT_TYPE, BALANCE) VALUES (?, ?, ?)";

	private static final String addPartnerCreditSql =
			"UPDATE PARTNER_CREDIT SET BALANCE=BALANCE + ?  WHERE BUSINESS_PARTNER_ID=? AND CREDIT_TYPE = ?";

	private static final String decPartnerCreditSql =
			"UPDATE PARTNER_CREDIT SET BALANCE=BALANCE - ?  WHERE BUSINESS_PARTNER_ID=? AND CREDIT_TYPE = ?";

	@Autowired
    private JdbcTemplate j;
	
	@Transactional
	public int addVendor(int partnerId, String approved) {
		return j.update(insertVendorSql, partnerId, Utils.onlyDate(), approved);
	}
	
	@Transactional
	public int addCustomer(int partnerId) {
		return j.update(insertCustomerSql, partnerId, Utils.onlyDate());
	}
	
	@Transactional
	public int addBusinessOwner(int partnerId) {
		return j.update(insertOwnerSql, partnerId, Utils.onlyDate());
	}
	
	@Transactional
	public int addSubcontractor(int partnerId) {
		return j.update(insertSubcontractorSql, partnerId, Utils.onlyDate());
	}
	
	public String[] partnerDates(int partnerId) {
		return j.query(partnerDateSql, new ArgumentPreparedStatementSetter(new Object[]{partnerId}), new ResultSetExtractor<String[]>() {
			@Override
			public String[] extractData(ResultSet rs) throws SQLException, DataAccessException {
				String[] d = new String[8];
				if (rs.next()) {
					d[0] = rs.getString(1);
					d[1] = rs.getString(2);
					d[2] = rs.getString(3);
					d[3] = rs.getString(4);
					try {d[4] = Labels.dmyDF.format(rs.getDate(5));} catch (Exception e) {d[4] = null;}
					try {d[5] = Labels.dmyDF.format(rs.getDate(6));} catch (Exception e) {d[5] = null;}
					try {d[6] = Labels.dmyDF.format(rs.getDate(7));} catch (Exception e) {d[6] = null;}
					try {d[7] = Labels.dmyDF.format(rs.getDate(8));} catch (Exception e) {d[7] = null;}
				}
				return d;
			}
		});
	}
	
	public List<String[]> vendorBusinesses(int partnerId) {
		return j.query(vendorBusinessSql, new ArgumentPreparedStatementSetter(new Object[]{partnerId}), new ResultSetExtractor<List<String[]>>() {
			@Override
			public List<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String[]> d = new ArrayList<String[]>();
				while (rs.next()) {
					d.add(new String[] {rs.getString(1),rs.getString(2)});
				}
				return d;
			}
		});
	}

	public void addVendorBusiness(int partnerId, int nodeId) {
		j.update(insertVendorBusinessSql, partnerId, nodeId, Utils.onlyDate());
	}
	
	public void deleteVendorBusiness(int partnerId, int nodeId) {
		j.update(deleteVendorBusinessSql, partnerId, nodeId);
	}

	public double getPartnerCredit(int partnerId, String creditType) {
		return j.query(getPartnerCreditSql, new ArgumentPreparedStatementSetter(new Object[]{partnerId, creditType}), new ResultSetExtractor<Double>() {
			@Override
			public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getDouble(1);
				}
				return (double) 0;
			}
		});
	}
	
	public void addPartnerCredit(int partnerId, String creditType, double credit) throws Exception {
		if (credit <= 0)
			throw new Exception("Add positive credit");
		if (j.update(addPartnerCreditSql, credit, partnerId, creditType) < 1)
			j.update(insPartnerCreditSql, partnerId, creditType, credit);
	}
	
	public void decPartnerCredit(int partnerId, String creditType, double credit) throws Exception {
		double balance = getPartnerCredit(partnerId, creditType);
		if (balance < credit)
			throw new Exception("Balance not enough");
		j.update(decPartnerCreditSql, credit, partnerId, creditType);
	}

}
