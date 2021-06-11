package com.nauticana.business.controller;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.utils.ControllerStatic;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.BusinessPartner;
import com.nauticana.business.model.PartnerAddress;
import com.nauticana.business.model.PartnerAddressId;
import com.nauticana.business.service.BusinessJdbcService;
import com.nauticana.business.service.PartnerAddressService;
import com.nauticana.finance.model.Bank;
import com.nauticana.finance.model.BankAccount;
import com.nauticana.finance.model.BankBranch;
import com.nauticana.finance.service.BankAccountService;
import com.nauticana.finance.service.BankBranchService;
import com.nauticana.finance.service.BankService;
import com.nauticana.personnel.controller.EmployeeController;
import com.nauticana.personnel.model.ViewEmployeeUser;

@Controller
@ResponseBody
@RequestMapping("/businessPartner")
public class BusinessPartnerController extends AbstractController<BusinessPartner, Integer> {

	@Autowired
	BusinessJdbcService businessJdbcService;
	
    @Autowired
    protected PartnerAddressService						partnerAddressService;

    @Autowired
    protected BankAccountService						bankAccountService;

    @Autowired
    protected BankBranchService							bankBranchService;

    @Autowired
    protected BankService								bankService;

    @Autowired
    protected EmployeeController						employeeController;

    @Override
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editGet(HttpServletRequest request) {
    	int id;
    	try {
    		id = Integer.parseInt(request.getParameter("id"));
    		if (id < 1) return newGet(request);
        	ModelAndView model = super.editGet(request);
        	model.addObject("dates", businessJdbcService.partnerDates(id));
        	model.addObject("accounts", bankAccountService.findAll(id));
            model.addObject("addressTypes", dataCache.getDomainOptions("ADDRESS_TYPE"));
            model.addObject("countries", dataCache.getLookupOptions("COUNTRY", -1));
            model.addObject("genders", dataCache.getDomainOptions("GENDER"));
            model.addObject("businessTree", basisService.treeData("SELECT NODE_ID, CAPTION, PARENT_ID FROM TREE_DATA WHERE PURPOSE='VENDOR_BUSINESS'"));// AND OWNER_ID=" + getClient(request.getSession(true))));
           	model.addObject("vendorBusiness", businessJdbcService.vendorBusinesses(id));
            return model;
    	} catch (Exception e) {
    		return newGet(request);
    	}
    }

	@Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newGet(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
        String parentKey = request.getParameter(Labels.PARENTKEY);
        BusinessPartner record = modelService.newEntityWithParentId(parentKey);
        ControllerStatic controllerStatic = getControllerStatic();
        ModelAndView model = editView(controllerStatic, language);

        model.addObject("record", record);
        model.addObject("INPUTMODE", "NEW");

        model.addObject(Labels.PAGETITLE, language.getText(tableName()));
        model.addObject(Labels.PREVPAGE, prevPage(record));
        model.addObject(Labels.POSTLINK, getControllerStatic().getRootMapping() + "/new");
        model.addObject(Labels.SELECT_OPTIONS, getSelectOptions(language, -1, controllerStatic));
        model.addObject("addressTypes", dataCache.getDomainOptions("ADDRESS_TYPE", null));
        model.addObject("countries", dataCache.getLookupOptions("COUNTRY", -1));
        model.addObject("genders", dataCache.getDomainOptions("GENDER"));
        model.addObject("businessTree", basisService.treeData("SELECT NODE_ID, CAPTION, PARENT_ID FROM TREE_DATA WHERE PURPOSE='VENDOR_BUSINESS' AND OWNER_ID=" + getClient(request.getSession(true))));
        model.addObject("newPerson", "X");
    	return model;
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newPost(@ModelAttribute BusinessPartner record, BindingResult result, HttpServletRequest request) {
    	return editPost(record, result, request);
    }
	
    @Override
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editPost(@ModelAttribute BusinessPartner record, BindingResult result, HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/userAccount/login");

        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());
        String nextpage = request.getParameter(Labels.NEXTPAGE);

        int id;
        try {
        	id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		String caption = request.getParameter("caption");
		String webAddress = request.getParameter("webAddress");
		String hqCountry = request.getParameter("hqCountry");
		String taxCenter = request.getParameter("taxCenter");
		String taxNumber = request.getParameter("taxNumber");
		String adr = request.getParameter("addressId");
		String adr1 = request.getParameter("firstAddressId");
		short addressId = Short.parseShort(adr);
		short addressId1 = Short.parseShort(adr1);
		String[][] addressStr = null;
		if (addressId-addressId1 > 0) {
			addressStr = new String[addressId-addressId1][6];
			for (short i = 0; i < addressStr.length; i++) {
				addressStr[i][0] = request.getParameter("addressType"+(i+addressId1));
				addressStr[i][1] = request.getParameter("street"+(i+addressId1));
				addressStr[i][2] = request.getParameter("city"+(i+addressId1));
				addressStr[i][3] = request.getParameter("state"+(i+addressId1));
				addressStr[i][4] = request.getParameter("country"+(i+addressId1));
				addressStr[i][5] = request.getParameter("phone"+(i+addressId1));
			}
		}
		
		String[][] newVendorBusiness = null;
		try {
			short vbCount = Short.parseShort(request.getParameter("vendorBusinessCount"));
			newVendorBusiness = new String[vbCount][2];
			for (short i = 0; i < vbCount; i++) {
				newVendorBusiness[i][0] = request.getParameter("vendorBusinessId"+(i+1));
				newVendorBusiness[i][1] = request.getParameter("vendorBusinessTx"+(i+1));
			}
		} catch (Exception e) {
		}
		
		String newPerson = request.getParameter("newPerson");
		
		String[] currencies = new String[3];
		String[] ibans = new String[3];
		String[] swifts = new String[3];
		String[] accountNos = new String[3];
		String[] accountTypes = new String[3];
		
		
		for (int i = 0; i < 3; i++) {
			currencies[i] = request.getParameter("currency"+(i+1));
			ibans[i] = request.getParameter("iban"+(i+1));
			swifts[i] = request.getParameter("swift"+(i+1));
			accountNos[i] = request.getParameter("accountNo"+(i+1));
			accountTypes[i] = request.getParameter("accountType"+(i+1));
		}

		
		ViewEmployeeUser veu = null;
		BusinessPartner partner = modelService.findById(id);
		if (partner == null) {
			partner = new BusinessPartner();
		}
		partner.setCaption(caption);
		partner.setWebAddress(webAddress);
		partner.setHqCountry(hqCountry);
		partner.setTaxCenter(taxCenter);
		partner.setTaxNumber(taxNumber);
			
		if (!Utils.emptyStr(newPerson)) {
			String position = request.getParameter("position");
			String emailAddress = request.getParameter("emailAddress");
			String workPhone = request.getParameter("workPhone");
			String firstName = request.getParameter("firstName");
			String middleName = request.getParameter("middleName");
			String lastName = request.getParameter("lastName");
			Date birthday;
			try {birthday = Labels.dmyDF.parse(request.getParameter("birthday"));} catch (ParseException e) {birthday = null;}
			String gender = request.getParameter("gender");
			String nationality = request.getParameter("nationality");
			String governmentId = request.getParameter("governmentId");
			String personelMail = request.getParameter("personelMail");
			String cellPhone = request.getParameter("cellPhone");
			String newuser = request.getParameter("username").toUpperCase(Locale.ENGLISH);
			String userMail = request.getParameter("userMail");

	        veu = new ViewEmployeeUser(0, Utils.onlyDate(), position, emailAddress, workPhone, firstName, middleName, lastName, birthday, gender, nationality, governmentId, personelMail, cellPhone, newuser, "I", userMail, null, null);
		}
		
		PartnerAddress[] addresses = null;
		if (addressStr != null) {
			addresses = new PartnerAddress[addressStr.length];
			for (short i = 0; i < addressStr.length; i++) {
				if (!Utils.emptyStr(addressStr[i][0]) && !Utils.emptyStr(addressStr[i][1])) {
					addresses[i] = new PartnerAddress();
					addresses[i].setAddressType(addressStr[i][0]);
					addresses[i].setStreet(addressStr[i][1]);
					addresses[i].setCity(addressStr[i][2]);
					addresses[i].setCountry(addressStr[i][3]);
					addresses[i].setState(addressStr[i][4]);
					addresses[i].setPhone(addressStr[i][5]);
				} else
					addresses[i] = null;
			}
		}
		
		try {
			partner = modelService.create(partner);
		} catch (Exception e) {
			ModelAndView res = editGet(request);
			res.addObject("errorText", e.getMessage());
			res.addObject("veu", veu);
			res.addObject("record", partner);
			res.addObject("newVendorBusiness", newVendorBusiness);
			if (!Utils.emptyStr(newPerson)) res.addObject("newPerson", newPerson);
			if (addresses != null) res.addObject("newAddress", addresses);
			return res;
		}

		if (veu != null) try {
			employeeController.addPerson(veu, partner.getId());
			newPerson=null;
		} catch (Exception e) {
			ModelAndView res = editGet(request);
			res.addObject("errorText", e.getMessage());
			res.addObject("veu", veu);
			res.addObject("record", partner);
			res.addObject("newVendorBusiness", newVendorBusiness);
			if (!Utils.emptyStr(newPerson)) res.addObject("newPerson", newPerson);
			if (addresses != null) res.addObject("newAddress", addresses);
			return res;
		}

		if (!Utils.emptyStr(request.getParameter("isBusinessOwner"))) {
			try {businessJdbcService.addBusinessOwner(partner.getId());} catch (Exception e) {}
		}
		
		if (!Utils.emptyStr(request.getParameter("isSubcontractor"))) {
			try {businessJdbcService.addSubcontractor(partner.getId());} catch (Exception e) {}
		}
		
		if (!Utils.emptyStr(request.getParameter("isVendor"))) {
			try {
				businessJdbcService.addVendor(partner.getId(), "N");
				basisService.addUserRole(veu.getUsername(), "MOTIF_VENDOR", new Date(), Utils.maxDate());
			} catch (Exception e) {}
		}
		
		if (!Utils.emptyStr(request.getParameter("isCustomer"))) {
			try {businessJdbcService.addCustomer(partner.getId());} catch (Exception e) {}
		}

		if (addresses != null)
		for (int i = 0; i < addresses.length; i++)
		try {
			addresses[i].setId(new PartnerAddressId(partner.getId(), (short) (addressId1+i)));
			addresses[i].setBusinessPartner(partner);
			addresses[i]=partnerAddressService.create(addresses[i]);
		} catch (Exception e) {
			ModelAndView res = editGet(request);
			res.addObject("errorText", e.getMessage());
			res.addObject("veu", veu);
			res.addObject("record", partner);
			res.addObject("newVendorBusiness", newVendorBusiness);
			if (!Utils.emptyStr(newPerson)) res.addObject("newPerson", newPerson);
			res.addObject("newAddress", addresses);
			return res;
		}
		
		if (newVendorBusiness != null)
		for (int i = 0; i < newVendorBusiness.length; i++)
		try {
			businessJdbcService.addVendorBusiness(partner.getId(), Integer.parseInt(newVendorBusiness[i][0]));
		} catch (Exception e) {
		}
		
		
		for (int i = 0; i < ibans.length; i++) {
			if (!Utils.emptyStr(ibans[i]) && !Utils.emptyStr(accountTypes[i])) {
				BankAccount account = new BankAccount();
				account.setId(ibans[i]);
				account.setAccountNo(accountNos[i]);
				account.setAccountType(accountTypes[i].charAt(0));
				account.setCurrency(currencies[i]);
				account.setOwnerId(partner.getId());
				BankBranch branch = bankBranchService.findById(swifts[i]);
				if (branch == null) {
					branch = new BankBranch();
					branch.setId(swifts[i]);
					branch.setBranchCode((short) 0);
					Bank bank = bankService.findById(swifts[i].substring(0, 8));
					if (bank == null) {
						bank = new Bank();
						bank.setId(swifts[i].substring(0,8));
						bank.setCaption("UNNAMED");
						bank.setCountry(ibans[i].substring(0,2));
						try {
							bank = bankService.create(bank);
						} catch (Exception e) {
							ModelAndView res = editGet(request);
							res.addObject("errorText", e.getMessage());
							res.addObject("veu", veu);
							res.addObject("record", partner);
							if (!Utils.emptyStr(newPerson)) res.addObject("newPerson", newPerson);
							if (addresses != null) res.addObject("newAddress", addresses);
							return res;
						}
					}
					branch.setBank(bank);
					try {
						branch = bankBranchService.create(branch);
					} catch (Exception e) {
						ModelAndView res = editGet(request);
						res.addObject("errorText", e.getMessage());
						res.addObject("veu", veu);
						if (!Utils.emptyStr(newPerson)) res.addObject("newPerson", newPerson);
						if (addresses != null) res.addObject("newAddress", addresses);
						return res;
					}
				}
				account.setBankBranch(branch);
				try {
					account = bankAccountService.create(account);
				} catch (Exception e) {
					ModelAndView res = editGet(request);
					res.addObject("errorText", e.getMessage());
					res.addObject("veu", veu);
					res.addObject("record", partner);
					res.addObject("newVendorBusiness", newVendorBusiness);
					if (!Utils.emptyStr(newPerson)) res.addObject("newPerson", newPerson);
					if (addresses != null) res.addObject("newAddress", addresses);
					return res;
				}
			}
		}
		
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
    }
	
	
}

