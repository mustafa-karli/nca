package com.nauticana.material.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.material.model.MaterialAttributeGroup;
import com.nauticana.material.repository.MaterialJdbcRepository;

@Service
public class MaterialJdbcService {

	@Autowired
	private MaterialJdbcRepository r;

    public List<MaterialAttributeGroup> materialAttrGroupByMatGroup(int materialGroup) {
    	return r.materialAttrGroupByMatGroup(materialGroup);
    }
    
    public int addMaterialGroupAssignment(int materialGroupId, int materialId, Date begda) {
    	return r.addMaterialGroupAssignment(materialGroupId, materialId, begda);
    }
    
    public int addMaterialAttribute(int materialId, String magId, String value) {
    	return r.addMaterialAttribute(materialId, magId, value);
    }

}
