package com.nauticana.basis.model;
// Generated Mar 19, 2018 1:39:18 PM by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TableActionId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TABLENAME", nullable = false, length = 30)
	private String	tablename;

	@Column(name = "ACTION", nullable = false, length = 30)
	private String	action;

	public TableActionId(String keys) {
		String[] s = keys.split(",");
		this.tablename = s[0];
		this.action = s[1];
	}

	@Override
	public String toString() {
		return this.tablename + "," + this.action;
	}
}
