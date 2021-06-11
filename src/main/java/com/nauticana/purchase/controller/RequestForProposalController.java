package com.nauticana.purchase.controller;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.ContentRelation;
import com.nauticana.basis.utils.ControllerStatic;
import com.nauticana.basis.utils.ControllerStatic.TableContentType;
import com.nauticana.basis.utils.ControllerStatic.TableNavAction;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.BusinessPartner;
import com.nauticana.business.model.PartnerAddress;
import com.nauticana.business.model.PartnerAddressId;
import com.nauticana.business.service.BusinessPartnerService;
import com.nauticana.business.service.PartnerAddressService;
import com.nauticana.business.service.VendorService;
import com.nauticana.material.model.Manufacturer;
import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialType;
import com.nauticana.material.service.ManufacturerService;
import com.nauticana.material.service.MaterialService;
import com.nauticana.material.service.MaterialTypeService;
import com.nauticana.purchase.model.ProposalToRfp;
import com.nauticana.purchase.model.ProposalToRfpItem;
import com.nauticana.purchase.model.ProposalToRfpItemId;
import com.nauticana.purchase.model.RequestForProposal;
import com.nauticana.purchase.model.RequestForProposalItem;
import com.nauticana.purchase.model.RequestForProposalItemId;
import com.nauticana.purchase.service.ProposalToRfpItemService;
import com.nauticana.purchase.service.ProposalToRfpService;
import com.nauticana.purchase.service.PurchaseJdbcService;
import com.nauticana.purchase.service.RequestForProposalItemService;
import com.nauticana.purchase.service.RequestForProposalService;

@Controller
@ResponseBody
@RequestMapping("/requestForProposal")
public class RequestForProposalController extends AbstractController<RequestForProposal, Integer> {
	
	@Autowired
	RequestForProposalItemService	requestForProposalItemService;
	
	@Autowired
	PurchaseJdbcService				purchaseJdbcService;

	@Autowired
	ProposalToRfpService			proposalToRfpService;
	
	@Autowired
	ProposalToRfpItemService		proposalToRfpItemService;

	@Autowired
	MaterialTypeService				materialTypeService;

	@Autowired
	MaterialService					materialService;

	@Autowired
	ManufacturerService				manufacturerService;
	
	@Autowired
	VendorService					vendorService;

	@Autowired
	BusinessPartnerService			businessPartnerService;
	
	@Autowired
	PartnerAddressService			partnerAddressService;
	
	@RequestMapping(value = "/fastEntry", method = RequestMethod.GET)
	public ModelAndView fastEntryGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());
		int client=getClient(session);
		
		ModelAndView model = new ModelAndView("purchase/rfpEntry");
		RequestForProposal record = new RequestForProposal();
		record.setConsortiumAllowed("N");
		record.setPartialAllowed("N");
		model.addObject("record", record);
		model.addObject("language", language);
        model.addObject("MEASUREMENT_UNIT", dataCache.getDomainOptions("MEASUREMENT_UNIT", language));
        model.addObject("YESNO", dataCache.getDomainOptions("YESNO", language));
        model.addObject("PARTNER_ADDRESS", dataCache.getLookupOptions("PARTNER_ADDRESS", client));
        model.addObject("client", client);
        model.addObject("businessTree", basisService.treeData("SELECT NODE_ID, CAPTION, PARENT_ID FROM TREE_DATA WHERE PURPOSE='VENDOR_BUSINESS' AND OWNER_ID=" + getClient(request.getSession(true))));
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.PREVPAGE, "requestForProposal/initial");
		model.addObject(Labels.POSTLINK, "requestForProposal/fastEntry");
		return model;
	}
	
	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.UPDATE + " ON " + tableName());
		int client=getClient(session);
		ModelAndView model = new ModelAndView("purchase/rfpEntry");
		model.addObject("record", modelService.findById(Integer.parseInt(request.getParameter("id"))));
		model.addObject("language", language);
        model.addObject("MEASUREMENT_UNIT", dataCache.getDomainOptions("MEASUREMENT_UNIT", language));
        model.addObject("YESNO", dataCache.getDomainOptions("YESNO", language));
        model.addObject("PARTNER_ADDRESS", dataCache.getLookupOptions("PARTNER_ADDRESS", client));
        model.addObject("client", client);
        model.addObject("businessTree", basisService.treeData("SELECT NODE_ID, CAPTION, PARENT_ID FROM TREE_DATA WHERE PURPOSE='VENDOR_BUSINESS' AND OWNER_ID=" + getClient(request.getSession(true))));
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.PREVPAGE, "requestForProposal/initial");
		model.addObject(Labels.POSTLINK, "requestForProposal/fastEntry");
		return model;
	}
	
    @RequestMapping(value = "/fastEntry", method = RequestMethod.POST)
    public ModelAndView fastEntryPost(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/userAccount/login");
		int client = getClient(session);
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());
        String nextpage = request.getParameter(Labels.NEXTPAGE);

		try {
			String caption = request.getParameter("caption");
			Date requestDate = Utils.onlyDate(); //Labels.dmyDF.parse(request.getParameter("requestDate"));
			Date endOfProposal = Labels.dmyDF.parse(request.getParameter("endOfProposal"));
			Date deliveryDate = Labels.dmyDF.parse(request.getParameter("deliveryDate"));
			String consortiumAllowed = request.getParameter("consortiumAllowed");
			String partialAllowed = request.getParameter("partialAllowed");
			int purchaseArea = Integer.parseInt(request.getParameter("purchaseArea"));
			short deliveryAddressId = Short.parseShort(request.getParameter("deliveryAddressId"));
			short itemCount = Short.parseShort(request.getParameter("itemCount"));
			short lastLine = Short.parseShort(request.getParameter("lastLine"));
			PartnerAddress deliveryAddress = partnerAddressService.findById(new PartnerAddressId(client, deliveryAddressId));

			String[] materialTypeIds = new String[itemCount];
			String[] partNumbers     = new String[itemCount];
			String[] specifications  = new String[itemCount];
			String[] manufacturerIds = new String[itemCount];
			String[] units           = new String[itemCount];
			int[]    quantities      = new int[20];
			
			for (int i = lastLine+1; i < itemCount+1; i++) {
				materialTypeIds[i-1] = request.getParameter("materialType"+i);
				partNumbers[i-1] = request.getParameter("partNumber"+i);
				specifications[i-1] = request.getParameter("specification"+i);
				manufacturerIds[i-1] = request.getParameter("manufacturer"+i);
				units[i-1] = request.getParameter("unit"+i);
				try {
					quantities[i-1] = Integer.parseInt(request.getParameter("quantity"+i));
				} catch (Exception e) {
					quantities[i-1] = 0;
				}
			}
			

			MaterialType[] materialTypes = new MaterialType[20];
			Material[] materials = new Material[20];
			Manufacturer[] manufacturers = new Manufacturer[20];
			
			for (int j = 0; j < partNumbers.length; j++) {
				if (!Utils.emptyStr(materialTypeIds[j]))
				try {
					materialTypes[j] = materialTypeService.findById(materialTypeIds[j]);
					if (materialTypes[j] == null) {
						MaterialType mt = new MaterialType();
						mt.setId(materialTypeIds[j]);
						mt.setCaption(materialTypeIds[j]);
						mt.setUnit(units[j]);
						materialTypes[j] = materialTypeService.create(mt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} else
					materialTypes[j] = null;
					
				if (!Utils.emptyStr(manufacturerIds[j]))
				try {
					manufacturers[j] = manufacturerService.findById(manufacturerIds[j]);
					if (manufacturers[j] == null) {
						Manufacturer mn = new Manufacturer();
						mn.setId(manufacturerIds[j]);
						mn.setCaption(manufacturerIds[j]);
						manufacturers[j] = manufacturerService.create(mn);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} else
					manufacturers[j] = null;
				
				if (!Utils.emptyStr(partNumbers[j]))
				try {
					materials[j] = materialService.checkMaterialByPart(client, manufacturers[j], materialTypes[j], partNumbers[j], units[j]);
				} catch (Exception e) {
					e.printStackTrace();
				} else
					materials[j] = null;

				if (!Utils.emptyStr(specifications[j]))
				try {
					materials[j] = materialService.checkMaterialByCaption(client, manufacturers[j], materialTypes[j], specifications[j], units[j]);
				} catch (Exception e) {
					e.printStackTrace();
				} else
					materials[j] = null;
			}
			
			RequestForProposal rfp = null;
			try {
				rfp = modelService.findById(Integer.parseInt(request.getParameter("id")));
				if (rfp == null)
					rfp = new RequestForProposal();
				rfp.setPurchaseArea(purchaseArea);
				rfp.setUsername(username);
				rfp.setCaption(caption);
				rfp.setConsortiumAllowed(consortiumAllowed);
				rfp.setDeliveryDate(deliveryDate);
				rfp.setEndOfProposal(endOfProposal);;
//				rfp.setOwnerId(client);
				rfp.setPartialAllowed(partialAllowed);
				rfp.setRequestDate(requestDate);
				rfp.setDeliveryAddress(deliveryAddress);
				rfp.setStatus("I");
				rfp = modelService.create(rfp);
			
				for (int i = lastLine; i < itemCount; i++)
				if (materialTypes[i] != null || materials[i] != null) {
					RequestForProposalItemId id = new RequestForProposalItemId(rfp.getId(), (short) (i+1));
					RequestForProposalItem rfpItem = new RequestForProposalItem();
					rfpItem.setId(id);
					rfpItem.setRequestForProposal(rfp);
					rfpItem.setMaterialType(materialTypes[i]);
					rfpItem.setMaterial(materials[i]);
					rfpItem.setManufacturer(manufacturers[i]);
					rfpItem.setSpecification(specifications[i]);
					rfpItem.setQuantity(new BigDecimal(quantities[i]));
					rfpItem.setUnit(units[i]);
					rfpItem = requestForProposalItemService.create(rfpItem);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
			}
			if (Utils.emptyStr(nextpage))
				nextpage = modelService.parentLink(rfp);
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_WRONGDATA, e.getMessage());
		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
    }

	@RequestMapping(value = "/initial", method = RequestMethod.GET)
	public ModelAndView initialGet(HttpServletRequest request) {
		return statusGet(request, "I", "purchase/rfpGuestView");
	}

	@RequestMapping(value = "/allPublished", method = RequestMethod.GET)
	public ModelAndView allPublishedGet(HttpServletRequest request) {
		return statusGet(request, "G", "purchase/rfpGuestView");
	}

	@RequestMapping(value = "/myPublished", method = RequestMethod.GET)
	public ModelAndView myPublishedGet(HttpServletRequest request) {
		return statusGet(request, "P", "purchase/rfpGuestView");
	}

	@RequestMapping(value = "/allPublishedMap", method = RequestMethod.GET)
	public ModelAndView allPublishedMapGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		int client = getClient(session);
		if (client < 1) client = 3;

//		String username = (String) session.getAttribute(Labels.USERNAME);
//		if (Utils.emptyStr(username))
//			return new ModelAndView("redirect:/userAccount/login");
//		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
//		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
//			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
//		int client = getClient(session);

        List<String[]> records = purchaseJdbcService.allPublishedByCity(client, "TR", null, null);
        ModelAndView model = new ModelAndView("purchase/rfpMap");
        model.addObject("records", records);
        model.addObject("client", client);
        model.addObject("language", language);

        return model;
	}

	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	public ModelAndView completeGet(HttpServletRequest request) {
		return statusGet(request, "C", "purchase/rfpGuestView");
	}

	private ModelAndView statusGet(HttpServletRequest request, String status, String view) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return null;
//			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
		int client = getClient(session);
        ControllerStatic controllerStatic = getControllerStatic();

        List<RequestForProposal> records;
        switch (status.charAt(0)) {
			case 'I': records = ((RequestForProposalService) modelService).myInitial(client); break;
			case 'G': records = ((RequestForProposalService) modelService).allPublished(client); break;
			case 'P': records = ((RequestForProposalService) modelService).myPublished(client); break;
			case 'C': records = ((RequestForProposalService) modelService).myComplete(client); break;
			default: records = null; break;
		}

        @SuppressWarnings("unchecked")
		List<ContentRelation>[] binaryContents = new ArrayList[records.size()];
		@SuppressWarnings("unchecked")
		List<int[]>[] purchaseReasons = new ArrayList[records.size()];
		BusinessPartner[] partners = new BusinessPartner[records.size()];

		int i = 0;
		for (RequestForProposal record : records) {
			partners[i] = businessPartnerService.findById(record.getDeliveryAddress().getId().getBusinessPartnerId());
			binaryContents[i] = null;
	        for (TableContentType contentType : controllerStatic.getContentTypes()) {
	            try {
	                List<ContentRelation> list = contentRelationService.findByOtype(getClient(session), contentType.objectType, record.getId()+"");
	                if (binaryContents[i] == null)
	                	binaryContents[i] = list;
	                else
	                	binaryContents[i].addAll(list);
	            } catch (Exception e) {
	            }
	        }
	        if (status.charAt(0) == 'C')
	        	purchaseReasons[i] = purchaseJdbcService.purchaseByReason("R", record.getId());
			i++;
		}
        ModelAndView model = new ModelAndView(view);
        model.addObject("records", records);
        model.addObject("partners", partners);
        model.addObject("client", client);
        model.addObject("controller", controllerStatic);
        model.addObject("language", language);
        model.addObject("binaryContents", binaryContents);
        model.addObject("purchaseReasons", purchaseReasons);
        
        if (status.charAt(0) == 'I') {
            model.addObject("vendors", vendorService.findAll(client));
        }

        if (status.charAt(0) == 'G') {
        	ProposalToRfp[] prfps = new ProposalToRfp[records.size()];
        	List<ProposalToRfp> list = proposalToRfpService.myActive(client);
        	i = 0;
        	for (RequestForProposal rfp : records) {
        		prfps[i] = null;
        		for (ProposalToRfp prfp : list) {
        			if (rfp.getId() == prfp.getRfpId()) prfps[i] = prfp;
        		}
        		i++;
        	}
            model.addObject("proposals", prfps);
        }

        model.addObject(Labels.DOMAIN_OPTIONS, getDomainOptions(language, client, controllerStatic));

        String tableName = tableName();
        
        if (basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName))
            model.addObject(Labels.INSERT_ALLOWED, "X");
        if (basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName))
            model.addObject(Labels.UPDATE_ALLOWED, "X");
        if (basisService.authorityChk(username, Labels.TABLE, Labels.DELETE, tableName))
            model.addObject(Labels.DELETE_ALLOWED, "X");

		if (controllerStatic.getActions() != null) {
			byte[] actAuth = new byte[controllerStatic.getActions().length];
			for (i = 0; i < actAuth.length; i++) {
				TableNavAction a = controllerStatic.getActions()[i];
				if (a.enable.charAt(0) == 'Y') {
					if (a.authorityCheck.charAt(0) == 'N')
						actAuth[i] = 1;
					else {
						if (basisService.authorityChk(username, tableName, a.action))
							actAuth[i] = 1;
						else
							actAuth[i] = 0;
					}
				} else {
					actAuth[i] = 0;
				}
			}
			model.addObject("ACTIONS_ALLOWED", actAuth);
		}
        model.addObject(Labels.PAGETITLE, language.getText(tableName));
        return model;
    }

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	private ModelAndView publishGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
	
        try {
            RequestForProposal rfp = modelService.findById(Integer.parseInt(request.getParameter("id")));
    		if (!basisService.authorityChk(username, Labels.REQUEST_FOR_PROPOSAL, Labels.PUBLISH, rfp.getId()+""))
    			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.REQUEST_FOR_PROPOSAL + "-" + Labels.PUBLISH + " ON " + rfp.getId());
            rfp.setStatus("P");
			modelService.save(rfp);
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		return statusGet(request, "I", "purchase/rfpGuestView");
    }
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	private ModelAndView publishPost(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
	
		String idStr = request.getParameter("id");
        String[] vc = request.getParameterValues("vendor"+idStr);
        if (vc.length > 0)
        try {
            RequestForProposal rfp = modelService.findById(Integer.parseInt(idStr));
    		if (!basisService.authorityChk(username, Labels.REQUEST_FOR_PROPOSAL, Labels.PUBLISH, rfp.getId()+""))
    			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.REQUEST_FOR_PROPOSAL + "-" + Labels.PUBLISH + " ON " + rfp.getId());
    		if (vc[0].equals("0")) {
    			rfp.setStatus("G");
    		} else {
    			purchaseJdbcService.publishRfp(rfp, vc);
    			rfp.setStatus("P");
    		}
			modelService.save(rfp);
			if (rfp.getStatus().equals("G")) {
				for (BusinessPartner bp : ((RequestForProposalService)modelService).publishedVendors(rfp.getId())) {
					basisService.notifyUser(bp.getId(), tableName(), rfp.getId()+"", rfp.getEndOfProposal(), language.getText("NEW") + " " + language.getText(tableName()) + " " + rfp.getId());
				}
			} else {
				for (BusinessPartner bp : ((RequestForProposalService)modelService).globalVendors(rfp.getId())) {
					basisService.notifyUser(bp.getId(), tableName(), rfp.getId()+"", rfp.getEndOfProposal(), language.getText("NEW") + " " + language.getText(tableName()) + " " + rfp.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		return statusGet(request, "I", "purchase/rfpGuestView");
    }
	
	@RequestMapping(value = "/propose", method = RequestMethod.GET)
	public ModelAndView proposeGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
	
        try {
        	int id = Integer.parseInt(request.getParameter("id"));
    		if (!basisService.authorityChk(username, Labels.REQUEST_FOR_PROPOSAL, Labels.PROPOSE, id+""))
    			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.REQUEST_FOR_PROPOSAL + "-" + Labels.PROPOSE + " ON " + id);
            RequestForProposal rfp = modelService.findById(id);
            ModelAndView model = new ModelAndView("purchase/proposalEntry");
            model.addObject("record", proposalToRfpService.newEntity(rfp, getClient(session)));
            model.addObject("rfp", rfp);
    		model.addObject("language", language);
    		model.addObject(Labels.PREVPAGE, "requestForProposal/allPublished");
    		model.addObject(Labels.POSTLINK, "requestForProposal/propose");
            model.addObject("EXCHANGE", dataCache.getDomainOptions("EXCHANGE", language));
            model.addObject("PAYMENT_TYPE", dataCache.getDomainOptions("PAYMENT_TYPE", language));
            model.addObject("SHIPMENT_BY", dataCache.getDomainOptions("SHIPMENT_BY", language));
           return model;
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/propose", method = RequestMethod.POST)
	public ModelAndView proposePost(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/userAccount/login");
		int client = getClient(session);
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        String rfpId = request.getParameter("rfpId");
        
        if (!basisService.authorityChk(username, Labels.REQUEST_FOR_PROPOSAL, Labels.PROPOSE, rfpId ))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.REQUEST_FOR_PROPOSAL + "-" + Labels.PROPOSE + " ON " + rfpId);
        String nextpage = request.getParameter(Labels.NEXTPAGE);

		try {
			String description = request.getParameter("description");
			Date validUntil = Labels.dmyDF.parse(request.getParameter("validUntil"));
//			double totalPrice = Double.parseDouble(request.getParameter("totalPrice").replaceAll(" ", ""));
//			double extraDiscount = Double.parseDouble(request.getParameter("extraDiscount").replaceAll(" ", ""));
//			String currency = request.getParameter("currency");
			String currency = "TRY";
			String shipmentBy = request.getParameter("shipmentBy");
			String paymentType = request.getParameter("paymentType");
			String paymentNote = request.getParameter("paymentNote");
			String deliveryNote = request.getParameter("deliveryNote");
			String totalPrice = request.getParameter("totalPrice");
			RequestForProposal rfp = modelService.findById(Integer.parseInt(rfpId));

			int id = Integer.parseInt(request.getParameter("id"));
			
			ProposalToRfp prfp = proposalToRfpService.findById(id);
			if (prfp == null) prfp = new ProposalToRfp();
			prfp.setCurrency(currency);
			prfp.setDescription(description);
			prfp.setOwnerId(client);
			prfp.setShipmentBy(shipmentBy);
//			prfp.setExtraDiscount(new BigDecimal(extraDiscount));
//			prfp.setTotalPrice(new BigDecimal(totalPrice));
			prfp.setExtraDiscount(new BigDecimal(0));
			prfp.setTotalPrice(totalPrice);
			prfp.setUsername(username);
			prfp.setValidUntil(validUntil);
			prfp.setRfpId(rfp.getId());
			prfp.setPaymentType(paymentType);
			prfp.setPaymentNote(paymentNote);
			prfp.setDeliveryNote(deliveryNote);
			prfp = proposalToRfpService.create(prfp);
			
			for(RequestForProposalItem rfpItem : rfp.getRequestForProposalItems()) {
				double unitPrice;
				short discountPct;
				float taxPct;
				short line = rfpItem.getId().getLine();
				try {
					unitPrice = Double.parseDouble(request.getParameter("unitPrice"+line).replaceAll(" ", ""));
				} catch (Exception e) {
					unitPrice = 0;
				}
				try {
					taxPct = Float.parseFloat(request.getParameter("taxPct"+line).replaceAll(" ", ""));
				} catch (Exception e) {
					taxPct = 0;
				}
				try {
					discountPct = Short.parseShort(request.getParameter("discountPct"+line));
				} catch (Exception e) {
					discountPct = 0;
				}
				if (unitPrice > 0) {
					String itemCurrency = request.getParameter("currency"+line);
					String mfrcName = request.getParameter("manufacturer"+line);
//					String partNumber = request.getParameter("partNumber"+line);
//					String materialId = request.getParameter("materialId"+line);
					String caption = request.getParameter("caption"+line);
					if (Utils.emptyStr(itemCurrency)) itemCurrency = prfp.getCurrency();
					ProposalToRfpItemId itemId = new ProposalToRfpItemId(prfp.getId(), rfpItem.getId().getRfpId(), line);
					ProposalToRfpItem item = new ProposalToRfpItem();
					item.setId(itemId);
					item.setCurrency(itemCurrency);
					item.setMaterialType(rfpItem.getMaterialType());
					Material m;// = rfpItem.getMaterial();
//					if (m == null) {
//						try {
//							m = materialService.findById(Integer.parseInt(materialId));
//						} catch (Exception e) {
					Manufacturer manufacturer;
					if (Utils.emptyStr(mfrcName))
						manufacturer = null;
					else {
						manufacturer = manufacturerService.findById(mfrcName);
						if (manufacturer == null) {
							Manufacturer mn = new Manufacturer();
							mn.setId(mfrcName);
							mn.setCaption(mfrcName);
							manufacturer = manufacturerService.create(mn);
						}
					}
					m = materialService.checkMaterialByCaption(client, manufacturer, item.getMaterialType(), caption, rfpItem.getUnit());
//						}
//					}
					item.setMaterial(m);
					item.setOwnerId(client);
					item.setProposalToRfp(prfp);
					item.setQuantity(rfpItem.getQuantity());
					item.setRequestForProposalItem(rfpItem);
					item.setUnit(rfpItem.getUnit());
					item.setUnitPrice(new BigDecimal(unitPrice));
					item.setDiscountPct(discountPct);
					item.setTaxPct(taxPct);
					item = proposalToRfpItemService.create(item);
				}
			}
			basisService.notifyUser(rfp.getDeliveryAddress().getId().getBusinessPartnerId(), "PROPOSAL_TO_RFP", prfp.getId()+"", prfp.getValidUntil(), language.getText("NEW") + " " + language.getText("PROPOSAL_TO_RFP") + " " + prfp.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}

	@RequestMapping(value = "/proposals", method = RequestMethod.GET)
	public ModelAndView proposalsGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
	
        try {
        	int id = Integer.parseInt(request.getParameter("id"));
    		if (!basisService.authorityChk(username, Labels.REQUEST_FOR_PROPOSAL, Labels.CONTRACT, id+""))
    			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.REQUEST_FOR_PROPOSAL + "-" + Labels.CONTRACT + " ON " + id);
            RequestForProposal rfp = modelService.findById(id);
            Object[] o = purchaseJdbcService.rfpProposalChart(id);
            ModelAndView model = new ModelAndView("purchase/rfpProposals");
            model.addObject("rfp", rfp);
            model.addObject("proposals", o[0]);
            model.addObject("partners", o[1]);
            model.addObject("users", o[2]);
            model.addObject("lines", o[3]);
    		model.addObject("language", language);
    		model.addObject("now", Utils.onlyDate());
    		model.addObject(Labels.PREVPAGE, "requestForProposal/myPublished");
           return model;
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public ModelAndView printGet(HttpServletRequest request) {
        // Check for user and update authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");
        int client = getClient(session);
        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

        // Read data record and assign to model and view object
        String id = request.getParameter("id");
        RequestForProposal rfp = modelService.findById(Integer.parseInt(id));
        if (rfp == null)
            return errorPage(language, Labels.ERR_RECORDNOTFOUND, tableName() + " WITH ID : " + id);
        PartnerAddress deliveryAddress = rfp.getDeliveryAddress();
        BusinessPartner customer = businessPartnerService.findById(deliveryAddress.getId().getBusinessPartnerId());
        BusinessPartner vendor = businessPartnerService.findById(client);

        ControllerStatic controllerStatic = getControllerStatic();
        String view = "purchase/rfpPrint";
        if (Utils.emptyStr(view))
            view = DEFULT_EDIT_VIEW;
        ModelAndView model = new ModelAndView(view);

        model.addObject("rfp", rfp);
        model.addObject("now", Utils.onlyDate());
        model.addObject("customer", customer);
        model.addObject("vendor", vendor);
        model.addObject("deliveryAddress", deliveryAddress);
        model.addObject("customerAddress", customer.getPartnerAddresses().iterator().next());
        model.addObject("vendorAddress", vendor.getPartnerAddresses().iterator().next());
        model.addObject("controller", controllerStatic);
        model.addObject("language", language);
        model.addObject("INPUTMODE", "PRINT");

        // Assign text objects from session language
        model.addObject(Labels.PAGETITLE, language.getText(tableName()));
        model.addObject(Labels.PREVPAGE, prevPage(rfp));
        model.addObject(Labels.POSTLINK, getControllerStatic().getRootMapping() + "/show");
        model.addObject(Labels.SELECT_OPTIONS, getSelectOptions(language, getClient(session), controllerStatic));
        return model;
	}
	
}