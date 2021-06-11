package com.nauticana.material.service;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialType;

@Service
public class MaterialTypeService extends AbstractService<MaterialType, String> {

	@Override
	@Transactional
	public MaterialType newEntityWithParentId(String parentKey) {
		MaterialType entity = new MaterialType();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public MaterialType newEntityWithId(String strId) {
		MaterialType entity = new MaterialType();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		String findAllSql = "SELECT T.* FROM MATERIAL_TYPE T WHERE T.MASTER=?";
		Query query = entityManager.createNativeQuery(findAllSql, MaterialType.class);
		query.setParameter(1, client);
		@SuppressWarnings("unchecked")
		List<MaterialType> list = query.getResultList();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(MaterialType x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(MaterialType entity) {
		return null;
	}

	@Override
	public String idAsStr(MaterialType entity) {
		if (entity != null)
			return entity.getId();
		else
			return null;
	}
}

