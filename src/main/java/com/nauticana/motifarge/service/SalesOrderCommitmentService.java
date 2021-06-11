package com.nauticana.motifarge.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.motifarge.model.ProductPriceCommitmentId;
import com.nauticana.motifarge.model.SalesOrderCommitment;
import com.nauticana.motifarge.model.SalesOrderCommitmentId;

@Service
public class SalesOrderCommitmentService extends AbstractService<SalesOrderCommitment, SalesOrderCommitmentId> {

	@Autowired
	ProductPriceCommitmentService parentService;

	@Override
	@Transactional
	public SalesOrderCommitment newEntityWithParentId(String parentKey) {
		SalesOrderCommitment entity = new SalesOrderCommitment();
		entity.setId(new SalesOrderCommitmentId());
		ProductPriceCommitmentId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId.getMaterialId());
		entity.getId().setBusinessPartnerId(parentId.getBusinessPartnerId());
		entity.getId().setOrderDeadLine(parentId.getOrderDeadLine());
		entity.setProductPriceCommitment(parentService.findById(parentId));
		return entity;
	}

	@Override
	public SalesOrderCommitmentId strToId(String id) {
		return new SalesOrderCommitmentId(id);
	}

	@Override
	public SalesOrderCommitment newEntityWithId(String strId) {
		SalesOrderCommitment entity = new SalesOrderCommitment();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SalesOrderCommitment entity) {
		if (entity == null) return null;
		return "productPriceCommitment/show?id=" + entity.getProductPriceCommitment().getId().toString();
	}

	@Override
	public String idAsStr(SalesOrderCommitment entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

