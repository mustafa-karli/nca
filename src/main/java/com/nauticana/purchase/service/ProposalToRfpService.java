package com.nauticana.purchase.service;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.ProposalToRfp;
import com.nauticana.purchase.model.ProposalToRfpItem;
import com.nauticana.purchase.model.ProposalToRfpItemId;
import com.nauticana.purchase.model.RequestForProposal;
import com.nauticana.purchase.model.RequestForProposalItem;

@Service
public class ProposalToRfpService extends AbstractService<ProposalToRfp, Integer> {

	@Override
	@Transactional
	public ProposalToRfp newEntityWithParentId(String parentKey) {
		ProposalToRfp entity = new ProposalToRfp();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(ProposalToRfp entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(ProposalToRfp entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public ProposalToRfp newEntityWithId(String strId) {
		ProposalToRfp entity = new ProposalToRfp();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProposalToRfp entity) {
		return null;
	}

	@Override
	public String idAsStr(ProposalToRfp entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<ProposalToRfp> myActive(int client) {
		String sql = "SELECT T.* FROM PROPOSAL_TO_RFP T WHERE T.OWNER_ID=" + client + " AND T.RFP_ID IN (SELECT RFP_ID FROM REQUEST_FOR_PROPOSAL WHERE STATUS = 'G' OR (STATUS='P' AND RFP_ID IN (SELECT RFP_ID FROM RFP_PUBLISHMENT WHERE VENDOR_ID=" + client + "))) ORDER BY RFP_ID";
		Query query = entityManager.createNativeQuery(sql, ProposalToRfp.class);
		return query.getResultList();
	}
	
	public ProposalToRfp newEntity(RequestForProposal rfp, int client) {
		ProposalToRfp entity = new ProposalToRfp();
		entity.setDescription(rfp.getCaption());
		entity.setOwnerId(client);
		entity.setPaymentType("C");
		entity.setRfpId(rfp.getId());
		entity.setShipmentBy("C");
		for (RequestForProposalItem rfpItem : rfp.getRequestForProposalItems()) {
			ProposalToRfpItemId itemId = new ProposalToRfpItemId(entity.getId(), rfpItem.getId().getRfpId(), rfpItem.getId().getLine());
			ProposalToRfpItem item = new ProposalToRfpItem();
			item.setId(itemId);
			item.setMaterialType(rfpItem.getMaterialType());
			item.setMaterial(rfpItem.getMaterial());
			item.setDiscountPct((short) 0);
			item.setOwnerId(client);
			item.setProposalToRfp(entity);
			item.setQuantity(rfpItem.getQuantity());
			item.setRequestForProposalItem(rfpItem);
			item.setTaxPct(18);
			item.setUnit(rfpItem.getUnit());
			item.setUnitPrice(new BigDecimal(0));
			item.setCurrency("TRY");
			entity.getProposalToRfpItems().add(item);
		}
		return entity;
	}

}

