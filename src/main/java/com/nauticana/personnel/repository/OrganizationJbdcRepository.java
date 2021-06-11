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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.personnel.model.ViewOrganizationContainer;

/**
 * Date: 3/19/18
 * Time: 3:56 PM
 */
@Repository
public class OrganizationJbdcRepository {

//    private static final String getFindAllChildren=
//            "WITH RECURSIVE tblChild AS(" +
//            "SELECT O.ORGANIZATION_ID " +
//            "   FROM ORGANIZATION O WHERE O.PARENT_ID = 10" +
//            "   UNION ALL" +
//            "   SELECT ORGANIZATION.ORGANIZATION_ID  FROM ORGANIZATION JOIN tblChild ON ORGANIZATION.PARENT_ID = tblChild.ORGANIZATION_ID" +
//            ")" +
//            "SELECT * " +
//            "FROM tblChild" +
//            "LIMIT 500000";

    private static final String findAllChildren =
            "SELECT O.ORGANIZATION_ID" +
                    "  FROM ORGANIZATION O" +
                    " WHERE O.PARENT_ID = ? ";

    private static final String findSiblings =
            "SELECT O.ORGANIZATION_ID, CAPTION" +
            "  FROM ORGANIZATION O" +
            " WHERE O.PARENT_ID = ? ";

    @Autowired
    private JdbcTemplate jdbc;

    class IntegerRowMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt(1);
        }
    }

    @Transactional(readOnly=true)
    public List<Integer> getAllChildren(int parentId) {
        return jdbc.query(findAllChildren, new ArgumentPreparedStatementSetter(new Object[]{parentId}), new IntegerRowMapper());
    }

    @Transactional(readOnly=true)
    public List<ViewOrganizationContainer> getSiblings(int parentId) {
        return jdbc.query(findSiblings, new ArgumentPreparedStatementSetter(new Object[]{parentId}), new ResultSetExtractor<List<ViewOrganizationContainer>>() {

			@Override
			public List<ViewOrganizationContainer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<ViewOrganizationContainer> items = new ArrayList<ViewOrganizationContainer>();
				while (rs.next()) {
					items.add(new ViewOrganizationContainer(rs.getInt(1), rs.getString(2)));
				}
				if (items.isEmpty()) return null;
				return items;
			}
        });
    }

    @Transactional(readOnly=true)
    public void getSiblings(ViewOrganizationContainer parent) {
    	parent.setChildren(getSiblings(parent.getId()));
    	if (parent.getChildren() == null) return;
    	for (ViewOrganizationContainer record : parent.getChildren()) {
    		getSiblings(record);
    	}
    }
    

}