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
public class TableFieldFaceId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TABLENAME", nullable = false, length = 30)
	private String	tablename;

	@Column(name = "FIELDNAME", nullable = false, length = 30)
	private String	fieldname;

	public TableFieldFaceId(String keys) {
		String[] s = keys.split(",");
		this.tablename = s[0];
		this.fieldname = s[1];
	}

	@Override
	public String toString() {
		return this.tablename + "," + this.fieldname;
	}

}
