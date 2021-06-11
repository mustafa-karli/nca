package com.nauticana.material.service;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.service.ContentRelationService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.material.model.Manufacturer;
import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialType;

@Service
public class MaterialService extends AbstractService<Material, Integer> {

	@Autowired
	ContentRelationService contentRelationService;
	
	@Override
	@Transactional
	public Material newEntityWithParentId(String parentKey) {
		Material entity = new Material();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(Material entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(Material entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Material newEntityWithId(String strId) {
		Material entity = new Material();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Material> list = findAll(client);
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Material x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(Material entity) {
		return null;
	}

	@Override
	public String idAsStr(Material entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

	public Material checkMaterialByPart(int ownerId, Manufacturer manufacturer, MaterialType materialType, String partNumber, String defaultUnit) throws Exception {
		String sql = "SELECT T.* FROM MATERIAL T WHERE OWNER_ID=" + ownerId + " AND MANUFACTURER_ID='" + manufacturer.getId() + "' AND PART_NUMBER='" + partNumber + "'";
		Query query = entityManager.createNativeQuery(sql, Material.class);
		@SuppressWarnings("unchecked")
		List<Material> l = query.getResultList();
		if (!l.isEmpty())
			return l.get(0);
		Material m = new Material();
		m.setManufacturer(manufacturer);
		m.setMaterialType(materialType);
		m.setOwnerId(ownerId);
		m.setPartNumber(partNumber);
		m.setCaption(materialType.getCaption());
		m.setDefaultUnit(defaultUnit);
		return create(m);
	}
	
	public Material checkMaterialByCaption(int ownerId, Manufacturer manufacturer, MaterialType materialType, String caption, String defaultUnit) throws Exception {
		if (ownerId < 1 || Utils.emptyStr(caption) || Utils.emptyStr(defaultUnit) || materialType == null)
			return null;
		String sql ;
		if (manufacturer == null)
			sql = "SELECT T.* FROM MATERIAL T WHERE OWNER_ID=" + ownerId + " AND CAPTION='" + caption + "'";
		else
			sql = "SELECT T.* FROM MATERIAL T WHERE OWNER_ID=" + ownerId + " AND CAPTION='" + caption + "' AND MANUFACTURER_ID='" + manufacturer.getId() + "'";
		Query query = entityManager.createNativeQuery(sql, Material.class);
		@SuppressWarnings("unchecked")
		List<Material> l = query.getResultList();
		if (!l.isEmpty())
			return l.get(0);
		Material m = new Material();
		m.setManufacturer(manufacturer);
		m.setMaterialType(materialType);
		m.setOwnerId(ownerId);
		m.setCaption(caption);
		m.setDefaultUnit(defaultUnit);
		return create(m);
	}
	
}

