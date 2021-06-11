package com.nauticana.business.model;

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

import lombok.Data;

@Data
@Entity
@Table(name = BusinessContract.TABLE_NAME)
public class BusinessContract implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "businessContract";
	public  static final String   TABLE_NAME  = "BUSINESS_CONTRACT";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "businessPartnerId", column = @Column(name = "BUSINESS_PARTNER_ID", nullable = false)),
			@AttributeOverride(name = "businessServiceId", column = @Column(name = "BUSINESS_SERVICE_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private BusinessContractId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_PARTNER_ID", nullable = false, insertable = false, updatable = false)
	private BusinessOwner businessOwner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_SERVICE_ID", nullable = false, insertable = false, updatable = false)
	private BusinessService businessService;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "ENDDA", nullable = false, length = 26)
	private Date endda;

	@Column(name = "COST", precision = 64, scale = 0)
	private Double cost;

	@Column(name = "DESCRIPTION", length = 2000)
	private String description;
}
