package com.nauticana.personnel.repository;

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

import com.nauticana.personnel.model.ViewEmployeeUser;

@Repository
public class PersonnelJdbcRepository {

	private static final String creditCardSql =
			"INSERT INTO CREDIT_CARD (CARD_NUMBER, CVC2, EXPIRE_YEAR, EXPIRE_MONTH, NAME_ON_CARD, PERSON_ID) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String empUserSql =
			"SELECT E.PERSON_ID, E.EMPLOYMENT, E.POSITION, E.EMAIL_ADDRESS, E.WORK_PHONE," + 
			"       P.FIRST_NAME, P.MIDDLE_NAME, P.LAST_NAME, P.BIRTHDAY, P.GENDER, P.NATIONALITY, P.GOVERNMENT_ID, P.PERSONAL_MAIL, P.CELL_PHONE," + 
			"       U.USERNAME, U.STATUS, U.EMAIL_ADDRESS AS USER_MAIL, U.PASSDATE, U.LASTLOGON" + 
			"  FROM EMPLOYEE E, PERSON P, USER_ACCOUNT_OWNER O, USER_ACCOUNT U" + 
			" WHERE P.PERSON_ID=E.PERSON_ID" + 
			"   AND O.PERSON_ID=P.PERSON_ID" + 
			"   AND U.USERNAME=O.USERNAME" + 
			"   AND E.OWNER_ID=?" + 
			"   AND E.DEPARTURE IS NULL" + 
			" ORDER BY E.EMPLOYMENT, E.PERSON_ID";
	
	@Autowired
    private JdbcTemplate j;
	
	@Transactional
	public int addCreditCard(String cardNumber, String cvc2, short expireYear, short expireMonth, String nameOnCard, int personId) {
		return j.update(creditCardSql, cardNumber, cvc2, expireYear, expireMonth, nameOnCard, personId);
	}
	
	@Transactional(readOnly=true)
    public List<ViewEmployeeUser> viewEmployeeUsers(int client) {
		List<ViewEmployeeUser> list = j.query(empUserSql, new ArgumentPreparedStatementSetter(new Object[]{client}), new ResultSetExtractor<List<ViewEmployeeUser>>() {
			@Override
			public List<ViewEmployeeUser> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<ViewEmployeeUser> rt = new ArrayList<ViewEmployeeUser>();
				while (rs.next()) {
					rt.add(new ViewEmployeeUser(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getDate(18), rs.getDate(19)));
				}
				return rt;
			}
		});
		return list;
    }
	
	
}
