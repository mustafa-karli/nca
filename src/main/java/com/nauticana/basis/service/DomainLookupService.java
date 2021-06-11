package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.DomainLookup;
import com.nauticana.basis.model.DomainLookupId;
import com.nauticana.basis.repository.DomainNameRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class DomainLookupService extends AbstractService<DomainLookup, DomainLookupId> {

	@Autowired
	DomainNameRepository parentRep;

	@Override
	public DomainLookup newEntityWithParentId(String parentKey) {
		DomainLookup entity = new DomainLookup();
		if (!Utils.emptyStr(parentKey)) {
			DomainLookupId id = new DomainLookupId();
			id.setDomain(parentKey);
			entity.setDomainName(parentRep.findById(parentKey).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public DomainLookupId strToId(String id) {
		String[] s = id.split(",");
		return new DomainLookupId(s[0], s[1]);
	}

	@Override
	public DomainLookup newEntityWithId(String strId) {
		DomainLookup entity = new DomainLookup();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(DomainLookup entity) {
		if (entity == null) return null;
		return "domainName/show?id=" + entity.getId().getDomain();
	}

	@Override
	public String idAsStr(DomainLookup entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}
