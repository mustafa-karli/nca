package com.nauticana.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
@Table(name = "APPLICATION_CONFIG")
public class ApplicationConfig implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "businessPartner"))
	@GeneratedValue(generator = "generator")
	@Column(name = "OWNER_ID", unique = true, nullable = false)
	private int				id;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private BusinessPartner	businessPartner;

	@Column(name = "DOMAIN", nullable = false, length = 80)
	private String			domain;

	@Column(name = "HOMEPAGE", nullable = false, length = 80)
	private String			homepage;

	@Column(name = "APPLICATION_TITLE", nullable = false, length = 80)
	private String			applicationTitle;
}
