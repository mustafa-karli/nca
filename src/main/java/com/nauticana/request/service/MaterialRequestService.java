package com.nauticana.request.service;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.request.model.MaterialRequest;

@Service
public class MaterialRequestService extends AbstractService<MaterialRequest, Integer> {

	@Override
	@Transactional
	public MaterialRequest newEntityWithParentId(String parentKey) {
		MaterialRequest entity = new MaterialRequest();
		entity.setStatus("I");
		entity.setPurpose("S");
		return entity;
	}

	@Override
	public boolean organizationFiltered() {return true;}

	@Override
	public boolean customerSpecific() {return true;}
	
	@Override
	public void setClient(MaterialRequest entity, int client) {
		entity.setOwnerId(client);
	}

	@Override
	public int getClient(MaterialRequest entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public MaterialRequest newEntityWithId(String strId) {
		MaterialRequest entity = new MaterialRequest();
		entity.setStatus("I");
		entity.setPurpose("S");
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialRequest entity) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MaterialRequest> findByOrganization(int client, List<Integer> organization, Date begda, Date endda) {
		String o = organization.get(0)+"";
		for (int i = 0; i < organization.size(); i++) {
			o = o + "," + organization.get(i);
		}
		String sql = "SELECT T.* FROM MATERIAL_REQUEST T WHERE T.OWNER_ID=" + client + " AND T.ORGANIZATION_ID IN (" + o + ") AND T.REQUEST_DATE >= ? AND T.REQUEST_DATE <= ? AND T.STATUS NOT IN ('C','F') ORDER BY ORGANIZATION_ID";
		Query query = entityManager.createNativeQuery(sql, modelClass());
		query.setParameter(1, begda);
		query.setParameter(2, endda);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<MaterialRequest> findByPurpose(int client, String purpose, Date begda, Date endda) {
		String sql = "SELECT T.* FROM MATERIAL_REQUEST T WHERE T.OWNER_ID=" + client + " AND T.PURPOSE = '" + purpose + "' AND T.REQUEST_DATE >= ? AND T.REQUEST_DATE <= ? AND T.STATUS NOT IN ('C','F') ORDER BY ORGANIZATION_ID";
		Query query = entityManager.createNativeQuery(sql, modelClass());
		query.setParameter(1, begda);
		query.setParameter(2, endda);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<MaterialRequest> findAllActive(int client, Date begda, Date endda) {
		String sql = "SELECT T.* FROM MATERIAL_REQUEST T WHERE T.OWNER_ID=" + client + " AND T.STATUS NOT IN ('C','F') AND T.REQUEST_DATE >= ? AND T.REQUEST_DATE <= ? ORDER BY ORGANIZATION_ID";
		Query query = entityManager.createNativeQuery(sql, modelClass());
		query.setParameter(1, begda);
		query.setParameter(2, endda);
		return query.getResultList();
	}

	@Override
	public String idAsStr(MaterialRequest entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

