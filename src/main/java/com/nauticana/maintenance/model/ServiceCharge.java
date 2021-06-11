package com.nauticana.maintenance.model;

import java.math.BigDecimal;
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
@Table(name = ServiceCharge.TABLE_NAME)
public class ServiceCharge implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "serviceCharge";
	public  static final String   TABLE_NAME  = "SERVICE_CHARGE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "serviceTypeId", column = @Column(name = "SERVICE_TYPE_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private ServiceChargeId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_TYPE_ID", nullable = false, insertable = false, updatable = false)
	private ServiceType serviceType;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", nullable = false, length = 26)
	private Date endda;

	@Column(name = "CHARGE", nullable = false, precision = 12)
	private BigDecimal charge;
}
