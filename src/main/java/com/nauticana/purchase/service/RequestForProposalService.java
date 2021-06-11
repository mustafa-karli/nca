package com.nauticana.purchase.service;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.business.model.BusinessPartner;
import com.nauticana.business.service.PartnerAddressService;
import com.nauticana.purchase.model.RequestForProposal;

@Service
public class RequestForProposalService extends AbstractService<RequestForProposal, Integer> {

	@Autowired
	PartnerAddressService partnerAddressService;

	@Override
	@Transactional
	public RequestForProposal newEntityWithParentId(String parentKey) {
		RequestForProposal entity = new RequestForProposal();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(RequestForProposal entity, int client) {
		if (entity.getDeliveryAddress() == null)
			entity.setDeliveryAddress(partnerAddressService.findAll(client).get(0));
	};

	@Override
	public int getClient(RequestForProposal entity) {
		return entity.getDeliveryAddress().getId().getBusinessPartnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public RequestForProposal newEntityWithId(String strId) {
		RequestForProposal entity = new RequestForProposal();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(RequestForProposal entity) {
		return null;
	}

	@Override
	public String idAsStr(RequestForProposal entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<RequestForProposal> myInitial(int client) {
		String sql = "SELECT T.* FROM REQUEST_FOR_PROPOSAL T WHERE OWNER_ID=" + client + " AND STATUS='I'";
		Query query = entityManager.createNativeQuery(sql, RequestForProposal.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<RequestForProposal> myComplete(int client) {
		String sql = "SELECT T.* FROM REQUEST_FOR_PROPOSAL T WHERE OWNER_ID=" + client + " AND STATUS='C'";
		Query query = entityManager.createNativeQuery(sql, RequestForProposal.class);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<RequestForProposal> allPublished(int client) {
		String sql = 
				"SELECT T.* FROM REQUEST_FOR_PROPOSAL T" +
				" WHERE (T.STATUS = 'G' AND PURCHASE_AREA IN (SELECT NODE_ID FROM VENDOR_BUSINESS WHERE BUSINESS_PARTNER_ID=" + client + "))" +
				"    OR (T.STATUS = 'P' AND T.RFP_ID IN (SELECT RFP_ID FROM RFP_PUBLISHMENT WHERE VENDOR_ID=" + client + "))" + 
				" ORDER BY RFP_ID";
		Query query = entityManager.createNativeQuery(sql, RequestForProposal.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<RequestForProposal> myPublished(int client) {
		String sql = "SELECT T.* FROM REQUEST_FOR_PROPOSAL T WHERE OWNER_ID=" + client + " AND STATUS IN ('P','G')";
		Query query = entityManager.createNativeQuery(sql, RequestForProposal.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BusinessPartner> publishedVendors(int rfpId) {
		String sql = "SELECT B.* FROM BUSINESS_PARTNER B, RFP_PUBLISHMENT P WHERE B.BUSINESS_PARTNER_ID=P.VENDOR_ID AND P.RFP_ID="+ rfpId;
		Query query = entityManager.createNativeQuery(sql, BusinessPartner.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BusinessPartner> unpublishedVendors(int rfpId) {
		String sql = 
				"SELECT B.* FROM BUSINESS_PARTNER B, VENDOR_BUSINESS N, REQUEST_FOR_PROPOSAL R" + 
				" WHERE R.RFP_ID="+ rfpId + 
				"   AND N.BUSINESS_PARTNER NOT IN (SELECT VENDOR_ID FROM RFP_PUBLISHMENT WHERE RFP_ID=R.RFP_ID)" +
				"   AND N.NODE_ID=R.PURCHASE_AREA" + 
				"   AND B.BUSINESS_PARTNER_ID=N.BUSINESS_PARTNER_ID";
		Query query = entityManager.createNativeQuery(sql, BusinessPartner.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BusinessPartner> globalVendors(int rfpId) {
		String sql = 
				"SELECT B.* FROM BUSINESS_PARTNER B, VENDOR_BUSINESS N, REQUEST_FOR_PROPOSAL R" + 
				" WHERE R.RFP_ID="+ rfpId + 
				"   AND N.NODE_ID=R.PURCHASE_AREA" + 
				"   AND B.BUSINESS_PARTNER_ID=N.BUSINESS_PARTNER_ID";
		Query query = entityManager.createNativeQuery(sql, BusinessPartner.class);
		return query.getResultList();
	}
}
