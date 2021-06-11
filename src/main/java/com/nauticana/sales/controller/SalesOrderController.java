package com.nauticana.sales.controller;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.service.UserAccountService;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.Customer;
import com.nauticana.business.service.CustomerService;
import com.nauticana.material.service.MaterialService;
import com.nauticana.sales.model.SalesOrder;
import com.nauticana.sales.model.SalesOrderItem;
import com.nauticana.sales.model.SalesOrderItemId;
import com.nauticana.sales.service.SalesJdbcService;
import com.nauticana.sales.service.SalesOrderItemService;

@Controller
@RequestMapping("/" + SalesOrder.ROOTMAPPING)
public class SalesOrderController extends AbstractController<SalesOrder, Integer> {

	@Autowired
	SalesJdbcService salesJdbcService;
	
	@Autowired
	MaterialService materialService;
	
	@Autowired
	UserAccountService userAccountService;
	
	@Autowired
	CustomerService customerService;

	@Autowired
	SalesOrderItemService salesOrderItemService;


	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cartGet(HttpServletRequest request) {
		// Check for user and update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());
		
		ModelAndView model = new ModelAndView("sales/cartGroup");
		
//		List<ViewProductsForCart> products = salesJdbcService.productsForCart(new Date());
//		model.addObject("products", products);
		String salesTag = request.getParameter("salesTag");
		if (Utils.emptyStr(salesTag)) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, "salesTag=..null..");
		}
		model.addObject("productGroups", salesJdbcService.productGroupsForCart(username, Utils.onlyDate(), salesTag));
//		model.addObject("materialAttributeGroups", materialJdbcService.materialAttrGroupByMatGroup(4, 541));
		model.addObject("manufacturer", dataCache.getLookupOptions("MANUFACTURER", -1));
		
		model.addObject(Labels.PAGETITLE, language.getText("ONLINE_PRODUCT_SALE"));
		model.addObject(Labels.ADD_TO_CART, language.getIconText(Labels.ADD_TO_CART));
		model.addObject(Labels.CHECK_OUT, language.getIconText(Labels.CHECK_OUT));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PREVPAGE, prevPage(null));
		return model;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ModelAndView cartPost(HttpServletRequest request) throws Exception {
		// Check for user and update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());
		
		int client;
		try {
			client = (int) session.getAttribute(Labels.OWNER_ID);
		} catch (Exception e) {
			client = -1;
		}
		
		
		/*
		ModelAndView model = new ModelAndView("basis/htmldata");
		ArrayList<String> r1 = new ArrayList<String>();
		ArrayList<String> r2 = new ArrayList<String>();
		
		Enumeration<String> e = request.getHeaderNames();
		
		while (e.hasMoreElements()) {
			String a = e.nextElement();
			r1.add(a);
			r2.add((String) request.getHeader(a));
		}

		e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String a = e.nextElement();
			r1.add(a);
			r2.add((String) request.getParameter(a));
		}
		model.addObject("r1", r1);
		model.addObject("r2", r2);
		*/
		
		
		String cartContent = "{xxx:" + (String) request.getParameter("cart_list") + "}";
//		System.out.println(cartContent);
		JSONObject jsonObject = new JSONObject(cartContent);
	    JSONArray jsonArray = jsonObject.getJSONArray("xxx");
	    Set<SalesOrderItem> items = new HashSet<SalesOrderItem>(0);
	    for (int i = 0; i < jsonArray.length(); ++i) {
	    	JSONObject product  = jsonArray.getJSONObject(i);
//	    	String productName  = product.getString("product_name");
	    	BigDecimal productPrice = BigDecimal.valueOf(product.getDouble("product_price"));
	    	int    productId    = product.getInt("product_id");
	    	BigDecimal quantity = BigDecimal.valueOf(product.getInt("product_quantity"));
//	    	System.out.println(product.getString("product_desc"));
//	    	System.out.println(product.getInt("unique_key"));
	    	SalesOrderItem item = new SalesOrderItem();
	    	SalesOrderItemId id = new SalesOrderItemId();
	    	id.setLine((short) (i+1));
	    	item.setId(id);
	    	item.setMaterial(materialService.findById(productId));
	    	item.setQuantity(quantity);
	    	item.setUnit("EA");
	    	item.setUnitPrice(productPrice);
	    	item.setStatus('I');
	    	items.add(item);
	    }
	    
	    SalesOrder salesOrder = new SalesOrder();
	    salesOrder.setOrderDate(new Date());
	    Customer customer = customerService.findById(client);
	    if (customer == null)
	    	customer = customerService.findById(1);
	    salesOrder.setCustomerId(customer.getId());
	    
	    salesOrder.setUsername(username);
	    salesOrder.setStatus("I");
	    salesOrder.setSalesOrderItems(items);
	    salesOrder.setOwnerId(client);
	    salesOrder = modelService.create(salesOrder);
	    for (SalesOrderItem item : salesOrder.getSalesOrderItems()) {
	    	item.getId().setSalesOrderId(salesOrder.getId());
	    	salesOrderItemService.save(item);
	    }
	    return new ModelAndView("redirect:/salesOrder/show?id=" + salesOrder.getId());
	}

}

