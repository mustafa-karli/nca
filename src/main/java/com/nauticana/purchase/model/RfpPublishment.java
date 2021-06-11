package com.nauticana.purchase.model;

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
@Table(name = "RFP_PUBLISHMENT")
public class RfpPublishment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "rfpId", column = @Column(name = "RFP_ID", nullable = false)),
		@AttributeOverride(name = "vendorId", column = @Column(name = "VENDOR_ID", nullable = false)) })
	private RfpPublishmentId	id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RFP_ID", nullable = false, insertable = false, updatable = false)
	private RequestForProposal			requestForProposal;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", length = 26)
	private Date						begda;
}
