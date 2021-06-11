package com.nauticana.basis.controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.ContentData;
import com.nauticana.basis.model.ContentRelation;
import com.nauticana.basis.model.ContentRelationId;
import com.nauticana.basis.repository.ContentRelationRepository;
import com.nauticana.basis.service.ContentDataService;
import com.nauticana.basis.service.ContentRelationService;
import com.nauticana.basis.utils.ControllerStatic;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;

@Controller
@ResponseBody
@RequestMapping("/" + ContentRelation.ROOTMAPPING)
public class ContentRelationController extends AbstractController<ContentRelation, ContentRelationId> {

	@Autowired
	ContentRelationRepository	modelRep;

	@Autowired
	ContentDataService			contentDataService;

	private static byte[] emptyPhoto = null;
	
	public byte[] emptyPhoto() throws IOException {
		if (emptyPhoto != null) return emptyPhoto;
		File fi = new File("noimage.gif");
		emptyPhoto = Files.readAllBytes(fi.toPath());
	    return emptyPhoto;
	}
	
	@RequestMapping(value = "/read/{otype}/{objid}/{contentId}", method = RequestMethod.GET)
	public HttpEntity<byte[]> readBinaryData(@PathVariable("otype") String otype, @PathVariable("objid") int objid, @PathVariable("contentId") long contentId, HttpServletRequest request, HttpServletResponse response) {
		ContentRelation entity = modelService.findById(new ContentRelationId(otype, objid, contentId));
		ContentData data = entity.getContentData();
		byte[] image = data.getBindata();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(data.getMimetype()));
		headers.setContentLength(image.length);
		return new HttpEntity<byte[]>(image, headers);
	}

	@RequestMapping(value = "/thumb/{otype}/{objid}/{contentId}", method = RequestMethod.GET)
	public HttpEntity<byte[]> thumbImage(@PathVariable("otype") String otype, @PathVariable("objid") int objid, @PathVariable("contentId") long contentId, HttpServletRequest request, HttpServletResponse response) {
		try {
			ContentRelation entity = modelService.findById(new ContentRelationId(otype, objid, contentId));
			ContentData data = entity.getContentData();
			byte[] image = data.getThumb();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			headers.setContentLength(image.length);
			return new HttpEntity<byte[]>(image, headers);
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/delWithObj/{otype}/{objid}/{contentId}")
	public String delWithObj(@PathVariable("otype") String otype, @PathVariable("objid") int objid, @PathVariable("contentId") long contentId, HttpServletRequest request, HttpServletResponse response) {
		try {
			modelService.remove(new ContentRelationId(otype, objid, contentId));
			ContentData data = contentDataService.findById(contentId);
			if (data.getContentRelations() == null || data.getContentRelations().isEmpty()) {
				contentDataService.remove(contentId);
			}
			return "DELETE OK";
		} catch (Exception e) {
			return "DELETE ERROR : " + e.getMessage();
		}
	}

	@RequestMapping(value = "/firstData/{otype}/{objid}", method = RequestMethod.GET)
	public HttpEntity<byte[]> firstData(@PathVariable("otype") String otype, @PathVariable("objid") String objid, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<String> filters = new ArrayList<String>();
			ArrayList<Integer> types = new ArrayList<Integer>();
			fields.add("OBJECT_TYPE");
			fields.add("OBJECT_ID");
			filters.add(otype);
			filters.add(objid);
			types.add(FieldType.T_STR);
			types.add(FieldType.T_INT);
			List<ContentRelation> records = modelService.search(fields, filters, types);

			ContentData data = contentDataService.findById(records.get(0).getId().getContentId());
			byte[] image = data.getBindata();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			headers.setContentLength(image.length);
			return new HttpEntity<byte[]>(image, headers);
		} catch (Exception e) {
			byte[] image = emptyPhoto();
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.IMAGE_JPEG);
		    headers.setContentLength(image.length);
		    return new HttpEntity<byte[]>(image, headers);
		}
	}

	@RequestMapping(value = "/firstThumb/{otype}/{objid}", method = RequestMethod.GET)
	public HttpEntity<byte[]> firstThumb(@PathVariable("otype") String otype, @PathVariable("objid") String objid, HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<String> filters = new ArrayList<String>();
			ArrayList<Integer> types = new ArrayList<Integer>();
			fields.add("OBJECT_TYPE");
			fields.add("OBJECT_ID");
			filters.add(otype);
			filters.add(objid);
			types.add(FieldType.T_STR);
			types.add(FieldType.T_INT);
			List<ContentRelation> records = modelService.search(fields, filters, types);
			ContentData data = contentDataService.findById(records.get(0).getId().getContentId());
			byte[] image = data.getThumb();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			headers.setContentLength(image.length);
			return new HttpEntity<byte[]>(image, headers);
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/listForObject/{otype}/{objid}", method = RequestMethod.GET)
	public ModelAndView listGet(@PathVariable("otype") String otype, @PathVariable("objid") String objid, HttpServletRequest request) throws IOException, ParseException {

		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		int client = getClient(session);

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

		List<ContentRelation> records = ((ContentRelationService) modelService).findByOtype(client, otype, objid);

		ControllerStatic controllerStatic = getControllerStatic();
        ModelAndView model = listView(controllerStatic, language);
		model.addObject("records", records);
		if (basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			model.addObject(Labels.INSERT_ALLOWED, "X");
		if (basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName()))
			model.addObject(Labels.UPDATE_ALLOWED, "X");
		if (basisService.authorityChk(username, Labels.TABLE, Labels.DELETE, tableName()))
			model.addObject(Labels.DELETE_ALLOWED, "X");

		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		return model;
	}

	@RequestMapping(value = "/postBinaryData/{otype}/{objid}", method = RequestMethod.POST)
	public String postBinaryData(@PathVariable("otype") String otype, @PathVariable("objid") String objid, @RequestParam("binFile") MultipartFile binFile, HttpServletRequest request) {
		if (!binFile.isEmpty()) {
			// Check for user and read authorization on table
			HttpSession session = request.getSession(true);
			String username = (String) session.getAttribute(Labels.USERNAME);
			if (Utils.emptyStr(username))
				return "";
			short priority;
			try {
				priority = Short.parseShort(request.getParameter("priority"));
			} catch (Exception e) {
				priority = 0;
			}
			String caption = request.getParameter("caption");
			String purpose = request.getParameter("purpose");
			try {
				ContentRelation record = ((ContentRelationService) modelService).postDataWithRelation(binFile, getClient(session), otype, objid, purpose, caption, priority, username, request.getRemoteAddr());
				return getControllerStatic().getRootMapping() + "/read/" + otype + "/" + objid + "/" + record.getId().getContentId();
			} catch (Exception e) {
				return null;
			}
		}
		return "";
	}

	@RequestMapping(value = "/postMultipleData", method = RequestMethod.GET)
	public ModelAndView postMultipleDataGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
		ModelAndView model = new ModelAndView("basis/uploadFile");
		model.addObject("language", language);
		model.addObject("CONTENT_RELATION_OBJECT_TYPE", dataCache.getDomainOptions("CONTENT_RELATION_OBJECT_TYPE", language));
		model.addObject("CONTENT_RELATION_PURPOSE", dataCache.getDomainOptions("CONTENT_RELATION_PURPOSE", language));
		return model;
	}

	@RequestMapping(value = "/postMultipleData", method = RequestMethod.POST)
	public ModelAndView postMultipleDataPost(HttpServletRequest request, HttpServletResponse response) {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String otype = "";
		String caption = "";
		String purpose = "";
		short priority = 0;

		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				FileItemIterator iterator = upload.getItemIterator(request);
				while (iterator.hasNext()) {
					FileItemStream binFile = iterator.next();
					InputStream is = binFile.openStream();
					if (binFile.isFormField()) {
						String fieldname = binFile.getFieldName();
						if ("otype".equals(fieldname))
							otype = Streams.asString(is);
						else if ("caption".equals(fieldname))
							caption = Streams.asString(is);
						else if ("purpose".equals(fieldname))
							purpose = Streams.asString(is);
						else if ("priority".equals(fieldname))
							priority = Short.parseShort(Streams.asString(is));
					} else {
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						Streams.copy(is, out, true);
						byte[] bytes = out.toByteArray();
						String orgName = binFile.getName();
						String[] il = orgName.split(".");
						int objid = Integer.parseInt(il[0]);
						ContentData data = contentDataService.newEntityWithParentId(null);
						data.setOriginalFile(orgName);
						data.setBindata(bytes);
						data.setCreateTime(new Date());
						data.setOwnerId(getClient(session));
						data.setMimetype(binFile.getContentType());
						data.setOriginIp(request.getRemoteAddr());
						data.setUsername(username);
						data = contentDataService.create(data);

						ContentRelation record = modelService.newEntityWithParentId(null);
						record.setId(new ContentRelationId(otype, objid, data.getId()));
						record.setContentData(data);
						record.setCreateTime(new Date());
						record.setOwnerId(getClient(session));
						record.setPriority(priority);
						record.setPurpose(purpose);
						record.setUsername(username);
						record.setCaption(caption);
						record = modelService.create(record);
					}
				}
			} catch (Exception e) {
				return errorPage(language, Labels.ERR_WRONGDATA, e.getMessage());
			}
		}

		// ServletFileUpload upload = new ServletFileUpload();
		//

		// String otype = request.getParameter("otype");
		// for (MultipartFile binFile : binFiles) {
		// if (!binFile.isEmpty()) {
		//// short priority;
		//// try {
		//// priority = Short.parseShort(request.getParameter("priority"));
		//// } catch (Exception e) {
		//// priority = 0;
		//// }
		//// String caption = request.getParameter("caption");
		//// String purpose = request.getParameter("purpose");
		// try {
		// String orgName = binFile.getOriginalFilename();
		// String[] il = orgName.split(".");
		// int objid = Integer.parseInt(il[0]);
		// ContentData data = contentDataService.newEntity(null);
		// data.setOriginalFile(orgName);
		// data.setBindata(new SerialBlob(binFile.getBytes()));
		// data.setCreateTime(new Date());
		// data.setOwnerId(getClient(session));
		// data.setMimetype(binFile.getContentType());
		// data.setOriginIp(request.getRemoteAddr());
		// data.setUsername(username);
		// data = contentDataService.create(data);
		//
		// ContentRelation record = MODEL_SERVICES.newEntity(null);
		// record.setId(new ContentRelationId("MT", objid, data.getId()));
		// record.setContentData(data);
		// record.setCreateTime(new Date());
		// record.setOwnerId(getClient(session));
		// record.setPriority(priority);
		// record.setPurpose(purpose);
		// record.setUsername(username);
		// record.setCaption(caption);
		// record = MODEL_SERVICES.create(record);
		// return getControllerStatic().getRootMapping() + "/read/" + otype + "/" +
		// objid + "/" + data.getId();
		// } catch (Exception e) {
		// return errorPage(language, Labels.ERR_WRONGDATA, e.getMessage());
		// }
		// }
		// }
		return new ModelAndView("redirect:/contentData/list");
	}

}
