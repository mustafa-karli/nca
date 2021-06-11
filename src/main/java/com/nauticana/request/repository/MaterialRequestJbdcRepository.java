package com.nauticana.request.repository;

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

import com.nauticana.sales.model.ViewProductGroupsForCart;
import com.nauticana.sales.model.ViewProductsForCart;

@Repository
public class MaterialRequestJbdcRepository {

    private static final String productGroupsForRequest    =
            "SELECT G.MATERIAL_GROUP_ID, G.CAPTION AS MATERIAL_GROUP_CAPTION, M.MATERIAL_ID, M.CAPTION"
                    + "  FROM MATERIAL_GROUP G, MATERIAL_GROUP_ASSIGNMENT A, MATERIAL M"
                    + " WHERE G.PURPOSE = ?"
                    + "   AND G.MATERIAL_GROUP_ID = A.MATERIAL_GROUP_ID"
                    + "   AND A.MATERIAL_ID       = M.MATERIAL_ID "
                    + "   AND A.BEGDA            >= ? "
                    + " ORDER BY G.PARENT_ID NULLS FIRST, G.MATERIAL_GROUP_ID, M.CAPTION ";

    private static final String productRequestGroups =
            "SELECT G.MATERIAL_GROUP_ID" +
                    "  FROM MATERIAL_GROUP G" +
                    " WHERE G.OWNER_ID = ? AND G.PURPOSE = ? AND PARENT_ID IS NULL";

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
    public List<Integer> productGroupsForRequest(int client, String purpose) {
        return j.query(productRequestGroups, new ArgumentPreparedStatementSetter(new Object[]{client, purpose}), new IntegerRowMapper());
    }

    @Transactional(readOnly=true)
    public List<ViewProductGroupsForCart> viewProductGroupsForRequest(String purpose, Date date) {

        List<ViewProductGroupsForCart> pgfc = j.query(productGroupsForRequest, new ArgumentPreparedStatementSetter(new Object[]{purpose, date, date}), new ResultSetExtractor<List<ViewProductGroupsForCart>>() {
            @Override
            public List<ViewProductGroupsForCart> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<ViewProductGroupsForCart> rt = new ArrayList<ViewProductGroupsForCart>();
                ViewProductGroupsForCart group = null;
                int oldg = -1;
                while (rs.next()) {
                    ViewProductsForCart p = new ViewProductsForCart();
                    int groupId = rs.getInt(1);
                    String groupCaption = rs.getString(2);
                    p.setId(rs.getInt(3));
                    p.setCaption(rs.getString(4));
                    p.setPrice(new BigDecimal(0));
                    if (group == null || oldg != groupId) {
                        group = new ViewProductGroupsForCart(groupId, groupCaption);
                        rt.add(group);
                    }
                    group.getItems().add(p);
                }
                return rt;
            }
        });
        return pgfc;
    }

}
