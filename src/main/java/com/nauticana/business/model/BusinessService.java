package com.nauticana.business.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nauticana.basis.model.ScreenPage;
import com.nauticana.helpdesk.model.ServiceLevelAgreement;
import com.nauticana.helpdesk.model.SupportArea;
import com.nauticana.maintenance.model.ServiceType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "BUSINESS_SERVICE")
public class BusinessService implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BUSINESS_SERVICE_ID", unique = true, nullable = false, length = 20)
	private String						id;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String						caption;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "BUSINESS_SERVICE_PAGE", joinColumns = {
			@JoinColumn(name = "BUSINESS_SERVICE_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "PAGENAME", nullable = false, updatable = false) })
	private Set<ScreenPage>				screenPages				= new HashSet<ScreenPage>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessService")
	private Set<SupportArea>			supportAreas			= new HashSet<SupportArea>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessService")
	private Set<BusinessContract>		businessContracts		= new HashSet<BusinessContract>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessService")
	private Set<ServiceLevelAgreement>	serviceLevelAgreements	= new HashSet<ServiceLevelAgreement>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessService")
	private Set<ServiceType>			serviceTypes			= new HashSet<ServiceType>(0);
}
