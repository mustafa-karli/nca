package com.nauticana.basis.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = DomainLookup.TABLE_NAME)
public class DomainLookup implements java.io.Serializable {

	public static final String   ROOTMAPPING = "domainLookup";
	public static final String   TABLE_NAME  = "DOMAIN_LOOKUP";

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "domain", column = @Column(name = "DOMAIN", nullable = false, length = 30)),
			@AttributeOverride(name = "TABLE_NAME", column = @Column(name = "TABLENAME", nullable = false, length = 30)) })
	private DomainLookupId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOMAIN", nullable = false, insertable = false, updatable = false)
	private DomainName domainName;

	@Column(name = "FIELDNAME", nullable = false, length = 30)
	private String fieldName;
}
