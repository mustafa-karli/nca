package com.nauticana.personnel.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.service.PartnerAddressService;
import com.nauticana.personnel.model.Organization;
import com.nauticana.personnel.model.ViewOrganizationContainer;
import com.nauticana.personnel.repository.OrganizationJbdcRepository;

@Service
public class OrganizationService extends AbstractService<Organization, Integer> {

	@Autowired
	OrganizationService parentService;

	@Autowired
	PartnerAddressService partnerAddressService;

    @Autowired
    OrganizationJbdcRepository orgJdbcRep;

    @Override
	@Transactional
	public Organization newEntityWithParentId(String parentKey) {
		Organization entity = new Organization();
		if (!Utils.emptyStr(parentKey)) {
			Organization parent = parentService.findById(parentService.strToId(parentKey));
			entity.setOrganization(parent);
			entity.setPartnerAddress(parent.getPartnerAddress());
		}
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(Organization entity, int client) {
		if (entity.getPartnerAddress() == null)
			entity.setPartnerAddress(partnerAddressService.findAll(client).get(0));
	}

	@Override
	public int getClient(Organization entity) {
		return entity.getPartnerAddress().getId().getBusinessPartnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Organization newEntityWithId(String strId) {
		Organization entity = new Organization();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(Organization entity) {
		if (entity == null) return null;
		if (entity.getOrganization() == null) return null;
		return Organization.ROOTMAPPING + "/show?id=" + entity.getOrganization().getId();
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Organization> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Organization x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getCaption();
		}
		return items;
	}

    public List<Integer> findAllChildren(int parentId){
        List<Integer> result=new ArrayList<>();
        result.add(parentId);
        Queue<Integer> searchForParent = new LinkedList<Integer>();
        List<Integer> allChilds = orgJdbcRep.getAllChildren(parentId);
        do{
            if(!allChilds.isEmpty()){
                for (Integer child : allChilds) {
                    List<Integer> allChilds1 = orgJdbcRep.getAllChildren(child);
                    if (allChilds1.isEmpty()){
                        result.add(child);
                    }else{
                        searchForParent.add(child);
                    }
                }
            }
            if(!searchForParent.isEmpty()){
                int child = searchForParent.poll();
                result.add(child);
                allChilds = orgJdbcRep.getAllChildren(child);
            }
        }
        while(!searchForParent.isEmpty());

        return result;
    }

	public void getSiblings(ViewOrganizationContainer record){
    	orgJdbcRep.getSiblings(record);
    }

    public void findAllChildren(ViewOrganizationContainer record, List<Integer> list){
    	list.add(record.getId());
    	if (record.getChildren() == null) return;
    	for (ViewOrganizationContainer child : record.getChildren()) {
    		findAllChildren(child, list);
    	}
    }

    public void findAllChildren(ViewOrganizationContainer record, HashMap<Integer,ViewOrganizationContainer> list){
    	list.put(record.getId(), record);
    	if (record.getChildren() == null) return;
    	for (ViewOrganizationContainer child : record.getChildren()) {
    		findAllChildren(child, list);
    	}
    }

	@Override
	public String idAsStr(Organization entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

