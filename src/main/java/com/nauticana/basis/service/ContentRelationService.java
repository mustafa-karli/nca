package com.nauticana.basis.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.ContentData;
import com.nauticana.basis.model.ContentRelation;
import com.nauticana.basis.model.ContentRelationId;
import com.nauticana.basis.repository.ContentDataRepository;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Utils;

@Service
public class ContentRelationService extends AbstractService<ContentRelation, ContentRelationId> {

	@Autowired
	ContentDataRepository parentRep;

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(ContentRelation entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(ContentRelation entity) {
		return entity.getOwnerId();
	};

	@Override
	public ContentRelation newEntityWithParentId(String parentKey) {
		ContentRelation entity = new ContentRelation();
		if (!Utils.emptyStr(parentKey)) {
			ContentRelationId id = new ContentRelationId();
			Long contentId = Long.parseLong(parentKey);
			id.setContentId(contentId);
			entity.setContentData(parentRep.findById(contentId).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ContentRelationId strToId(String id) {
		String[] s = id.split(",");
		return new ContentRelationId(s[0], Integer.parseInt(s[1]), Long.parseLong(s[2]));
	}

	@Override
	public ContentRelation newEntityWithId(String strId) {
		ContentRelation entity = new ContentRelation();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ContentRelation entity) {
		if (entity == null) return null;
		return ContentData.ROOTMAPPING + "/show?id=" + entity.getId().getContentId();
	}
	
	public List<ContentRelation> findByOtype(int client, String otype, String objid) throws ParseException {
		ArrayList<String>  fields  = new ArrayList<String>();
		ArrayList<String>  filters = new ArrayList<String>();
		ArrayList<Integer> types  = new ArrayList<Integer>();
		
		fields.add("OWNER_ID");
		fields.add("OBJECT_TYPE");
		fields.add("OBJECT_ID");
		filters.add(client+"");
		filters.add(otype);
		filters.add(objid);
		types.add(FieldType.T_INT);
		types.add(FieldType.T_STR);
		types.add(FieldType.T_INT);
		
		return search(fields, filters, types);
	}

	public ContentRelation postDataWithRelation(MultipartFile binFile, int client, String otype, String objid, String purpose, String caption, short priority, String username, String remoteAddr) throws Exception {
		String orgName = binFile.getOriginalFilename();
		ContentData data = new ContentData();
		data.setOriginalFile(orgName);
		data.setBindata(binFile.getBytes());
		data.setCreateTime(new Date());
		data.setOwnerId(client);
		data.setMimetype(binFile.getContentType());
		data.setOriginIp(remoteAddr);
		data.setUsername(username);
		data.setThumb(ContentDataService.thumb(data.getBindata()));
		data = parentRep.save(data);

		ContentRelation record = newEntityWithParentId(null);
		record.setId(new ContentRelationId(otype, Integer.parseInt(objid), data.getId()));
		record.setContentData(data);
		record.setCreateTime(new Date());
		record.setOwnerId(client);
		record.setPriority(priority);
		record.setPurpose(purpose);
		record.setUsername(username);
		record.setCaption(caption);
		return create(record);
	}

	@Override
	public String idAsStr(ContentRelation entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
	

}
