package com.nauticana.motifarge.controller;

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
import com.nauticana.basis.service.UserAccountService;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.Vendor;
import com.nauticana.business.service.VendorService;
import com.nauticana.material.model.Manufacturer;
import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialType;
import com.nauticana.material.service.ManufacturerService;
import com.nauticana.material.service.MaterialAttributeGroupService;
import com.nauticana.material.service.MaterialJdbcService;
import com.nauticana.material.service.MaterialService;
import com.nauticana.motifarge.model.ProductPriceCommitment;
import com.nauticana.motifarge.model.ProductPriceCommitmentId;
import com.nauticana.motifarge.model.ProductPriceCommitmentItem;
import com.nauticana.motifarge.model.ProductPriceCommitmentItemId;
import com.nauticana.motifarge.model.SalesOrderCommitment;
import com.nauticana.motifarge.model.SalesOrderCommitmentId;
import com.nauticana.motifarge.service.ProductPriceCommitmentItemService;
import com.nauticana.motifarge.service.SalesOrderCommitmentService;
import com.nauticana.personnel.model.CreditCard;
import com.nauticana.personnel.model.Person;
import com.nauticana.personnel.service.PersonService;
import com.nauticana.personnel.service.PersonnelJdbcService;
import com.nauticana.sales.model.SalesOrder;
import com.nauticana.sales.model.SalesOrderItem;
import com.nauticana.sales.model.ViewCommitmentOrder;
import com.nauticana.sales.service.SalesDeliveryAddressService;
import com.nauticana.sales.service.SalesJdbcService;
import com.nauticana.sales.service.SalesOrderItemService;
import com.nauticana.sales.service.SalesOrderService;

@Controller
@ResponseBody
@RequestMapping("/productPriceCommitment")
public class ProductPriceCommitmentController extends AbstractController<ProductPriceCommitment, ProductPriceCommitmentId> {

	@Autowired
	SalesJdbcService salesJdbcService;

	@Autowired
	MaterialService materialService;

	@Autowired
	MaterialJdbcService materialJdbcService;

	@Autowired
	MaterialAttributeGroupService materialAttributeGroupService;

	@Autowired
	ManufacturerService manufacturerService;
	
	@Autowired
	VendorService vendorService;

	@Autowired
	ProductPriceCommitmentItemService productPriceCommitmentItemService;

	@Autowired
	SalesOrderService salesOrderService;

	@Autowired
	SalesOrderItemService salesOrderItemService;

	@Autowired
	SalesOrderCommitmentService salesOrderCommitmentService;

	@Autowired
	SalesDeliveryAddressService salesDeliveryAddressService;

	@Autowired
	UserAccountService userAccountService;

	@Autowired
	PersonService personService;

	@Autowired
	PersonnelJdbcService personnelJdbcService;

	
	@RequestMapping(value = "/fastEntry", method = RequestMethod.GET)
	public ModelAndView fastEntryGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		ModelAndView model = new ModelAndView("motifarge/fastEntry");
		model.addObject("language", language);

		
		model.addObject("productGroups", salesJdbcService.productGroupsForCommitment(Utils.onlyDate(), "MOTIFSALES"));
		model.addObject("mags", materialAttributeGroupService.findAll(getClient(session)));
		
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.PREVPAGE, "productPriceCommitment/guestView");
		model.addObject(Labels.POSTLINK, "productPriceCommitment/fastEntry");
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
//			String vendorCaption = request.getParameter("vendorCaption");
			String partNumber = request.getParameter("partNumber");
			Date orderDeadLine = Labels.dmyDF.parse(request.getParameter("orderDeadLine"));
			Date deliveryPromise = Labels.dmyDF.parse(request.getParameter("deliveryPromise"));
			int minQuantity = Integer.parseInt(request.getParameter("minQuantity").replaceAll(" ", ""));
			int maxQuantity = Integer.parseInt(request.getParameter("maxQuantity").replaceAll(" ", ""));
			int boxQuantity = Integer.parseInt(request.getParameter("boxQuantity").replaceAll(" ", ""));
			int materialGroupId = Integer.parseInt(request.getParameter("materialGroupId"));
			String unit = request.getParameter("unit");
			double startPrice = Double.parseDouble(request.getParameter("startPrice").replaceAll(" ", ""));
			double goalPrice = Double.parseDouble(request.getParameter("goalPrice").replaceAll(" ", ""));
			String currency = request.getParameter("currency");
			String pricing = request.getParameter("pricing");
			int currentOrder = 0;
			double currentPrice = startPrice;
			float earlyBirdPct = Float.parseFloat(request.getParameter("earlyBirdPct").replaceAll(" ", ""));
			int[] quantity = new int[5];
			double[] price = new double[5];
			int maxMag = Integer.parseInt(request.getParameter("maxMag"));
			
			for (int i = 0; i < 5; i++)
				try {
					quantity[i] = Integer.parseInt(request.getParameter("quantity" + (i + 1)).replaceAll(" ", ""));
					price[i] = Double.parseDouble(request.getParameter("price" + (i + 1)).replaceAll(" ", ""));
				} catch (Exception e) {
					quantity[i] = 0;
					price[i] = 0;
				}

			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<String> filters = new ArrayList<String>();
			ArrayList<Integer> types = new ArrayList<Integer>();

			fields.add("PART_NUMBER");
			filters.add(partNumber);
			types.add(FieldType.T_STR);

			Material material = null;
			Vendor vendor = vendorService.findById(client);
			try {
				List<Material> materials = materialService.search(fields, filters, types);
				material = materials.get(0);
			} catch (Exception e) {
				MaterialType materialType = new MaterialType();
				materialType.setId("RETAIL");
				materialType.setCaption("RETAIL");
				materialType.setUnit("EA");
				Manufacturer manufacturer;
				try {
					manufacturer = manufacturerService.create(new Manufacturer());
					manufacturer.setId(client+"");
					manufacturer.setCaption(vendor.getBusinessPartner().getCaption());
					material = new Material();
					material.setManufacturer(manufacturer);
					material.setMaterialType(materialType);
					material.setOwnerId(client);
					material.setPartNumber(partNumber);
					material.setCaption(caption);
					material.setDefaultUnit("EA");
					material = materialService.create(material);
					
					materialJdbcService.addMaterialGroupAssignment(materialGroupId, material.getId(), Utils.onlyDate());
					for (int i = 0; i < maxMag; i++) {
						String magId = request.getParameter("magId" + i);
						String value = request.getParameter("mag" + i);
						if (!Utils.emptyStr(magId) && !Utils.emptyStr(value)) {
							materialJdbcService.addMaterialAttribute(material.getId(), magId, value);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			ProductPriceCommitmentId id = new ProductPriceCommitmentId(material.getId(), client, orderDeadLine);
			ProductPriceCommitment record = new ProductPriceCommitment();
			record.setId(id);
			record.setMaterial(material);
			record.setVendor(vendor);
			record.setOwnerId(client);
			record.setDeliveryPromise(deliveryPromise);
			record.setMinQuantity(minQuantity);
			record.setMaxQuantity(maxQuantity);
			record.setBoxQuantity(boxQuantity);
			record.setUnit(unit);
			record.setStartPrice(new BigDecimal(startPrice));
			record.setGoalPrice(new BigDecimal(goalPrice));
			record.setCurrency(currency);
			record.setPricing(pricing);
			record.setCurrentOrder(currentOrder);
			record.setCurrentPrice(new BigDecimal(currentPrice));
			record.setEarlyBirdPct(earlyBirdPct);
			record.setStatus("A");

			try {
				record = modelService.create(record);
				
				ProductPriceCommitmentItemId itemId = new ProductPriceCommitmentItemId(material.getId(), client, orderDeadLine, record.getMinQuantity());
				ProductPriceCommitmentItem item = new ProductPriceCommitmentItem();
				item.setId(itemId);
				item.setProductPriceCommitment(record);
				item.setPrice(new BigDecimal(startPrice));
				item = productPriceCommitmentItemService.create(item);
				
				int qty = minQuantity;
				double prc = startPrice;
				
				for (int i = 0; i < 5; i++) {
					if (quantity[i] > qty && price[i] < prc) {
						qty = quantity[i];
						prc = price[i];
						itemId = new ProductPriceCommitmentItemId(material.getId(), client, orderDeadLine, qty);
						item = new ProductPriceCommitmentItem();
						item.setId(itemId);
						item.setProductPriceCommitment(record);
						item.setPrice(new BigDecimal(prc));
						item = productPriceCommitmentItemService.create(item);
					}
				}
			} catch (Exception e) {
				return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
			}
			if (Utils.emptyStr(nextpage))
				nextpage = modelService.parentLink(record);
		} catch (Exception e) {

		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
    }

	@RequestMapping(value = "/guestView", method = RequestMethod.GET)
	public ModelAndView guestView(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		ModelAndView model = new ModelAndView("motifarge/guestView");
		model.addObject("allCommitments", salesJdbcService.viewCommitments("MOTIFSALES", Utils.onlyDate()));
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (!Utils.emptyStr(username)) {
    		model.addObject("sales", salesJdbcService.viewCommitmentSales(getClient(session)));
        }
        if (!Utils.emptyStr(username)) {
    		model.addObject("myCommitments", salesJdbcService.viewMyCommitments(getClient(session)));
        }
		model.addObject("language", language);
		return model;
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView orderGet(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		try {
			ModelAndView model = new ModelAndView("motifarge/order");
			model.addObject("record", modelService.findById(new ProductPriceCommitmentId(request.getParameter("id"))));
			
			for (CreditCard c : personService.findById((int)session.getAttribute(Labels.PERSON_ID)).getCreditCards()) {
				model.addObject("card", c);
			}
			model.addObject("language", language);
			model.addObject(Labels.PREVPAGE, "productPriceCommitment/guestView");
			model.addObject(Labels.POSTLINK, "productPriceCommitment/order");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ModelAndView orderPost(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/userAccount/login");
        int client = getClient(session);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        String nextpage = request.getParameter(Labels.NEXTPAGE);

		try {
			int materialId = Integer.parseInt(request.getParameter("materialId"));
			int businessPartnerId = Integer.parseInt(request.getParameter("businessPartnerId"));
			Date orderDeadLine = Labels.dmyDF.parse(request.getParameter("orderDeadLine"));
			Date dueDate = Labels.dmyDF.parse(request.getParameter("dueDate"));
			int quantity = Integer.parseInt(request.getParameter("quantity").replaceAll(" ",""));
			String unit = request.getParameter("unit");
			double unitPrice = Double.parseDouble(request.getParameter("unitPrice").replaceAll(" ",""));
			String currency = request.getParameter("currency");
			String description = request.getParameter("description");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			String cardNumber = request.getParameter("cardNumber");
			String cvc2 = request.getParameter("cvc2");
			short expireMonth = Short.parseShort(request.getParameter("expireMonth"));
			short expireYear = Short.parseShort(request.getParameter("expireYear"));
			String nameOnCard = request.getParameter("nameOnCard");
			
			ProductPriceCommitment cmt = modelService.findById(new ProductPriceCommitmentId(materialId, businessPartnerId, orderDeadLine));

			if (cmt.getCurrentOrder() + quantity > cmt.getMaxQuantity()) {
				throw new Exception ("Current order " + cmt.getCurrentOrder() + " + " + quantity + " is greater than maximum allowed quantity " + cmt.getMaxQuantity());
			}
			
			SalesOrder order = new SalesOrder();
			order.setCustomerId(client);
			order.setOwnerId(businessPartnerId);
			order.setDescription(description);
			order.setDueDate(dueDate);
			order.setUsername(username);
			order.setOrderDate(Utils.onlyDate());
			order.setOnlineOrder('Y');
			order.setStatus("I");
			order=salesOrderService.create(order);
			
			SalesOrderItem item = salesOrderItemService.newEntityWithId(order.getId()+",1");
			item.setCurrency(currency);
			item.setMaterial(cmt.getMaterial());
			item.setQuantity(new BigDecimal(quantity));
			item.setUnit(unit);
			item.setUnitPrice(new BigDecimal(unitPrice));
			item.setStatus('O');
			item = salesOrderItemService.create(item);
			
			salesJdbcService.addSalesAddress(order.getId(), address, city, state, country, 0, 0, 0);
			
			Person person;
			try {
				person = personService.findById((int) session.getAttribute(Labels.PERSON_ID));
			} catch (Exception e) {
				person = null;
			}
			
			CreditCard card = new CreditCard();
			card.setId(cardNumber);
			card.setPerson(person);
			card.setCvc2(cvc2);
			card.setExpireYear(expireYear);
			card.setExpireMonth(expireMonth);
			card.setNameOnCard(nameOnCard);
			
			double amount = unitPrice * quantity;
			
			if (personnelJdbcService.payment(card, amount, currency, 'P', null)) {
				order.setStatus("A");
				order.setAdvancePayment(new BigDecimal(amount));
				salesOrderService.save(order);

				SalesOrderCommitmentId ocid = new SalesOrderCommitmentId();
				ocid.setBusinessPartnerId(businessPartnerId);
				ocid.setInitialSequence(cmt.getCurrentOrder()+1);
				ocid.setLine(item.getId().getLine());
				ocid.setMaterialId(materialId);
				ocid.setOrderDeadLine(orderDeadLine);
				ocid.setSalesOrderId(order.getId());
				SalesOrderCommitment ocmt = new SalesOrderCommitment();
				ocmt.setId(ocid);
				ocmt.setProductPriceCommitment(cmt);
				ocmt.setSalesOrderItem(item);
				ocmt.setInitialPrice(cmt.getCurrentPrice());
				ocmt.setOrderRefund(new BigDecimal(0));
				ocmt.setRefundDecision("K");
				ocmt.setRefundStatus("I");
				ocmt = salesOrderCommitmentService.create(ocmt);
				
				cmt.setCurrentOrder(cmt.getCurrentOrder() + quantity);
				for (ProductPriceCommitmentItem cmtItem : cmt.getProductPriceCommitmentItems()) {
					if (cmtItem.getId().getQuantity() <= cmt.getCurrentOrder() && cmtItem.getPrice().doubleValue() < cmt.getCurrentPrice().doubleValue()) {
						cmt.setCurrentPrice(cmtItem.getPrice());
					}
				}
				modelService.save(cmt);
			}
//			nextpage = "salesOrder/list";
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:/productPriceCommitment/guestView");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}

	@RequestMapping(value = "/finalize", method = RequestMethod.GET)
	public ModelAndView finalizeGet(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/userAccount/login");
        int client = getClient(session);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        String nextpage = request.getParameter(Labels.NEXTPAGE);

		try {
			int materialId = Integer.parseInt(request.getParameter("materialId"));
			int businessPartnerId = Integer.parseInt(request.getParameter("businessPartnerId"));
			Date orderDeadLine = Labels.dmyDF.parse(request.getParameter("orderDeadLine"));

			ProductPriceCommitment cmt = modelService.findById(new ProductPriceCommitmentId(materialId, businessPartnerId, orderDeadLine));

			List<ViewCommitmentOrder> orders = salesJdbcService.viewCommitmentOrders(materialId, client, orderDeadLine);

			double quantity = 0;
			for (ViewCommitmentOrder order : orders) {
				quantity += order.getQuantity();
			}
			
//			double discount = cmt.getStartPrice().doubleValue() - cmt.getCurrentPrice().doubleValue();
			
//			if (discount > 0)
//			for (ViewCommitmentOrder order : orders) {
//				salesJdbcService.discountOrder(order.getSalesOrderId(), 1, order.getQuantity(), discount);
//			}
			
			double[] stepQuantity = new double[cmt.getProductPriceCommitmentItems().size()];
			double[] stepPrice    = new double[stepQuantity.length];
			double[] stepDiscount = new double[stepQuantity.length];
			double[] stepOrders   = new double[stepQuantity.length];
			int step = 0;
			int seq = 0;
			for (step=0; step<stepQuantity.length; step++) {
				stepQuantity[step] = 0;
				stepPrice[step] = 0;
				stepDiscount[step] = 0;
				stepOrders[step] = 0;
			}
			for (ProductPriceCommitmentItem ci : cmt.getProductPriceCommitmentItems()) {
				int l = step;
				for (int j = 0; j<step; j++) {
					if (stepQuantity[j]> ci.getId().getQuantity()) {
						for (int k=step; k>j; k--) {
							stepQuantity[k] = stepQuantity[k-1];
							stepPrice[k] = stepPrice[k-1];
						}
						l=j;
					}
				}
				stepQuantity[l]=ci.getId().getQuantity();
				stepPrice[l]=ci.getPrice().doubleValue();
				step++;
			}

			double price = cmt.getStartPrice().doubleValue();
			for (step=0; step<stepQuantity.length; step++)
			if (quantity > 0){
				if (stepQuantity[step] < quantity)
					stepOrders[step] = stepQuantity[step];
				else
					stepOrders[step] = quantity;
				quantity = quantity - stepOrders[step];
				price = stepPrice[step];
			}

			double favorers = 0;
			for (step=stepQuantity.length-1; step>=0; step--) {
				if (favorers > 0) {
					stepDiscount[step] = stepOrders[step] * price * cmt.getEarlyBirdPct() / 100;
					for (int j = step+1; j < stepQuantity.length; j++) {
						stepDiscount[j] = stepDiscount[j] - (stepDiscount[step] * stepOrders[j] / favorers);
					}
				}
				favorers += stepOrders[step];
			}
			
			
			step = 0;
			for (ViewCommitmentOrder order : orders) {

				double qty = order.getQuantity();
//				int    line = 1;
				double total = 0;
				
				while (qty > 0) {
					double oqty = qty;
					if (seq + qty > stepQuantity[step]) {
						oqty = stepQuantity[step] - seq;
						step++;
					}
					if (oqty>0) {
						total += oqty * (price - (stepDiscount[step] / stepOrders[step]));
//						salesJdbcService.modifyOrder(order.getSalesOrderId(), line, oqty, cmt.getStartPrice().doubleValue(), cmt.getStartPrice().doubleValue()-prc, cmt.getMaterial().getId(), cmt.getUnit(), cmt.getCurrency());
//						line++;
					}
					qty -= oqty;
					seq += (int) oqty;
				}
				if (total > 0)
					salesJdbcService.discountOrder(order.getSalesOrderId(), 1, order.getQuantity(), cmt.getStartPrice().doubleValue() - (total/order.getQuantity()));
			}
			
			cmt.setStatus("F");
			modelService.save(cmt);
			
			nextpage = "salesOrderCommitment/orders?materialId=" + materialId + "&orderDeadLine=" + Labels.dmyDF.format(orderDeadLine);
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:/productPriceCommitment/guestView");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}

}