package com.nauticana.basis.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.model.PartnerConversation;
import com.nauticana.basis.model.UserMenu;
import com.nauticana.basis.model.ViewTreeData;
import com.nauticana.basis.utils.ControllerStatic;
import com.nauticana.basis.utils.FieldType;

@Repository
public class BasisRepository {

	public static final HashMap<String, FieldType[]> fieldTypes = new HashMap<String, FieldType[]>(0);
	public static final HashMap<String, ControllerStatic> controllerStatics = new HashMap<String, ControllerStatic>(0);
	
	private static final String userMenuSql       = "SELECT * FROM USER_MENU_PERMISSION WHERE USERNAME=? ORDER BY MENU_ORDER, PAGE_ORDER";
	private static final String tableDomainSql    = "SELECT DISTINCT DOMAIN FROM DOMAIN_LOOKUP WHERE TABLENAME=?";
	private static final String authObjectType    = "SELECT OBJECT_TYPE FROM AUTHORITY_OBJECT ORDER BY 1";
//	private static final String authGroupsNa      = "SELECT AUTHORITY_GROUP FROM AUTHORITY_GROUP WHERE NOT AUTHORITY_GROUP LIKE '%ADMIN%' ORDER BY 1";
	private static final String authObjectAct     = "SELECT ACTION FROM AUTHORITY_OBJECT_ACTION WHERE OBJECT_TYPE=?";
	private static final String authorityChk      =
			"SELECT COUNT(*) FROM OBJECT_AUTHORIZATION O, USER_AUTHORIZATION U" + 
			" WHERE O.AUTHORITY_GROUP=U.AUTHORITY_GROUP" + 
			"   AND U.USERNAME=?" + 
			"   AND O.OBJECT_TYPE=?" + 
			"   AND O.ACTION=?" + 
			"   AND O.KEY_VALUE IN ('*', ?)";
	
	private static final String authorityChkE     =
			"SELECT COUNT(*) FROM OBJECT_AUTHORIZATION O, USER_AUTHORIZATION U" + 
			" WHERE O.AUTHORITY_GROUP=U.AUTHORITY_GROUP" + 
			"   AND U.USERNAME=?" + 
			"   AND O.OBJECT_TYPE=?" + 
			"   AND O.ACTION=?";
	
	private static final String authorizedObjects =
			"SELECT DISTINCT KEY_VALUE" + 
			"  FROM USER_AUTHORIZATION U, AUTHORITY_GROUP G, OBJECT_AUTHORIZATION O" + 
			" WHERE U.AUTHORITY_GROUP=G.AUTHORITY_GROUP" + 
			"   AND O.AUTHORITY_GROUP=G.AUTHORITY_GROUP" + 
			"   AND U.USERNAME=?" + 
			"   AND O.OBJECT_TYPE=?" + 
			"   AND O.ACTION=?";
	
	public static final String userFavoritesSql          = "SELECT T.OBJECT_ID FROM USER_FAVORITE T WHERE T.USERNAME=? AND T.FAVORITE_TYPE=?";
	public static final String addUserFavoritesSql       = "INSERT INTO USER_FAVORITE VALUES (?, ?, ?, ?)";
	public static final String addUserRoleSql       	 = "INSERT INTO USER_AUTHORIZATION (USERNAME, AUTHORITY_GROUP, BEGDA, ENDDA) VALUES (?, ?, ?, ?)";

	public static final String tableControllerStaticSql  = "SELECT * FROM TABLE_CONTROLLER_STATIC WHERE TABLENAME= ?";
	public static final String tableControllerFieldSql   = "SELECT * FROM TABLE_FIELD_FACE WHERE TABLENAME= ?";
	public static final String tableControllerActionSql  = "SELECT * FROM TABLE_ACTION WHERE TABLENAME= ?";
	public static final String tableControllerContentSql = "SELECT * FROM TABLE_CONTENT_TYPE WHERE TABLENAME= ?";
	public static final String tableControllerDetailSql  = "SELECT * FROM MASTER_DETAIL_RELATION WHERE MASTER_TABLE= ?";
	
	public static final String addPartnerConversationSql   = "INSERT INTO PARTNER_CONVERSATION (OBJECT_ID, OBJECT_TYPE, CTIME, SEQ, FROM_PARTNER, TO_PARTNER, USERNAME, CTEXT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String partnerConversationSql      = "SELECT CTIME, SEQ, FROM_PARTNER, TO_PARTNER, USERNAME, CTEXT FROM PARTNER_CONVERSATION WHERE OBJECT_TYPE=? AND OBJECT_ID=? ORDER BY CTIME, SEQ";
	
	public static final String notificationSql = 
			  "SELECT A.NOTIFICATION_TYPE_ID, B.USERNAME"
			+ "  FROM NOTIFICATION_TYPE A, NOTIFICATION_RECIPIENT B"
			+ " WHERE A.NOTIFICATION_TYPE_ID = B.NOTIFICATION_TYPE_ID"
			+ "   AND A.OWNER_ID=?"
			+ "   AND A.TABLENAME=?"
			+ "   AND B.EVENT=?"
			+ "   AND B.ENABLE='Y'";
	
	private static final String clientNotificationSql =
			  "SELECT A.NOTIFICATION_TYPE_ID, B.USERNAME"
			+ "  FROM NOTIFICATION_TYPE A, NOTIFICATION_RECIPIENT B, EMPLOYEE E, USER_ACCOUNT_OWNER O"
			+ " WHERE E.OWNER_ID=?"
			+ "   AND A.TABLENAME=?"
			+ "   AND B.EVENT=?"
			+ "   AND B.ENABLE='Y'"
			+ "   AND A.NOTIFICATION_TYPE_ID = B.NOTIFICATION_TYPE_ID"
			+ "   AND O.PERSON_ID=E.PERSON_ID" 
			+ "   AND B.USERNAME=O.USERNAME" 
			+ "   AND E.DEPARTURE IS NULL";
	
	
	public static final String tableViewScenarioItemSql =
			"SELECT SEQ, MASTER_TABLE, METHOD, CHILD_TABLE, ACCESS_TYPE, PARENT_SEQ FROM TABLE_VIEW_SCENARIO_ITEM WHERE SCENARIO_ID=? ORDER BY SEQ";
	
	@Autowired
    private JdbcTemplate j;

	class StringRowMapper implements RowMapper<String> {
	    @Override
	    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getString(1);
	    }
	}

	@Transactional(readOnly=true)
    public List<UserMenu> userMenu(String username) {
		ArrayList<UserMenu> m = j.query(userMenuSql, new ArgumentPreparedStatementSetter(new Object[]{username}), new ResultSetExtractor<ArrayList<UserMenu>>() {
			@Override
			public ArrayList<UserMenu> extractData(ResultSet rs) throws SQLException, DataAccessException {
		    	ArrayList<UserMenu> m = new ArrayList<UserMenu>();
		    	while (rs.next()) {
		    		m.add(new UserMenu(rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
		    	}
		        return m;
			}
		});
		return m;
    }

	@Transactional(readOnly=true)
    public List<String[]> notificationUser(int client, String tablename, String event) {
    	String sql = notificationSql;
    	if (getControllerStatic(tablename).isClientDependent())
    		sql = clientNotificationSql;
   		return j.query(sql, new ArgumentPreparedStatementSetter(new Object[]{client, tablename, event}), new ResultSetExtractor<ArrayList<String[]>>() {
   			@Override
   			public ArrayList<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
   				ArrayList<String[]> m = new ArrayList<String[]>();
   				while (rs.next()) {
   					String[] line = new String[]{rs.getString(1), rs.getString(2)};
   					m.add(line);
   				}
   				return m;
   			}
   		});
    }

	
	
	@Transactional(readOnly=true)
    public boolean authorityChk(String username, String objectType, String action, String keyValue) {
		Integer k = j.query(authorityChk, new ArgumentPreparedStatementSetter(new Object[]{username, objectType, action, keyValue}), new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int i = 0;
				if (rs.next()) {
					i = rs.getInt(1);
				} else {
//					System.out.println("Authority check for " + username + " to " + action + " on " + objectType + "(" + keyValue + ") failed !!!");
				}
				return Integer.valueOf(i);
			}
		});
		return k.intValue() > 0;
    }
	
	@Transactional(readOnly=true)
    public boolean authorityChk(String username, String objectType, String action) {
		Integer k = j.query(authorityChkE, new ArgumentPreparedStatementSetter(new Object[]{username, objectType, action}), new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int i = 0;
				if (rs.next()) {
					i = rs.getInt(1);
				} else {
//					System.out.println("Authority check for " + username + " to " + action + " on " + objectType + " failed !!!");
				}
				return Integer.valueOf(i);
			}
		});
		return k.intValue() > 0;
    }
	
	@Transactional(readOnly=true)
    public List<ViewTreeData> treeData(String sql) {
		ArrayList<ViewTreeData> m = j.query(sql, new ResultSetExtractor<ArrayList<ViewTreeData>>() {
			@Override
			public ArrayList<ViewTreeData> extractData(ResultSet rs) throws SQLException, DataAccessException {
		    	ArrayList<ViewTreeData> grands = new ArrayList<ViewTreeData>();
		    	Hashtable<Integer, ViewTreeData> nodes = new Hashtable<Integer, ViewTreeData>();
		    	Hashtable<Integer, Integer> parentKeys = new Hashtable<Integer, Integer>();
		    	while (rs.next()) {
		    		int id = rs.getInt(1);
		    		String caption = rs.getString(2);
		    		int parentId;
		    		try {parentId = rs.getInt(3);} catch (Exception e) {parentId = -1;}
		    		nodes.put(id, new ViewTreeData(id, caption));
		    		if (parentId > 0)
		    			parentKeys.put(id, parentId);
		    	}
		    	for (Entry<Integer, ViewTreeData> entry : nodes.entrySet()) {
		    		ViewTreeData data = entry.getValue();
		    		Integer parentKey = parentKeys.get(entry.getKey());
		    		ViewTreeData parent = null;
		    		if (parentKey != null)
		    			parent = nodes.get(parentKey);
		    		if (parent == null) {
		    			grands.add(data);
		    		} else {
		    			parent.addChild(data);
		    		}
		    	}
		        return grands;
			}
		});
		return m;
    }


	@Transactional(readOnly=true)
    public FieldType[] fieldTypes(String tableName) {
		FieldType[] k = fieldTypes.get(tableName);
		if (k != null) return k;
		k = j.query("SELECT * FROM " + tableName + " WHERE 1=2", new ResultSetExtractor<FieldType[]>() {
			@Override
			public FieldType[] extractData(ResultSet rs) throws SQLException, DataAccessException {
				ResultSetMetaData md = rs.getMetaData();
				FieldType[] fields = new FieldType[md.getColumnCount()];
				for (int i = 1; i <= md.getColumnCount(); i++) {
					boolean required = (md.isNullable(i) == 0);
					FieldType field = new FieldType(md.getColumnName(i), md.getColumnType(i), md.getColumnDisplaySize(i), md.getScale(i), i, required);
					fields[i-1] = field;
//					for (int j = 0; j < k.length; j++) {
//						if (field.fieldName.equals(k[j])) {
//							keys[j] = field;
//							field.isKey = true;
//						}
//					}
				}
				return fields;
			}
		});
		if (k != null)
			fieldTypes.put(tableName, k);
		return k;
    }
	
	@Transactional(readOnly=true)
	public List<String> tableDomainsList(String tableName) {
    	return j.query(tableDomainSql, new ArgumentPreparedStatementSetter(new Object[]{tableName}), new StringRowMapper());
	}

	@Transactional(readOnly=true)
	public String[] tableDomains(String tableName) {
		ArrayList<String> s = j.query(tableDomainSql, new ArgumentPreparedStatementSetter(new Object[]{tableName}), new ResultSetExtractor<ArrayList<String>>() {
			@Override
			public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String> s = new ArrayList<String>();
				while (rs.next()) {
					s.add(rs.getString(1));
				}return s;
			}
		});
		String[] sl = new String[s.size()];
		for (int i = 0; i < sl.length; i++) {
			sl[i] = s.get(i);
		}
		return sl;
	}

	@Transactional(readOnly=true)
	public String[] userFavorites(String username, String favType) {
		ArrayList<String> s = j.query(userFavoritesSql, new ArgumentPreparedStatementSetter(new Object[]{username, favType}), new ResultSetExtractor<ArrayList<String>>() {
			@Override
			public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String> s = new ArrayList<String>();
				while (rs.next()) {
					s.add(rs.getString(1));
				}return s;
			}
		});
		String[] sl = new String[s.size()];
		for (int i = 0; i < sl.length; i++) {
			sl[i] = s.get(i);
		}
		return sl;
	}

	@Transactional
	public int addUserFavorite(String username, String favType, String objid, String description) {
		return j.update(addUserFavoritesSql, username, favType, objid, description);
	}
	
	@Transactional
	public int addUserRole(String username, String authorityGroup, Date begda, Date endda) {
		return j.update(addUserRoleSql, username, authorityGroup, begda, endda);
	}
	
	@Transactional(readOnly=true)
	public List<String> authorityObjectTypes() {
    	return j.query(authObjectType, new StringRowMapper());
	}

	@Transactional(readOnly=true)
	public List<String> authorityObjectActions(String authorityObject) {
    	return j.query(authObjectAct, new ArgumentPreparedStatementSetter(new Object[]{authorityObject}), new StringRowMapper());
	}

	@Transactional(readOnly=true)
	public List<String> authorizedObjects(String username, String authorityObject, String action) {
    	return j.query(authorizedObjects, new ArgumentPreparedStatementSetter(new Object[]{username, authorityObject, action}), new StringRowMapper());
	}

	public ControllerStatic getControllerStatic(String tableName) {
		ControllerStatic controllerStatic = controllerStatics.get(tableName);
		if (controllerStatic != null) return controllerStatic;
		
		controllerStatic = j.query(tableControllerStaticSql, new ArgumentPreparedStatementSetter(new Object[]{tableName}), new ResultSetExtractor<ControllerStatic>() {
			@Override
			public ControllerStatic extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					boolean clientDependent = "Y".equals(rs.getString(4));
					return new ControllerStatic(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), clientDependent);
				}
				return null;
			}
		});

		ArrayList<String[]> fields = j.query(tableControllerFieldSql, new ArgumentPreparedStatementSetter(new Object[]{tableName}), new ResultSetExtractor<ArrayList<String[]>>() {
			@Override
			public ArrayList<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String[]> f = new ArrayList<String[]>();
				while (rs.next()) {
					String [] s = new String[] {rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10) };
					f.add(s);
				}
				return f;
			}
		});
		
		FieldType[] fieldTypes = fieldTypes(tableName);
		
		for (int i = 0; i < fields.size(); i++) {
			String[] list = fields.get(i);
			for (int j = 0; j < fieldTypes.length; j++) {
				if (fieldTypes[j].fieldName.equals(list[0])) {
					fieldTypes[j].setEditStyle(list[1]);
					fieldTypes[j].setEditJstlPath(list[2]);
					fieldTypes[j].setViewJstlPath(list[3]);
					fieldTypes[j].setSearchStyle(list[4]);
					fieldTypes[j].setLookupStyle(list[5]);
					fieldTypes[j].setTranslated(list[6].charAt(0) == 'Y');
					fieldTypes[j].setMinValue(list[7]);
					fieldTypes[j].setMaxValue(list[8]);
				}
			}
		}
		
		controllerStatic.setFields(fieldTypes);
		
		ArrayList<String[]> contentTypes = j.query(tableControllerContentSql, new ArgumentPreparedStatementSetter(new Object[]{tableName}), new ResultSetExtractor<ArrayList<String[]>>() {
			@Override
			public ArrayList<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String[]> f = new ArrayList<String[]>();
				while (rs.next()) {
					String [] s = new String[] {rs.getString(2), rs.getString(3), rs.getString(4) };
					f.add(s);
				}
				return f;
			}
		});
		
		controllerStatic.setContentTypes(contentTypes);
		
		ArrayList<String[]> actions = j.query(tableControllerActionSql, new ArgumentPreparedStatementSetter(new Object[]{tableName}), new ResultSetExtractor<ArrayList<String[]>>() {
			@Override
			public ArrayList<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String[]> f = new ArrayList<String[]>();
				while (rs.next()) {
					String [] s = new String[] {rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) };
					f.add(s);
				}
				return f;
			}
		});
		
		controllerStatic.setActions(actions);
		
		controllerStatics.put(tableName, controllerStatic);
		
		ArrayList<String[]> details = j.query(tableControllerDetailSql, new ArgumentPreparedStatementSetter(new Object[]{tableName}), new ResultSetExtractor<ArrayList<String[]>>() {
			@Override
			public ArrayList<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String[]> f = new ArrayList<String[]>();
				while (rs.next()) {
					String [] s = new String[] {rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) };
					f.add(s);
				}
				return f;
			}
		});
		
		ArrayList<ControllerStatic> detailStatics = new ArrayList<ControllerStatic>();
		
		for (int i = 0; i < details.size(); i++) {
			detailStatics.add(getControllerStatic(details.get(i)[1]));
		}
		
		controllerStatic.setDetails(details, detailStatics);
		
		return controllerStatic;
	}

    public void addPartnerConversation(String objectType, int objectId, int fromPartner, int toPartner, String username, String ctext) {
		j.update(addPartnerConversationSql, objectId, objectType, new Date(), 0, fromPartner, toPartner, username, ctext);
    }
	
	
	@Transactional(readOnly=true)
    public List<PartnerConversation> partnerConversations(String objectType, int objectId) {
		ArrayList<PartnerConversation> m = j.query(partnerConversationSql, new ArgumentPreparedStatementSetter(new Object[]{objectType, objectId}), new ResultSetExtractor<ArrayList<PartnerConversation>>() {
			@Override
			public ArrayList<PartnerConversation> extractData(ResultSet rs) throws SQLException, DataAccessException {
		    	ArrayList<PartnerConversation> m = new ArrayList<PartnerConversation>();
		    	while (rs.next()) {
		    		m.add(new PartnerConversation(rs.getDate(1), rs.getShort(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
		    	}
		        return m;
			}
		});
		return m;
    }

	@Transactional(readOnly=true)
	public ArrayList<String[]> freeQueryString(String sql) {
		return j.query(sql, new ResultSetExtractor<ArrayList<String[]>>() {
			@Override
			public ArrayList<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String[]> s = new ArrayList<String[]>();
				while (rs.next()) {
					String[] x = new String[2];
					x[0] = rs.getString(1);
					x[1] = rs.getString(2);
					s.add(x);
				}return s;
			}
		});
	}


}
