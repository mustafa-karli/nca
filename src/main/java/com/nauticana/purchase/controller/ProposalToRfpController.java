package com.nauticana.purchase.controller;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import com.nauticana.business.service.BusinessPartnerService;
import com.nauticana.material.model.Manufacturer;
import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialType;
import com.nauticana.material.service.ManufacturerService;
import com.nauticana.material.service.MaterialService;
import com.nauticana.material.service.MaterialTypeService;
import com.nauticana.personnel.service.OrganizationService;
import com.nauticana.purchase.model.ProposalToRfp;
import com.nauticana.purchase.model.ProposalToRfpItem;
import com.nauticana.purchase.model.PurchaseOrder;
import com.nauticana.purchase.model.PurchaseOrderItem;
import com.nauticana.purchase.model.PurchaseOrderTax;
import com.nauticana.purchase.model.PurchaseOrderTaxId;
import com.nauticana.purchase.model.RequestForProposal;
import com.nauticana.purchase.service.ProposalToRfpItemService;
import com.nauticana.purchase.service.PurchaseOrderService;
import com.nauticana.purchase.service.RequestForProposalService;

@Controller
@ResponseBody
@RequestMapping("/proposalToRfp")
public class ProposalToRfpController extends AbstractController<ProposalToRfp, Integer> {

	@Autowired
	PurchaseOrderService purchaseOrderService;

	@Autowired
	OrganizationService organizationService;

	@Autowired
	BusinessPartnerService businessPartnerService;
	
	@Autowired
	PurchaseOrderController purchaseOrderController;

	@Autowired
	RequestForProposalService requestForProposalService;
	
	@Autowired
	ProposalToRfpItemService		proposalToRfpItemService;

	@Autowired
	MaterialTypeService				materialTypeService;

	@Autowired
	MaterialService					materialService;

	@Autowired
	ManufacturerService				manufacturerService;
	
//	@Override
//	public ModelAndView editView(ControllerStatic controllerStatic, PortalLanguage language) {
//		return new ModelAndView("purchase/proposalEntry")
//				.addObject("controller", controllerStatic)
//        		.addObject("language", language);
//	}
	
	@Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newGet(HttpServletRequest request) {
    	return null;
    }
	
	@Override
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
	
        try {
        	int id = Integer.parseInt(request.getParameter("id"));
            ProposalToRfp record = modelService.findById(id);
            RequestForProposal rfp = requestForProposalService.findById(record.getRfpId());
            ModelAndView model = new ModelAndView("purchase/proposalEntry");
            model.addObject("record", record);
            model.addObject("rfp", rfp);
    		model.addObject("language", language);
    		model.addObject(Labels.PREVPAGE, "requestForProposal/allPublished");
    		model.addObject(Labels.POSTLINK, "proposalToRfp/edit");
            model.addObject("EXCHANGE", dataCache.getDomainOptions("EXCHANGE", language));
            model.addObject("PAYMENT_TYPE", dataCache.getDomainOptions("PAYMENT_TYPE", language));
            model.addObject("SHIPMENT_BY", dataCache.getDomainOptions("SHIPMENT_BY", language));
           return model;
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
    }
	
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute ProposalToRfp record, BindingResult result, HttpServletRequest request) {

        // Check for user and insert/update authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");
        int client = getClient(session);

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.UPDATE + " ON " + tableName());

        if (result.hasErrors()) {
            String msgText = "";
            for (ObjectError e : result.getAllErrors()) {
                msgText = msgText + "\n" + e.toString();
            }
            return errorPage(language, Labels.ERR_BINDING, msgText);
        }

        // Update record in database
        try {
        	RequestForProposal rfp = requestForProposalService.findById(record.getRfpId());
        	record.setUsername(username);
            record = modelService.create(record);
			basisService.notifyUser(rfp.getDeliveryAddress().getId().getBusinessPartnerId(), "PROPOSAL_TO_RFP", record.getId()+"", record.getValidUntil(), "PROPOSAL TO RFP FOR YOUR RFP IS MODIFIED BY " + username);
        } catch (Exception e) {
            return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
        }
        
        for (ProposalToRfpItem item : record.getProposalToRfpItems()) {
			double unitPrice;
			short discountPct;
			float taxPct;
			short line = item.getId().getLine();
			try {unitPrice = Double.parseDouble(request.getParameter("unitPrice"+line));} catch (Exception e) {unitPrice = 0;}
			try {taxPct = Float.parseFloat(request.getParameter("taxPct"+line).replaceAll(" ", ""));} catch (Exception e) {taxPct = 0;}
			try {discountPct = Short.parseShort(request.getParameter("discountPct"+line));} catch (Exception e) {discountPct = 0;}
			String itemCurrency = request.getParameter("currency"+line);
			String mfrcName = request.getParameter("manufacturer"+line);
//			String partNumber = request.getParameter("partNumber"+line);
			String caption = request.getParameter("caption"+line);
			String materialTypeId = request.getParameter("materialTypeId"+line);
			int materialId = Integer.parseInt(request.getParameter("materialId"+line));
			double quantity = Double.parseDouble(request.getParameter("quantity"+line));
			taxPct = Float.parseFloat(request.getParameter("taxPct"+line));
			String unit = request.getParameter("unit"+line);
			if (item.modified(record.getId(), item.getId().getLine(), materialId, materialTypeId, mfrcName, caption, client, quantity, unit, unitPrice, discountPct, itemCurrency, taxPct)) {
				MaterialType materialType = materialTypeService.findById(materialTypeId);
				Manufacturer manufacturer = manufacturerService.findById(mfrcName);
				if (manufacturer == null) {
					try {
						manufacturer = new Manufacturer();
						manufacturer.setId(mfrcName);
						manufacturer.setCaption(mfrcName);
						manufacturerService.create(manufacturer);
					} catch (Exception e) {
						manufacturer = null;
					}
				}
				Material material;
				try {
					material = materialService.checkMaterialByCaption(client, manufacturer, materialType, caption, unit);
				} catch (Exception e) {
					material = null;
				}
				item.setCurrency(itemCurrency);
				item.setMaterialType(materialType);
				item.setMaterial(material);
				item.setOwnerId(client);
				item.setProposalToRfp(record);
				item.setQuantity(new BigDecimal(quantity));
				item.setUnit(unit);
				item.setUnitPrice(new BigDecimal(unitPrice));
				item.setDiscountPct(discountPct);
				item.setTaxPct(taxPct);
				try {
					proposalToRfpItemService.save(item);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
        
        String nextpage = request.getParameter(Labels.NEXTPAGE);
        if (Utils.emptyStr(nextpage))
            nextpage = modelService.parentLink(record);
        if (Utils.emptyStr(nextpage))
            return new ModelAndView("redirect:/" + getControllerStatic().getRootMapping() + "/list");
        else
            return new ModelAndView("redirect:/" + nextpage);
    }

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView orderGet(HttpServletRequest request) {
        try {
        	String[] curr = {"TRY","EUR","USD","GBP","AUD","CAD"};
        	double[] tots = {0.0,0.0,0.0};
        	int id = Integer.parseInt(request.getParameter("id"));
        	String dueStr = request.getParameter("dueDate");
        	String orderIdStr = request.getParameter("orderId");
        	String rfpIdStr = request.getParameter("rfpId");
        	
            ProposalToRfp prfp = modelService.findById(id);
            PurchaseOrder po = null;
            int rfpId;
            try {rfpId = Integer.parseInt(rfpIdStr);} catch (Exception e) {rfpId = prfp.getRfpId();}
            BusinessPartner vendor = businessPartnerService.findById(prfp.getOwnerId());

            PurchaseOrderItem[] items = null;
			if (Utils.emptyStr(orderIdStr)) {
				po = new PurchaseOrder();
				po.setDueDate(Labels.dmyDF.parse(dueStr));
				po.setOnlineOrder("N");
				po.setStatus("I");
				po.setVendorId(prfp.getOwnerId());
				items = new PurchaseOrderItem[prfp.getProposalToRfpItems().size()];
				for (ProposalToRfpItem pi : prfp.getProposalToRfpItems()) {
					int i = pi.getId().getLine() - 1;
					rfpId = pi.getId().getRfpId();
					items[i] = new PurchaseOrderItem();
					items[i].setCurrency(pi.getCurrency());
					items[i].setMaterial(pi.getMaterial());
					items[i].setQuantity(pi.getQuantity());
					items[i].setStatus("I");
					items[i].setUnit(pi.getUnit());
					items[i].setUnitPrice(new BigDecimal(pi.getUnitPrice().doubleValue() * (100 - pi.getDiscountPct()) / 100));
					double taxAmt = items[i].getUnitPrice().doubleValue() * pi.getQuantity().doubleValue() * pi.getTaxPct() / 100;
					for (int j = 0; j < 3; j++) {
						if (curr[j].equals(pi.getCurrency()))
							tots[j] += items[i].getQuantity().doubleValue() * items[i].getUnitPrice().doubleValue() + taxAmt;
					}
					if (taxAmt > 0) {
						PurchaseOrderTax tax = new PurchaseOrderTax();
						tax.setId(new PurchaseOrderTaxId(0, (short) i, "KDV"));
						tax.setPurchaseOrderItem(items[i]);
						tax.setAmount(new BigDecimal(taxAmt));
						items[i].getPurchaseOrderTaxes().add(tax);
					}
				}
				po.setDiscount(prfp.getExtraDiscount());
			} else {
				po = purchaseOrderService.findById(Integer.parseInt(orderIdStr));
				items = new PurchaseOrderItem[po.getPurchaseOrderItems().size()];
				int i = 0;
				for (PurchaseOrderItem item : po.getPurchaseOrderItems()) {
					items[i] = item;
					i++;
				}
			}

            ModelAndView model = purchaseOrderController.fastEntryGet(request, po, items, vendor, "requestForProposal/proposals?id="+rfpId, "");
            model.addObject("reasonType", "R");
            model.addObject("reasonId", rfpId);
            model.addObject("refType", "P");
            model.addObject("refId", id);
            model.addObject("totalPrice", prfp.getTotalPrice());
            return model;
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(null, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
	}

	
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public ModelAndView printGet(HttpServletRequest request) {
        // Check for user and update authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

        // Read data record and assign to model and view object
        String id = request.getParameter("id");
        ProposalToRfp prfp = modelService.findById(Integer.parseInt(id));
        if (prfp == null)
            return errorPage(language, Labels.ERR_RECORDNOTFOUND, tableName() + " WITH ID : " + id);
        PartnerAddress deliveryAddress = requestForProposalService.findById(prfp.getRfpId()).getDeliveryAddress();
        BusinessPartner customer = businessPartnerService.findById(deliveryAddress.getId().getBusinessPartnerId());
        BusinessPartner vendor = businessPartnerService.findById(prfp.getOwnerId());

        ControllerStatic controllerStatic = getControllerStatic();
        String view = "purchase/proposalPrint";
        if (Utils.emptyStr(view))
            view = DEFULT_EDIT_VIEW;
        ModelAndView model = new ModelAndView(view);

        model.addObject("prfp", prfp);
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
        model.addObject(Labels.PREVPAGE, prevPage(prfp));
        model.addObject(Labels.POSTLINK, getControllerStatic().getRootMapping() + "/show");
        model.addObject(Labels.SELECT_OPTIONS, getSelectOptions(language, getClient(session), controllerStatic));
        return model;
		
	}
	
}