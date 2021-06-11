package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.DomainValue;
import com.nauticana.basis.model.DomainValueId;
import com.nauticana.basis.repository.DomainNameRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class DomainValueService extends AbstractService<DomainValue, DomainValueId> {

	@Autowired
	DomainNameRepository parentRep;

	@Override
	public DomainValue newEntityWithParentId(String parentKey) {
		DomainValue entity = new DomainValue();
		if (!Utils.emptyStr(parentKey)) {
			DomainValueId id = new DomainValueId();
			id.setDomain(parentKey);
			entity.setDomainName(parentRep.findById(parentKey).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public DomainValueId strToId(String id) {
		String[] s = id.split(",");
		return new DomainValueId(s[0], s[1]);
	}

	@Override
	public DomainValue newEntityWithId(String strId) {
		DomainValue entity = new DomainValue();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(DomainValue entity) {
		if (entity == null) return null;
		return "domainName/show?id=" + entity.getId().getDomain();
	}

	@Override
	public String idAsStr(DomainValue entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}
