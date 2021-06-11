package com.nauticana.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.CategoryText;
import com.nauticana.project.model.CategoryTextId;

@Service
public class CategoryTextService extends AbstractService<CategoryText,CategoryTextId> {

	@Override
	@Transactional
	public CategoryText newEntityWithParentId(String parentKey) {
		CategoryText entity = new CategoryText();
		if (!Utils.emptyStr(parentKey)) {
			CategoryTextId id = new CategoryTextId(parentKey);
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public CategoryTextId strToId(String id) {
		String[] s = id.split(",");
		return new CategoryTextId(Integer.parseInt(s[0]), s[1]);
	}

	@Override
	public CategoryText newEntityWithId(String strId) {
		CategoryText entity = new CategoryText();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(CategoryText entity) {
		return null;
	}

	@Override
	public String idAsStr(CategoryText entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
