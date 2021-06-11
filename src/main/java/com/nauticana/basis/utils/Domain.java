package com.nauticana.basis.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Domain {
	private String id;
	private String caption;
	private int size;
	private String sortBy;
	Map<String, String> options = new LinkedHashMap<String, String>();
	Map<String, Map<String, String>> langOptions = new HashMap<String, Map<String, String>>();
	private String jsonTxt = "";
	
	public Domain(String id, String caption, int size, String sortBy) {
		super();
		this.id = id;
		this.caption = caption;
		this.size = size;
		this.sortBy = sortBy;
	}

	public String getId() {
		return id;
	}

	public String getCaption() {
		return caption;
	}

	public String getSortBy() {
		return sortBy;
	}

	public int size() {
		return size;
	}

	public void addOption(String value,String caption) {
		options.put(value, caption);
	}
	
	public Map<String, String> getOptions() {
		return options;
	}

	public Map<String, String> getOptions(PortalLanguage l) {
		if (l == null) return options;
		Map<String, String> o = langOptions.get(l.code);
		if (o != null) return o;
		o = new LinkedHashMap<String, String>();
		for (String key : options.keySet()) {
			o.put(key, l.getText(options.get(key)));
		}
		List<Entry<String, String>> ol = new ArrayList<Entry<String, String>>(o.entrySet());
		if ("K".equals(this.sortBy))
			Collections.sort(ol, new MapKeyComparator());
		else
			Collections.sort(ol, new MapValueComparator());
		o.clear();
		for (Entry<String, String> entry : ol) {
			o.put(entry.getKey(), entry.getValue());
		}
		langOptions.put(l.code, o);
		return o;
	}

	public void println() {
		System.out.println("DOMAIN: " + id + " KEY SIZE: " + size);
		for (String s:options.keySet()) {
			System.out.println("  " + s + " -> " + options.get(s));
		}
	}

	public void println(PortalLanguage l) {
		System.out.println("DOMAIN: " + id + " KEY SIZE: " + size + " LANGUAGE: " + l.caption);
		Map<String, String> o = langOptions.get(l.code);
		for (String s:o.keySet()) {
			System.out.println("  " + s + " -> " + o.get(s));
		}
	}
	
	public String json() {
		if (!jsonTxt.equals("")) return jsonTxt;
		for (String key : options.keySet()) {
			jsonTxt = jsonTxt + ",{\"key\":\"" + key + "\",\"txt\":\"" + options.get(key) + "\"}";
		}
		if (jsonTxt.equals(""))
			jsonTxt = "[]";
		else
			jsonTxt = "[" + jsonTxt.substring(1) + "]"; 
		return jsonTxt;
	}
	
	public void clear() {
		options.clear();
		langOptions.clear();
	}
}
