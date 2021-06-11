package com.nauticana.sales.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.model.UserFavorite;
import com.nauticana.basis.service.UserFavoriteService;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Utils;
import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialGroup;
import com.nauticana.material.model.MaterialGroupAssignment;
import com.nauticana.material.model.MaterialSalePrice;
import com.nauticana.material.service.MaterialGroupService;
import com.nauticana.material.service.MaterialService;
import com.nauticana.sales.model.ViewCommitment;
import com.nauticana.sales.model.ViewCommitmentOrder;
import com.nauticana.sales.model.ViewProductGroupsForCart;
import com.nauticana.sales.model.ViewProductsForCart;
import com.nauticana.sales.repository.SalesJbdcRepository;

@Service
public class SalesJdbcService {

	@Autowired
	private SalesJbdcRepository		r;

	@Autowired
	private MaterialService			materialService;

	@Autowired
	private MaterialGroupService	materialGroupService;

	@Autowired
	private UserFavoriteService		userFavoriteService;

	public List<ViewProductsForCart> productsForCart(Date salesDate) {
		List<ViewProductsForCart> l = r.productsForCart(salesDate);
		for (ViewProductsForCart p : l) {
			p.setMaterialAttributes(materialService.findById(p.getId()).getMaterialAttributes());
		}
		return l;
	}

	public ViewProductGroupsForCart getSalesChildren(int id, String username, Date date) {
		MaterialGroup group = materialGroupService.findById(id);
		ViewProductGroupsForCart g = new ViewProductGroupsForCart(group.getId(), group.getCaption());
		char fav;
		int[] favs = null;
		if (!Utils.emptyStr(username))
		try {
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<String> filters = new ArrayList<String>();
			ArrayList<Integer> types = new ArrayList<Integer>();

			fields.add("USERNAME");
			fields.add("FAVORITE_TYPE");
			filters.add(username);
			filters.add("MT");
			types.add(FieldType.T_STR);
			types.add(FieldType.T_STR);

			List<UserFavorite> userFavorites = userFavoriteService.search(fields, filters, types);
			if (userFavorites.size() > 0) {
				favs = new int[userFavorites.size()];
				for (int i = 0; i < userFavorites.size(); i++) {
					favs[i] = Integer.parseInt(userFavorites.get(i).getId().getObjectId());
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (MaterialGroupAssignment a : group.getMaterialGroupAssignments()) {
			Material m = a.getMaterial();
			for (MaterialSalePrice p : m.getMaterialSalePrices()) {
				if (p.getId().getBegda().before(date) && p.getEndda().after(date)) {
					fav = 'N';
					if (favs != null)
					for (int i = 0; i < favs.length; i++) {
						fav = 'N';
						if (favs[i] == m.getId())
							fav = 'Y';
					}
					ViewProductsForCart c = new ViewProductsForCart(m.getId(), m.getCaption(), "", "", p.getPrice(), m.getMaterialAttributes(), fav);
					g.getItems().add(c);

				}
			}
		}
		
		for (MaterialGroup a : group.getMaterialGroups()) {
			ViewProductGroupsForCart child = getSalesChildren(a.getId(), null, date);
			if (child != null)
				g.getGroups().add(child);
		}
		
		if (g.getItems().isEmpty() && g.getGroups().isEmpty())
			return null;
		return g;
	}

	public ViewProductGroupsForCart getSalesCommitment(int id, Date date) {
		MaterialGroup group = materialGroupService.findById(id);
		ViewProductGroupsForCart g = new ViewProductGroupsForCart(group.getId(), group.getCaption());
		
		for (MaterialGroup a : group.getMaterialGroups()) {
			ViewProductGroupsForCart child = getSalesCommitment(a.getId(), date);
			if (child != null)
				g.getGroups().add(child);
		}
		
//		if (g.getCmts().isEmpty() && g.getGroups().isEmpty())
//			return null;
		return g;
	}

	public TreeSet<ViewProductGroupsForCart> productGroupsForCart(String username, Date salesDate, String masterGroup) {
		List<Integer> ids = r.productGroupsForCart(masterGroup);
		TreeSet<ViewProductGroupsForCart> list = new TreeSet<ViewProductGroupsForCart>();
		for (int id : ids) {
			ViewProductGroupsForCart v = getSalesChildren(id, username, salesDate);
			if (v != null)
				list.add(v);
		}
		return list;
	}

	public TreeSet<ViewProductGroupsForCart> productGroupsForCommitment(Date salesDate, String masterGroup) {
		List<Integer> ids = r.productGroupsForCart(masterGroup);
		TreeSet<ViewProductGroupsForCart> list = new TreeSet<ViewProductGroupsForCart>();
		for (int id : ids) {
			ViewProductGroupsForCart v = getSalesCommitment(id, salesDate);
			if (v != null)
				list.add(v);
		}
		return list;
	}

    public List<ViewCommitment> viewCommitments(String purpose, Date date) {
    	return r.viewCommitments(date);
    }

    public List<ViewCommitment> viewMyCommitments(int client) {
    	return r.viewMyCommitments(client);
    }

    public List<ViewCommitment> viewCommitmentSales(int client) {
    	return r.viewCommitmentSales(client);
    }

    public List<ViewCommitmentOrder> viewCommitmentOrders(int materialId, int client, Date date) {
    	return r.viewCommitmentOrders(materialId, client, date);
    }

    public int addSalesAddress(int id, String address, String city, String state, String country, double longitude, double latitude, double altitude) {
    	return r.addSalesAddress(id, address, city, state, country, longitude, latitude, altitude);
    }

	public int modifyOrder(int salesOrderId, int line, double quantity, double price, double discount, int materialId, String unit, String currency) {
		return r.modifyOrder(salesOrderId, line, quantity, price, discount, materialId, unit, currency);
	}

	public void discountOrder(int salesOrderId, int line, double quantity, double discount) {
		r.discountOrder(salesOrderId, line, quantity, discount);
	}
}
