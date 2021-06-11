package com.nauticana.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.Category;

@Service
public class CategoryService extends AbstractService<Category,Integer> {

	@Override
	@Transactional
	public Category newEntityWithParentId(String parentKey) {
		Category entity = new Category();
		entity.setMainFlag("-");
		if (!Utils.emptyStr(parentKey)) {
			Category parent = r.getOne(Integer.parseInt(parentKey));
			if (parent != null) {
				entity.setParentId(parent.getId());
				entity.setCatLevel((byte) (parent.getCatLevel()+1));
				entity.setTreeCode(parent.getTreeCode()+"-");
				entity.setUnit(parent.getUnit());
			}
		} else {
			entity.setCatLevel((byte) 1);
		}
		return entity;
	}

	public Category create(Category entity) throws Exception {
		if (entity.getParentId() != null) {
			Category parent = r.getOne(entity.getParentId());
			entity.setCatLevel((byte) (parent.getCatLevel()+1));
			entity.setTreeCode(parent.getTreeCode()+"-"+entity.getCatIndex());
		}
		return super.create(entity);
	}
	
	public void save(Category entity) throws Exception {
		if (entity.getParentId() != null) {
			Category parent = r.getOne(entity.getParentId());
			entity.setTreeCode(parent.getTreeCode()+"-"+entity.getCatIndex());
		}else entity.setTreeCode(entity.getCatIndex());
		super.create(entity);
	}
	
	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Category newEntityWithId(String strId) {
		Category entity = new Category();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(Category entity) {
		return null;
	}

	@Override
	public String idAsStr(Category entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}
