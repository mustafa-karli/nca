package com.nauticana.helpdesk.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;
import com.nauticana.business.model.BusinessPartner;
import com.nauticana.business.model.BusinessService;

import lombok.Data;

@Data
@Entity
@Table(name = "SERVICE_LEVEL_AGREEMENT", schema = "PUBLIC", catalog = "PUBLIC")
public class ServiceLevelAgreement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "ownerId", column = @Column(name = "OWNER_ID", nullable = false)),
		@AttributeOverride(name = "businessServiceId", column = @Column(name = "BUSINESS_SERVICE_ID", nullable = false, length = 20)),
		@AttributeOverride(name = "businessPartnerId", column = @Column(name = "BUSINESS_PARTNER_ID", nullable = false)),
		@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private ServiceLevelAgreementId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_PARTNER_ID", nullable = false, insertable = false, updatable = false)
	private BusinessPartner businessPartner;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_SERVICE_ID", nullable = false, insertable = false, updatable = false)
	private BusinessService businessService;
	
	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", length = 26)
	private Date endda;
	
	@Column(name = "INITIAL_RESPONSE")
	private Integer initialResponse;
	
	@Column(name = "EXPERT_RESPONSE")
	private Integer expertResponse;
	
	@Column(name = "SERVICE_DAYS", length = 1)
	private Character serviceDays;
	
	@Column(name = "SERVICE_HOURS", length = 1)
	private Character serviceHours;
}
