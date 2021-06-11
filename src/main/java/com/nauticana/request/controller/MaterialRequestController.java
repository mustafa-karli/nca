package com.nauticana.request.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.UserFavorite;
import com.nauticana.basis.model.UserFavoriteId;
import com.nauticana.basis.model.UserNotification;
import com.nauticana.basis.service.ContentRelationService;
import com.nauticana.basis.service.LanguageService;
import com.nauticana.basis.service.NotificationTypeService;
import com.nauticana.basis.service.UserAccountService;
import com.nauticana.basis.service.UserFavoriteService;
import com.nauticana.basis.service.UserNotificationService;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialSalePrice;
import com.nauticana.material.service.MaterialService;
import com.nauticana.material.service.MaterialTypeService;
import com.nauticana.personnel.service.OrganizationService;
import com.nauticana.request.model.MaterialRequest;
import com.nauticana.request.model.MaterialRequestItem;
import com.nauticana.request.model.MaterialRequestItemAttr;
import com.nauticana.request.model.MaterialRequestItemAttrId;
import com.nauticana.request.model.MaterialRequestItemId;
import com.nauticana.request.model.ProductByDefine;
import com.nauticana.request.model.ProductTypeByDefine;
import com.nauticana.request.service.MaterialRequestItemAttrService;
import com.nauticana.request.service.MaterialRequestItemService;
import com.nauticana.request.service.MaterialRequestJdbcService;
import com.nauticana.request.service.MaterialRequestService;
import com.nauticana.request.service.ProductTypeByDefineService;
import com.nauticana.sales.model.ViewProductGroupsForCart;

@Controller
@RequestMapping("/materialRequest")
public class MaterialRequestController extends AbstractController<MaterialRequest, Integer> {

	@Autowired
	MaterialRequestJdbcService		materialRequestJdbcService;

	@Autowired
	MaterialService					materialService;

	@Autowired
	MaterialTypeService				materialTypeService;

	@Autowired
	ProductTypeByDefineService		productTypeByDefineService;

	@Autowired
	UserAccountService				userAccountService;

	@Autowired
	MaterialRequestItemService		materialRequestItemService;

	@Autowired
	MaterialRequestItemAttrService	materialRequestItemAttrService;

	@Autowired
	OrganizationService				organizationService;

	@Autowired
	MaterialRequestService			materialRequestService;

	@Autowired
	LanguageService					languageService;

	@Autowired
	ContentRelationService			contentRelationService;

	@Autowired
	UserFavoriteService				userFavoriteService;

	@Autowired
	UserNotificationService			userNotificationService;

	@Autowired
	NotificationTypeService			notificationTypeService;

	private String getMaterialRequestJson(String materialRequestId) {
		String mrJson = "";
		MaterialRequest materialRequest = null;
		materialRequest = materialRequestService.findById(materialRequestService.strToId(materialRequestId));

		try {
			MaterialSalePrice materialSalesPrice = null;

			for (MaterialRequestItem mr_item : materialRequest.getMaterialRequestItems()) {

				for (MaterialSalePrice ms_item : mr_item.getMaterial().getMaterialSalePrices()) {
					if (ms_item.getEndda().after(Calendar.getInstance().getTime())) {
						materialSalesPrice = ms_item;
						break;
					}
				}
				if (!Utils.emptyStr(mrJson)) {
					mrJson = mrJson + ",";
				}
				mrJson = mrJson + "{\"product_name\":\"" + mr_item.getMaterial().getCaption() + "\",";
				mrJson = mrJson + "\"product_price\":\"" + materialSalesPrice.getPrice() + "\",";
				mrJson = mrJson + "\"product_id\":\"" + mr_item.getMaterial().getId() + "\",";
				mrJson = mrJson + "\"unique_key\":\"" + mr_item.getMaterial().getId() + "\",";
				mrJson = mrJson + "\"product_quantity\":\"" + mr_item.getQuantity().intValueExact() + "\"}";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mrJson;
	}

	private void addNotification(int client, int mrId, Date dueDate, String decription) throws Exception {
		List<String[]> notificationTypes = basisService.notificationUser(client, tableName(), "CREATE");

		for (String[] sl : notificationTypes) {
			UserNotification un = userNotificationService.newEntityWithParentId(null);
			un.setNotificationType(notificationTypeService.findById(Integer.parseInt(sl[0])));
			un.setObjectId(mrId + "");
			un.setUserAccount(userAccountService.findById(sl[1]));
			un.setRaiseDate(new Date());
			un.setStatus("I");
			un.setDescription(decription);
			un.setDueDate(dueDate);
			userNotificationService.create(un);
		}

	}

	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	public ModelAndView entryGet(HttpServletRequest request) {
		// Check for user and update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		ModelAndView model = new ModelAndView("request/materialRequestEntry");
		model.addObject("language", language);
		String mrJson = "";

		String materialRequestId = request.getParameter("id");

		if (!Utils.emptyStr(materialRequestId)) {
			mrJson = getMaterialRequestJson(materialRequestId);
		}

		TreeSet<ViewProductGroupsForCart> productGroups = materialRequestJdbcService.productGroupsForCart(getClient(session), username, Utils.onlyDate(), "SALES");

		model.addObject("mrJson", mrJson);
		model.addObject("materialRequestId", materialRequestId);
		model.addObject("productGroups", productGroups);
		model.addObject(Labels.MY_FAVORITE_ORDERS, language.getText(Labels.MY_FAVORITE_ORDERS));
		model.addObject(Labels.MY_FAVORITE_PRODUCTS, language.getText(Labels.MY_FAVORITE_PRODUCTS));
		model.addObject(Labels.ORGANIZATION_ID, session.getAttribute(Labels.ORGANIZATION_ID));
		model.addObject(Labels.ORGANIZATION, dataCache.getLookupOptions(Labels.ORGANIZATION, getClient(session)));
		model.addObject(Labels.PAGETITLE, language.getText("ONLINE_PRODUCT_SALE"));
		model.addObject(Labels.ADD_TO_CART, language.getIconText(Labels.ADD_TO_CART));
		model.addObject(Labels.CHECK_OUT, language.getIconText(Labels.CHECK_OUT));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PREVPAGE, prevPage(null));
		return model;
	}

	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	public ModelAndView entryPost(HttpServletRequest request) throws Exception {
		// Check for user and update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		int client = getClient(session);

		String cartContent = "{xxx:" + (String) request.getParameter("cart_list") + "}";
		// System.out.println(cartContent);

		JSONObject jsonObject = new JSONObject(cartContent);
		JSONArray jsonArray = jsonObject.getJSONArray("xxx");

		// get materialRequestId
		String materialRequestId = request.getParameter("materialRequestId");

		MaterialRequest materialRequest = null;

		int mrIdInt = 0;
		try {
			mrIdInt = Integer.parseInt(materialRequestId);
			materialRequest = materialRequestService.findById(mrIdInt);
			for (MaterialRequestItem mr_item : materialRequest.getMaterialRequestItems()) {
				materialRequestItemService.remove(mr_item.getId());
			}
		} catch (Exception e) {
			materialRequest = modelService.newEntityWithParentId(null);
		}
		materialRequest.setMaterialRequestItems(null);
		try {
			materialRequest.setUsername(username);
			materialRequest.setOwnerId(client);
			materialRequest.setDueDate(Utils.onlyDate());
			materialRequest.setRequestDate(Utils.onlyDate());
			materialRequest.setOrganization(organizationService.findById((Integer) session.getAttribute("ORGANIZATION_ID")));
			materialRequest = modelService.create(materialRequest);
			mrIdInt = materialRequest.getId();
			materialRequestId = String.valueOf(mrIdInt);
			for (int i = 0; i < jsonArray.length(); ++i) {
				JSONObject product = jsonArray.getJSONObject(i);
				int productId = product.getInt("product_id");
				BigDecimal quantity = BigDecimal.valueOf(product.getInt("product_quantity"));
				MaterialRequestItem item = new MaterialRequestItem();
				MaterialRequestItemId id = new MaterialRequestItemId();
				id.setMaterialRequestId(mrIdInt);
				id.setLine((short) (i + 1));
				item.setId(id);
				item.setMaterial(materialService.findById(productId));
				item.setQuantity(quantity);
				item.setUnit("EA");
				materialRequestItemService.create(item);
			}

			String fav = request.getParameter("addToFav");
			if ("on".equals(fav)) {
				// basisService.addUserFavorite(username, "MR", materialRequestId, null);
				UserFavoriteId userFavoriteId = new UserFavoriteId(username, "MR", materialRequestId);
				UserFavorite userFavorite = new UserFavorite();
				userFavorite.setId(userFavoriteId);
				userFavoriteService.save(userFavorite);
			}

			addNotification(client, materialRequest.getId(), materialRequest.getDueDate(), "Yeni ürün siparişi");

			return new ModelAndView("redirect:/materialRequest/show?id=" + materialRequest.getId());
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/requestByDefine", method = RequestMethod.GET)
	public ModelAndView requestByDefineGet(HttpServletRequest request) throws Exception {
		// Check for user and update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		String materialTypeId = request.getParameter("materialTypeId");
		ProductTypeByDefine productTypeByDefine;
		try {
			productTypeByDefine = materialTypeService.findById(materialTypeId).getProductTypeByDefines().iterator().next();
		} catch (Exception e) {
			productTypeByDefine = null;
		}
		String materialId = request.getParameter("materialId");
		Material material = null;
		String contentRequired = "";
		if (!Utils.emptyStr(materialId)) {
			material = materialService.findById(materialService.strToId(materialId));
			for (ProductByDefine p : productTypeByDefine.getProductByDefines()) {
				if (p.getContentRequired().charAt(0) == 'Y' && p.getMaterial().getId() == material.getId()) {
					contentRequired = "required";
				}
			}
		}

		ModelAndView model = new ModelAndView("request/requestByDefine");
		model.addObject("language", language);
		model.addObject("MATERIAL_TYPE", dataCache.getLookupOptions("PRODUCT_TYPE_BY_DEFINE", getClient(session)));
		model.addObject("MEASUREMENT_UNIT", dataCache.getDomainOptions("MEASUREMENT_UNIT", language));
		model.addObject("contentRequired", contentRequired);
		if (productTypeByDefine != null) {
			model.addObject("productTypeByDefine", productTypeByDefine);
			model.addObject("materialTypeId", materialTypeId);
		}
		if (material != null) {
			model.addObject("material", material);
			model.addObject("materialId", materialId);
		}
		model.addObject(Labels.PAGETITLE, language.getText("ORDER_BY_DEFINE"));
		model.addObject(Labels.POSTLINK,  "materialRequest/postRequestByDefine");
		model.addObject(Labels.GETLINK,   "materialRequest/requestByDefine");
		model.addObject(Labels.PREVPAGE,  prevPage(null));
		return model;
	}

	@RequestMapping(value = "/postRequestByDefine")
	public ModelAndView requestByDefinePost(@RequestParam("binFile") MultipartFile binFile, HttpServletRequest request) throws Exception {
		// Check for user and update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		int client = getClient(session);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		String materialTypeId = request.getParameter("materialTypeId");
		ProductTypeByDefine productTypeByDefine;
		try {
			productTypeByDefine = materialTypeService.findById(materialTypeId).getProductTypeByDefines().iterator().next();
		} catch (Exception e) {
			productTypeByDefine = null;
		}
		String materialId = request.getParameter("materialId");
		Material material = null;
		String contentRequired = "";
		if (!Utils.emptyStr(materialId)) {
			material = materialService.findById(materialService.strToId(materialId));
			for (ProductByDefine p : productTypeByDefine.getProductByDefines()) {
				if (p.getContentRequired().charAt(0) == 'Y' && p.getMaterial().getId() == material.getId()) {
					contentRequired = "required";
				}
			}
		}
		ArrayList<String> magIds = new ArrayList<String>();
		ArrayList<String> magVals = new ArrayList<String>();
		int c = 0;
		String magId = request.getParameter("magId" + c);
		while (!Utils.emptyStr(magId)) {
			String magVal = request.getParameter("magVal" + c);
			magIds.add(magId);
			magVals.add(magVal);
			c++;
			magId = request.getParameter("magId" + c);
		}

		float quantity;
		try {
			quantity = Float.parseFloat(request.getParameter("quantity"));
		} catch (Exception e) {
			quantity = 0;
		}
		String unit = request.getParameter("unit");
		String description = request.getParameter("description");

		if (productTypeByDefine == null || material == null || quantity == 0) {
			ModelAndView model = new ModelAndView("request/requestByDefine");
			model.addObject("language", language);
			model.addObject("MATERIAL_TYPE", dataCache.getLookupOptions("PRODUCT_TYPE_BY_DEFINE", client));
			model.addObject("MEASUREMENT_UNIT", dataCache.getDomainOptions("MEASUREMENT_UNIT", language));
			model.addObject("contentRequired", contentRequired);
			if (productTypeByDefine != null) {
				model.addObject("productTypeByDefine", productTypeByDefine);
				model.addObject("materialTypeId", materialTypeId);
			}
			if (material != null) {
				model.addObject("material", material);
				model.addObject("materialId", materialId);
			}
			model.addObject(Labels.PAGETITLE, language.getText("ORDER_BY_DEFINE"));
			model.addObject(Labels.POSTLINK, "materialRequest/postRequestByDefine");
			model.addObject(Labels.PREVPAGE, prevPage(null));
			return model;
		} else {
			if (!Utils.emptyStr(contentRequired) && binFile.isEmpty()) {
				return errorPage(language, "CONTENT REQUIRED", "SELECT DESCRIPTIVE FILE FOR REQUESTING THIS MATERIAL BY DEFINE");
			}
			MaterialRequest materialRequest = modelService.newEntityWithParentId(null);
			try {
				Date dueDate = Labels.dmyDF.parse(request.getParameter("dueDate"));
				materialRequest.setUsername(username);
				materialRequest.setOwnerId(getClient(session));
				materialRequest.setDueDate(dueDate);
				materialRequest.setRequestDate(Utils.onlyDate());
				materialRequest.setOrganization(organizationService.findById((Integer) session.getAttribute("ORGANIZATION_ID")));
				materialRequest.setDescription(description);
				materialRequest.setPurpose("D");
				materialRequest = modelService.create(materialRequest);
				MaterialRequestItem item = new MaterialRequestItem();
				item.setId(new MaterialRequestItemId(materialRequest.getId(), (short) 1));
				item.setMaterialRequest(materialRequest);
				item.setMaterial(material);
				item.setQuantity(BigDecimal.valueOf(quantity));
				item.setUnit(unit);
				materialRequestItemService.create(item);
				for (int i = 0; i < magIds.size(); i++) {
					MaterialRequestItemAttr attr = new MaterialRequestItemAttr();
					attr.setId(new MaterialRequestItemAttrId(item.getId().getMaterialRequestId(), item.getId().getLine(), magIds.get(i)));
					attr.setMaterialRequestItem(item);
					attr.setValue(magVals.get(i));
					materialRequestItemAttrService.create(attr);
				}
				if (!binFile.isEmpty()) {
					contentRelationService.postDataWithRelation(binFile, client, "MR", materialRequest.getId() + "", "PH", "PHOTO", (short) 1, username, request.getRemoteAddr());
//					materialRequest.setStatus("G");
					materialRequestService.save(materialRequest);
				}
				addNotification(client, materialRequest.getId(), materialRequest.getDueDate(), "Özel Pasta Siparişi");

				return new ModelAndView("redirect:/materialRequest/show?id=" + materialRequest.getId());
			} catch (Exception e) {
				return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
			}
		}
	}

	@GetMapping("/listFav")
	@ResponseBody
	public ArrayList<MaterialRequest> getUserFavorites(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return null;
		int[] ids = basisService.userFavoritesInt(username, "MR");
		MaterialRequest materialRequest = null;
		ArrayList<MaterialRequest> materialRequests = new ArrayList<MaterialRequest>();
		for (int id : ids) {
			materialRequest = materialRequestService.findById(id);
			if (materialRequest != null) {
				materialRequests.add(materialRequest);
			}
		}
		return materialRequests;
	}

	@GetMapping(value = "/getFav")
	@ResponseBody
	public String getFav(@RequestParam String id, HttpServletRequest request) throws JSONException {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return null;
		String mrJson = getMaterialRequestJson(id);
		mrJson = "[" + mrJson + "]";
		return mrJson;
	}

	@RequestMapping(value = "/approve")
	public ModelAndView approve(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		try {
			String idStr = request.getParameter("id");
			if (!basisService.authorityChk(username, tableName(), Labels.APPROVE, idStr))
				return errorPage(language, Labels.ERR_UNAUTHORIZED, tableName() + "-" + Labels.APPROVE + " ON " + idStr);
			int id = modelService.strToId(idStr);
			MaterialRequest record = modelService.findById(id);
			record.setStatus("A");
			modelService.save(record);
			return new ModelAndView("redirect:/materialRequest/list");
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/close")
	public ModelAndView close(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		try {
			String idStr = request.getParameter("id");
			if (!basisService.authorityChk(username, tableName(), Labels.APPROVE, idStr))
				return errorPage(language, Labels.ERR_UNAUTHORIZED, tableName() + "-" + Labels.APPROVE + " ON " + idStr);
			int id = modelService.strToId(idStr);
			MaterialRequest record = modelService.findById(id);
			record.setStatus("C");
			modelService.save(record);
			return new ModelAndView("redirect:/materialRequest/list");
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/cancel")
	public ModelAndView cancel(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		try {
			String idStr = request.getParameter("id");
			if (!basisService.authorityChk(username, tableName(), Labels.APPROVE, idStr))
				return errorPage(language, Labels.ERR_UNAUTHORIZED, tableName() + "-" + Labels.APPROVE + " ON " + idStr);
			int id = modelService.strToId(idStr);
			MaterialRequest record = modelService.findById(id);
			record.setStatus("F");
			modelService.save(record);
			return new ModelAndView("redirect:/materialRequest/list");
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
	}

}
