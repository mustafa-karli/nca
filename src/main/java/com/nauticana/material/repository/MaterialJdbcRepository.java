package com.nauticana.material.repository;

import java.util.Date;
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

import com.nauticana.material.model.MaterialAttributeGroup;
import com.nauticana.material.model.MaterialAttributeOption;
import com.nauticana.material.model.MaterialAttributeOptionId;

@Repository
public class MaterialJdbcRepository {

	private static final String	materialAttrGroupByMatGroupSql	=
			  "SELECT DISTINCT G.MAG_ID, G.OWNER_ID, G.CAPTION, A.VALUE"
			+ "  FROM MATERIAL_ATTRIBUTE_GROUP G, MATERIAL_ATTRIBUTE A"
			+ " WHERE A.MAG_ID=G.MAG_ID"
			+ "   AND A.MATERIAL_ID IN (SELECT MATERIAL_ID FROM MATERIAL_GROUP_ASSIGNMENT WHERE MATERIAL_GROUP_ID=?)"
			+ " ORDER BY 1,3";

	private static final String materialGroupAssignmentSql =
			"INSERT INTO MATERIAL_GROUP_ASSIGNMENT (MATERIAL_GROUP_ID, MATERIAL_ID, BEGDA) VALUES (?, ?, ?)";

	private static final String materialAttributeSql =
			"INSERT INTO MATERIAL_ATTRIBUTE (MATERIAL_ID, MAG_ID, VALUE) VALUES (?, ?, ?)";
	
	@Autowired
    private JdbcTemplate j;

    public List<MaterialAttributeGroup> materialAttrGroupByMatGroup(int materialGroup) {
        List<MaterialAttributeGroup> list = j.query(materialAttrGroupByMatGroupSql, new ArgumentPreparedStatementSetter(new Object[]{materialGroup}), new ResultSetExtractor<List<MaterialAttributeGroup>>() {
			@Override
			public List<MaterialAttributeGroup> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<MaterialAttributeGroup> l = new ArrayList<MaterialAttributeGroup>();
				String oldId = "";
				MaterialAttributeGroup g = null;
				while (rs.next()) {
		            String     magId       = rs.getString(1);
		            int        client      = rs.getInt(2);
		            String     caption     = rs.getString(3);
		            String     value       = rs.getString(4);
		            if (!magId.equals(oldId)) {
		            	g = new MaterialAttributeGroup();
		            	g.setId(magId);
		            	g.setOwnerId(client);
		            	g.setCaption(caption);
		            	oldId=magId;
		            }
		            MaterialAttributeOption mao = new MaterialAttributeOption();
		            mao.setId(new MaterialAttributeOptionId(magId, value));
		            mao.setMaterialAttributeGroup(g);
		            mao.setHigh(value);
	            	g.getMaterialAttributeOptions().add(mao);
				}
				return l;
			}
        });
        return list;
    }

	@Transactional
	public int addMaterialGroupAssignment(int materialGroupId, int materialId, Date begda) {
		return j.update(materialGroupAssignmentSql, materialGroupId, materialId, begda);
	}
    
	@Transactional
	public int addMaterialAttribute(int materialId, String magId, String value) {
		return j.update(materialAttributeSql, materialId, magId, value);
	}
    
}
