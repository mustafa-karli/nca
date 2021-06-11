package com.nauticana.business.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.BusinessContract;
import com.nauticana.business.model.BusinessContractId;
import com.nauticana.business.model.BusinessOwner;

@Service
public class BusinessContractService extends AbstractService<BusinessContract, BusinessContractId> {

	@Autowired
	BusinessOwnerService parentService;

	@Override
	@Transactional
	public BusinessContract newEntityWithParentId(String parentKey) {
		BusinessContract entity = new BusinessContract();
		int key = Integer.parseInt(parentKey);
		BusinessContractId id = new BusinessContractId();
		id.setBusinessPartnerId(key);
		id.setBegda(Utils.onlyDate());
		entity.setId(id);
		entity.setBusinessOwner(parentService.findById(key));
		return entity;
	}

	@Override
	public BusinessContractId strToId(String id) {
		return new BusinessContractId(id);
	}

	@Override
	public BusinessContract newEntityWithId(String strId) {
		BusinessContract entity = new BusinessContract();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(BusinessContract entity) {
		if (entity == null) return null;
		return BusinessOwner.ROOTMAPPING + "/show?id=" + entity.getId().getBusinessPartnerId();
	}

	@Override
	public String idAsStr(BusinessContract entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}

}

