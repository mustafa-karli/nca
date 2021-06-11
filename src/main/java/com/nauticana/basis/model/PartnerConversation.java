package com.nauticana.basis.model;

import java.util.Date;

public class PartnerConversation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Date   ctime;
	private short  seq;
	private int    fromPartner;
	private int    toPartner;
	private String username;
	private String ctext;
	
	public PartnerConversation(Date ctime, short seq, int fromPartner, int toPartner, String username, String ctext) {
		super();
		this.ctime = ctime;
		this.seq = seq;
		this.fromPartner = fromPartner;
		this.toPartner = toPartner;
		this.username = username;
		this.ctext = ctext;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	public short getSeq() {
		return seq;
	}

	public void setSeq(short seq) {
		this.seq = seq;
	}
	
	public int getFromPartner() {
		return fromPartner;
	}

	public void setFromPartner(int fromPartner) {
		this.fromPartner = fromPartner;
	}

	public int getToPartner() {
		return toPartner;
	}

	public void setToPartner(int toPartner) {
		this.toPartner = toPartner;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCtext() {
		return ctext;
	}

	public void setCtext(String ctext) {
		this.ctext = ctext;
	}
	
	
}
