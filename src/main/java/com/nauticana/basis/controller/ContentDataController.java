package com.nauticana.basis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.ContentData;

@Controller
@ResponseBody
@RequestMapping("/" + ContentData.ROOTMAPPING)
public class ContentDataController extends AbstractController<ContentData, Long> {

	@RequestMapping(value = "/read/{contentId}", method = RequestMethod.GET)
	public HttpEntity<byte[]> read(@PathVariable("contentId") long contentId, HttpServletRequest request) {
		ContentData data = modelService.findById(contentId);
		byte[] image = data.getBindata();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(data.getMimetype()));
		headers.setContentLength(image.length);
		return new HttpEntity<byte[]>(image, headers);
	}

	@RequestMapping(value = "/thumb/{contentId}", method = RequestMethod.GET)
	public HttpEntity<byte[]> thumb(@PathVariable("contentId") long contentId, HttpServletRequest request) {
		ContentData data = modelService.findById(contentId);
		byte[] image = data.getBindata();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(data.getMimetype()));
		headers.setContentLength(image.length);
		return new HttpEntity<byte[]>(image, headers);
	}

	
}
