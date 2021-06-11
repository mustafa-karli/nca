package com.nauticana.basis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = Country.TABLE_NAME)
public class Country implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "country";
	public  static final String   TABLE_NAME  = "COUNTRY";

	@Id
	@Column(name = "CODE", unique = true, nullable = false, length = 30)
	private String id;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;
}
