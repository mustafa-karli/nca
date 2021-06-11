package com.nauticana.request.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.service.BasisService;
import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialGroup;
import com.nauticana.material.model.MaterialGroupAssignment;
import com.nauticana.material.model.MaterialSalePrice;
import com.nauticana.material.service.MaterialGroupService;
import com.nauticana.personnel.service.OrganizationService;
import com.nauticana.request.repository.MaterialRequestJbdcRepository;
import com.nauticana.sales.model.ViewProductGroupsForCart;
import com.nauticana.sales.model.ViewProductsForCart;

@Service
public class MaterialRequestJdbcService {

	@Autowired
	private BasisService					namsService;

	@Autowired
	private MaterialRequestJbdcRepository	r;

	@Autowired
	private MaterialGroupService			materialGroupService;

	@Autowired
	OrganizationService						organizationService;

	public ViewProductGroupsForCart getSalesChildren(int id, String username, Date date) {
		MaterialGroup group = materialGroupService.findById(id);
		ViewProductGroupsForCart g = new ViewProductGroupsForCart(group.getId(), group.getCaption());
		for (MaterialGroupAssignment a : group.getMaterialGroupAssignments()) {
			Material m = a.getMaterial();
			for (MaterialSalePrice p : m.getMaterialSalePrices()) {
				if (p.getId().getBegda().before(date) && p.getEndda().after(date)) {
					ViewProductsForCart c = new ViewProductsForCart(m.getId(), m.getCaption(), "", "", p.getPrice(), m.getMaterialAttributes(), 'N');
					g.getItems().add(c);
				}
			}
		}
		for (MaterialGroup a : group.getMaterialGroups()) {
			ViewProductGroupsForCart child = getSalesChildren(a.getId(), username, date);
			if (child != null)
				g.getGroups().add(child);
		}
		if (g.getItems().isEmpty() && g.getGroups().isEmpty())
			return null;
		return g;
	}

	public TreeSet<ViewProductGroupsForCart> productGroupsForCart(int client, String username, Date salesDate, String masterGroup) {
		List<Integer> ids = r.productGroupsForRequest(client, masterGroup);
		Collections.sort(ids);
		TreeSet<ViewProductGroupsForCart> list = new TreeSet<ViewProductGroupsForCart>(new Comparator<ViewProductGroupsForCart>() {
			public int compare(ViewProductGroupsForCart c1, ViewProductGroupsForCart c2) {
				return c1.getCaption().compareTo(c2.getCaption());
			}
		});
		for (int id : ids) {
			ViewProductGroupsForCart v = getSalesChildren(id, username, salesDate);
			if (v != null)
				list.add(v);
		}
		int[] f = namsService.userFavoritesInt(username, "MT");
		for (ViewProductGroupsForCart cart : list) {
			for (ViewProductsForCart prod : cart.getItems()) {
				for (int i = 0; i < f.length; i++) {
					if (prod.getId() == f[i]) {
						prod.setFavorite('Y');
					}
				}
			}
		}

		return list;
	}

}
