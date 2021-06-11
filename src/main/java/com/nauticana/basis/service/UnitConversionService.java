package com.nauticana.basis.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.UnitConversion;
import com.nauticana.basis.model.UnitConversionId;


@Service
public class UnitConversionService extends AbstractService<UnitConversion, UnitConversionId> {

	@Override
	@Transactional
	public UnitConversion newEntityWithParentId(String parentKey) {
		return new UnitConversion();
	}

	@Override
	public UnitConversionId strToId(String id) {
		return new UnitConversionId(id);
	}

	@Override
	public UnitConversion newEntityWithId(String strId) {
		UnitConversion entity = new UnitConversion();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(UnitConversion entity) {
		return null;
	}

	@Override
	public String idAsStr(UnitConversion entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}

}
