package com.nauticana.basis.utils;

import java.util.List;

import com.nauticana.basis.model.UserMenu;

public class NamsSession {

	private String key;
	private long   created;
	private long   lastAccess;
	private String user;
	private int    personId;
	private int    client;
	private String lang;
	
	private List<UserMenu> menu;
	
	
	
	public NamsSession(String user, int personId, int client, String lang, List<UserMenu> menu) {
		this.created = System.currentTimeMillis();
		this.lastAccess = this.created;
		long t = this.created;
		char[]  c = new char[32];
		int i = 0;
		while (t > 0 && i < 32) {
			c[i] = (char) (t % 19 + 65);
			t = t / 19;
			i++;
		}
		key = c.toString();
		this.user = user;
		this.personId = personId;
		this.client = client;
		this.lang = lang;
		this.menu = menu;
	}

	public String getKey() {
		return key;
	}

	public synchronized long checkTime() {
		long t = System.currentTimeMillis();
		long elapsed = t - lastAccess;
		this.lastAccess = t;
		return elapsed;
	}

	public String getUser() {
		return user;
	}

	public int getPersonId() {
		return personId;
	}

	public int getClient() {
		return client;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public List<UserMenu> getMenu() {
		return menu;
	}

}
