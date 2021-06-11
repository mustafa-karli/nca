package com.nauticana.motifarge.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.motifarge.model.ProductPriceCommitmentId;
import com.nauticana.motifarge.model.ProductPriceCommitmentItem;
import com.nauticana.motifarge.model.ProductPriceCommitmentItemId;

@Service
public class ProductPriceCommitmentItemService extends AbstractService<ProductPriceCommitmentItem, ProductPriceCommitmentItemId> {

	@Autowired
	ProductPriceCommitmentService parentService;

	@Override
	@Transactional
	public ProductPriceCommitmentItem newEntityWithParentId(String parentKey) {
		ProductPriceCommitmentItem entity = new ProductPriceCommitmentItem();
		entity.setId(new ProductPriceCommitmentItemId());
		ProductPriceCommitmentId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId.getMaterialId());
		entity.getId().setBusinessPartnerId(parentId.getBusinessPartnerId());
		entity.getId().setOrderDeadLine(parentId.getOrderDeadLine());
		entity.setProductPriceCommitment(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ProductPriceCommitmentItemId strToId(String id) {
		return new ProductPriceCommitmentItemId(id);
	}

	@Override
	public ProductPriceCommitmentItem newEntityWithId(String strId) {
		ProductPriceCommitmentItem entity = new ProductPriceCommitmentItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductPriceCommitmentItem entity) {
		if (entity == null) return null;
		if (entity.getProductPriceCommitment() == null) return null;
		return "productPriceCommitment/show?id=" + entity.getProductPriceCommitment().getId().toString();
	}

	@Override
	public String idAsStr(ProductPriceCommitmentItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

