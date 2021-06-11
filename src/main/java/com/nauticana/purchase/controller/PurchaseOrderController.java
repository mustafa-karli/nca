package com.nauticana.purchase.controller;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.nauticana.material.service.MaterialService;
import com.nauticana.personnel.service.OrganizationService;
import com.nauticana.purchase.model.PurchaseOrder;
import com.nauticana.purchase.model.PurchaseOrderItem;
import com.nauticana.purchase.model.PurchaseOrderItemId;
import com.nauticana.purchase.model.PurchaseOrderTax;
import com.nauticana.purchase.model.PurchaseOrderTaxId;
import com.nauticana.purchase.service.PurchaseJdbcService;
import com.nauticana.purchase.service.PurchaseOrderItemService;
import com.nauticana.purchase.service.PurchaseOrderTaxService;

@Controller
@ResponseBody
@RequestMapping("/" + PurchaseOrder.ROOTMAPPING)
public class PurchaseOrderController extends AbstractController<PurchaseOrder, Integer> {
	
	@Autowired
	OrganizationService organizationService;

	@Autowired
	MaterialService materialService;

	@Autowired
	PurchaseOrderItemService purchaseOrderItemService;

	@Autowired
	PurchaseOrderTaxService purchaseOrderTaxService;

	@Autowired
	PurchaseJdbcService purchaseJdbcService;
	
	@Autowired
	BusinessPartnerService			businessPartnerService;

//	@Autowired
//	PartnerAddressService partnerAddressService;

	
	
	@RequestMapping(value = "/fastEntry", method = RequestMethod.GET)
    public ModelAndView fastEntryGet(HttpServletRequest request, PurchaseOrder order, PurchaseOrderItem[] items, BusinessPartner vendor, String prevpage, String nextpage) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, "PURCHASE_ORDER"))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON PURCHASE_ORDER");
		int client = getClient(session);
    	int org = (int) session.getAttribute(Labels.ORGANIZATION_ID);
        order.setOrganization(organizationService.findById(org));
        order.setOwnerId(client);
        order.setUsername(username);
		ModelAndView model = new ModelAndView("purchase/orderEntry");
        model.addObject("vendor", vendor);
        model.addObject("order", order);
        model.addObject("items", items);
		model.addObject("language", language);
        model.addObject("EXCHANGE", dataCache.getDomainOptions("EXCHANGE", language));
        model.addObject("addresses", dataCache.getLookupOptions("PARTNER_ADDRESS", client));
		model.addObject(Labels.PREVPAGE, prevpage);
		model.addObject(Labels.NEXTPAGE, nextpage);
		model.addObject(Labels.POSTLINK, "purchaseOrder/fastEntry");
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
        short addressId = Short.parseShort(request.getParameter("addressId"));

		try {
			PurchaseOrder po = new PurchaseOrder();
			po.setDescription(request.getParameter("description"));
			po.setDueDate(Labels.dmyDF.parse(request.getParameter("dueDate")));
			po.setOrderDate(Utils.onlyDate());
			po.setOrganization(organizationService.findById(Integer.parseInt(request.getParameter("organizationId"))));
			po.setVendorId(Integer.parseInt(request.getParameter("vendorId")));
			try {
				po.setAdvancePayment(new BigDecimal(Double.parseDouble(request.getParameter("advancePayment"))));
			} catch (Exception e) {
				po.setAdvancePayment(new BigDecimal(0));
			}
			try {
				po.setDiscount(new BigDecimal(Double.parseDouble(request.getParameter("discount"))));
			} catch (Exception e) {
				po.setDiscount(new BigDecimal(0));
			}
			String reasonType;
			int reasonId;
			try {
				reasonType = request.getParameter("reasonType");
				reasonId = Integer.parseInt(request.getParameter("reasonId"));
			} catch (Exception e) {
				reasonType = null;
				reasonId = 0;
			}
			String refType;
			int refId;
			try {
				refType = request.getParameter("refType");
				refId = Integer.parseInt(request.getParameter("refId"));
			} catch (Exception e) {
				refType = null;
				refId = 0;
			}
			
			po.setOnlineOrder("Y");
			po.setOwnerId(client);
			po.setStatus("I");
			po.setUsername(username);

			int maxline = Integer.parseInt(request.getParameter("maxline"));
			
			PurchaseOrderItem[] items = new PurchaseOrderItem[maxline];
			PurchaseOrderTax[][] taxes = new PurchaseOrderTax[maxline][];
			for (short line = 1; line <= maxline; line++) {
				PurchaseOrderItemId id = new PurchaseOrderItemId(0, line);
				PurchaseOrderItem item = new PurchaseOrderItem();
				item.setCurrency(request.getParameter("currency"+line));
				item.setId(id);
				item.setMaterial(materialService.findById(Integer.parseInt(request.getParameter("materialId"+line))));
				item.setQuantity(new BigDecimal(request.getParameter("quantity"+line)));
				item.setStatus("I");
				item.setUnit(request.getParameter("unit"+line));
				item.setUnitPrice(new BigDecimal(request.getParameter("unitPrice"+line).replaceAll(" ", "")));
				items[line-1] = item;
				int taxCnt = Integer.parseInt(request.getParameter("taxCnt" + line));
				taxes[line-1] = new PurchaseOrderTax[taxCnt];
				for (int j=1; j <= taxCnt; j++) {
					try {
						String taxType = request.getParameter("taxType" + line + "_" + j);
						double taxAmt = Double.parseDouble(request.getParameter("taxAmt" + line + "_" + j).replaceAll(" ", ""));
						if (taxAmt > 0) {
							PurchaseOrderTax tax = new PurchaseOrderTax();
							tax.setId(new PurchaseOrderTaxId(0, line, taxType));
							tax.setPurchaseOrderItem(items[line-1]);
							tax.setAmount(new BigDecimal(taxAmt));
							taxes[line-1][j-1] = tax;
						} else
							taxes[line-1][j-1] = null;
					} catch (Exception e) {
						e.printStackTrace();
						return errorPage(language, Labels.ERR_WRONGDATA, e.getMessage());
					}
				}
			}
			
			po = modelService.create(po);
			purchaseJdbcService.addDeliveryAddress(po.getId(), client, addressId);
			
			
			for (int i = 0; i<items.length; i++)  {
				PurchaseOrderItem item = items[i];
				item.getId().setPurchaseOrderId(po.getId());
				item.setPurchaseOrder(po);
				item = purchaseOrderItemService.create(item);
				if (reasonId>0)
					purchaseJdbcService.addPurchaseReason(po.getId(), item.getId().getLine(), reasonType, reasonId, item.getId().getLine(), new Date());
				for (PurchaseOrderTax tax : taxes[i])
				if (tax != null){
					tax.getId().setPurchaseOrderId(po.getId());
					tax.setPurchaseOrderItem(item);
					purchaseOrderTaxService.create(tax);
				}
			}
			
			basisService.notifyUser(po.getVendorId(), tableName(), po.getId()+"", po.getDueDate(), language.getText("NEW") + " " + language.getText(tableName()) + " " + po.getId());
			
			if (reasonId>0)
				purchaseJdbcService.completePurchaseReason(reasonType.charAt(0), reasonId);
			
			if (refId>0)
				purchaseJdbcService.addPurchaseRef(refType.charAt(0), refId, po.getId());
			
			if (Utils.emptyStr(nextpage))
				nextpage = "purchaseOrder/show?id=" + po.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_WRONGDATA, e.getMessage());
		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
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
        PurchaseOrder po = modelService.findById(Integer.parseInt(id));
        if (po == null)
            return errorPage(language, Labels.ERR_RECORDNOTFOUND, tableName() + " WITH ID : " + id);
        PartnerAddress deliveryAddress = po.getPartnerAddresses().iterator().next();
        BusinessPartner customer = businessPartnerService.findById(po.getOwnerId());
        BusinessPartner vendor = businessPartnerService.findById(po.getVendorId());

        ControllerStatic controllerStatic = getControllerStatic();
        String view = "purchase/purchaseOrderPrint";
        if (Utils.emptyStr(view))
            view = DEFULT_EDIT_VIEW;
        ModelAndView model = new ModelAndView(view);

        model.addObject("po", po);
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
        model.addObject(Labels.PREVPAGE, prevPage(po));
        model.addObject(Labels.POSTLINK, getControllerStatic().getRootMapping() + "/show");
        model.addObject(Labels.SELECT_OPTIONS, getSelectOptions(language, getClient(session), controllerStatic));
        return model;
	}

}