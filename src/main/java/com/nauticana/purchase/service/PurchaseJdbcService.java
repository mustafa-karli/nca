package com.nauticana.purchase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.model.UserAccount;
import com.nauticana.basis.model.ViewTreeData;
import com.nauticana.basis.service.UserAccountService;
import com.nauticana.business.model.BusinessPartner;
import com.nauticana.business.service.BusinessPartnerService;
import com.nauticana.purchase.model.ProposalToRfp;
import com.nauticana.purchase.model.RequestForProposal;
import com.nauticana.purchase.repository.PurchaseJbdcRepository;

@Service
public class PurchaseJdbcService {

	@Autowired
	private PurchaseJbdcRepository		r;

	@Autowired
	private ProposalToRfpService		proposalToRfpService;

	@Autowired
	private BusinessPartnerService		businessPartnerService;

	@Autowired
	private UserAccountService			userAccountService;

    public List<ViewTreeData> purchaseGroups() {
    	return r.purchaseGroups();
    }
	
    public Object[] rfpProposalChart(int rfpId) {
    	Object[] o = r.rfpProposalChart(rfpId);
    	@SuppressWarnings("unchecked")
		ArrayList<Integer> n = (ArrayList<Integer>) o[0];
    	ProposalToRfp[] p = new ProposalToRfp[n.size()];
    	BusinessPartner[] b = new BusinessPartner[n.size()];
    	UserAccount[] u = new UserAccount[n.size()];
    	
    	for (int i = 5; i < n.size(); i++) {
			p[i] = proposalToRfpService.findById(n.get(i));
			b[i] = businessPartnerService.findById(p[i].getOwnerId());
			u[i] = userAccountService.findById(p[i].getUsername());
		}
    	return new Object[] {p, b, u, o[1]};
    }

	public int addDeliveryAddress(int purchaseOrderId, int businessPartnerId, short addressId) {
		return r.addDeliveryAddress(purchaseOrderId, businessPartnerId, addressId);
	}

	public int addPurchaseReason(int purchaseOrderId, short line, String reasonType, int reasonId, short reasonLine, Date begda) {
		return r.addPurchaseReason(purchaseOrderId, line, reasonType, reasonId, reasonLine, begda);
	}

	public void publishRfp(RequestForProposal rfp, String[] vendors) {
		for (String vendor : vendors) {
			r.publishRfp(rfp.getId(), Integer.parseInt(vendor));
		}
	}
	
	public int completePurchaseReason(char reasonType, int reasonId) {
		return r.completePurchaseReason(reasonType, reasonId);
	}

	public int addPurchaseRef(char refType, int refId, int orderId) {
		return r.addPurchaseRef(refType, refId, orderId);
	}
	
	public List<int[]> purchaseByReason(String reasonTtype, int reasonId) {
		return r.purchaseByReason(reasonTtype, reasonId);
	}

	public List<String[]> allPublishedByCity(int client, String country, String state, String city) {
		return r.allPublishedByCity(client, country, state, city);
	}
}
