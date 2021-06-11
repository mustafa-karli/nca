package com.nauticana.basis.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = DomainName.TABLE_NAME)
public class DomainName implements java.io.Serializable {

	public static final String   ROOTMAPPING = "domainName";
	public static final String   TABLE_NAME  = "DOMAIN_NAME";


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DOMAIN", unique = true, nullable = false, length = 30)
	private String id;

	@Column(name = "KEYSIZE", nullable = false, precision = 4, scale = 0)
	private short keysize;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;

	@Column(name = "SORT_BY", nullable = false, length = 1)
	private String sortBy;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domainName")
	@OrderBy
	private Set<DomainValue> domainValues = new LinkedHashSet<DomainValue>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domainName")
	private Set<DomainLookup> domainLookups = new HashSet<DomainLookup>(0);

}
