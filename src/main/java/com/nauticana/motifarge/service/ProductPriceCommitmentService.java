package com.nauticana.motifarge.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.motifarge.model.ProductPriceCommitment;
import com.nauticana.motifarge.model.ProductPriceCommitmentId;

@Service
public class ProductPriceCommitmentService extends AbstractService<ProductPriceCommitment, ProductPriceCommitmentId> {

	@Override
	@Transactional
	public ProductPriceCommitment newEntityWithParentId(String parentKey) {
		ProductPriceCommitment entity = new ProductPriceCommitment();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(ProductPriceCommitment entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(ProductPriceCommitment entity) {
		return entity.getOwnerId();
	};

	@Override
	public ProductPriceCommitmentId strToId(String id) {
		return new ProductPriceCommitmentId(id);
	}

	@Override
	public ProductPriceCommitment newEntityWithId(String strId) {
		ProductPriceCommitment entity = new ProductPriceCommitment();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductPriceCommitment entity) {
		return null;
	}

	@Override
	public String idAsStr(ProductPriceCommitment entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

