package com.nauticana.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.business.repository.BusinessJdbcRepository;

@Service
public class BusinessJdbcService {

	@Autowired
	private BusinessJdbcRepository		r;

	public int addVendor(int partnerId, String approved) {
		return r.addVendor(partnerId, approved);
	}

	public int addCustomer(int partnerId) {
		return r.addCustomer(partnerId);
	}

	public int addBusinessOwner(int partnerId) {
		return r.addBusinessOwner(partnerId);
	}

	public int addSubcontractor(int partnerId) {
		return r.addSubcontractor(partnerId);
	}

	public String[] partnerDates(int partnerId) {
		return r.partnerDates(partnerId);
	}
	
	public List<String[]> vendorBusinesses(int partnerId) {
		return r.vendorBusinesses(partnerId);
	}

	public void addVendorBusiness(int partnerId, int nodeId) {
		r.addVendorBusiness(partnerId, nodeId);
	}

	public void deleteVendorBusiness(int partnerId, int nodeId) {
		r.deleteVendorBusiness(partnerId, nodeId);
	}

	public double getPartnerCredit(int partnerId, String creditType) {
		return r.getPartnerCredit(partnerId, creditType);
	}

	public void addPartnerCredit(int partnerId, String creditType, double credit) throws Exception {
		r.addPartnerCredit(partnerId, creditType, credit);
	}

	public void decPartnerCredit(int partnerId, String creditType, double credit) throws Exception {
		r.decPartnerCredit(partnerId, creditType, credit);
	}

}
