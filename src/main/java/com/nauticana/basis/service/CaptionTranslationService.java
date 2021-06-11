package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.CaptionTranslation;
import com.nauticana.basis.model.CaptionTranslationId;
import com.nauticana.basis.model.Language;
import com.nauticana.basis.repository.LanguageRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class CaptionTranslationService extends AbstractService<CaptionTranslation,CaptionTranslationId> {

	@Autowired
	LanguageRepository parentRep;

	@Override
	public CaptionTranslation newEntityWithParentId(String parentKey) {
		CaptionTranslation entity = new CaptionTranslation();
		if (!Utils.emptyStr(parentKey)) {
			CaptionTranslationId id = new CaptionTranslationId();
			id.setLangcode(parentKey);
			entity.setLanguage(parentRep.findById(parentKey).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public CaptionTranslationId strToId(String id) {
		String[] s = id.split(",");
		return new CaptionTranslationId(s[0], s[1]);
	}

	@Override
	public CaptionTranslation newEntityWithId(String strId) {
		CaptionTranslation entity = new CaptionTranslation();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(CaptionTranslation entity) {
		if (entity == null) return null;
		return Language.ROOTMAPPING + "/show?id=" + entity.getId().getLangcode();
	}

	@Override
	public String idAsStr(CaptionTranslation entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}