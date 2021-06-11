package com.nauticana.basis.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.ContentData;

@Service
public class ContentDataService extends AbstractService<ContentData, Long> {
		
	public static final int THUMB_SIZE = 64;
	
	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(ContentData entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(ContentData entity) {
		return entity.getOwnerId();
	};

	@Override
	public ContentData newEntityWithParentId(String parentKey) {
		return new ContentData();
	}
	
	@Override
	public Long strToId(String id) {
		return Long.parseLong(id);
	}

	@Override
	public ContentData newEntityWithId(String strId) {
		ContentData entity = new ContentData();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ContentData entity) {
		return null;
	}
	
	
	
	@Override
	public ContentData create(ContentData entity) throws Exception {
		entity.setThumb(thumb(entity.getBindata()));
		return super.create(entity);
	}

	@Override
	public void save(ContentData entity) throws Exception {
		entity.setThumb(thumb(entity.getBindata()));
		super.save(entity);
	}

	public static byte[] thumb(byte[] data) {
		BufferedImage img;
		try {
			img = ImageIO.read(new ByteArrayInputStream(data));
			BufferedImage thumbnail = Scalr.resize(img, THUMB_SIZE);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(thumbnail, "jpg", baos );
			baos.flush();
			byte[] thumbByte = baos.toByteArray();
			baos.close();
			return thumbByte;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String idAsStr(ContentData entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}
