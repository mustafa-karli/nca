package com.nauticana.finance.model;
// Generated Feb 20, 2018 9:43:30 AM by Hibernate Tools 5.2.8.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nauticana.material.model.MaterialSaleTax;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = TaxType.TABLE_NAME)
public class TaxType implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "taxType";
	public  static final String   TABLE_NAME  = "TAX_TYPE";

	@Id
	@Column(name = "TAX_ID", unique = true, nullable = false, length = 8)
	private String id;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;

	@Column(name = "COUNTRY", nullable = false, length = 2)
	private String country;

	@Column(name = "RATE", nullable = false, precision = 8)
	private BigDecimal rate;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxType")
	private Set<MaterialSaleTax> materialSaleTaxes = new HashSet<MaterialSaleTax>(0);
}
