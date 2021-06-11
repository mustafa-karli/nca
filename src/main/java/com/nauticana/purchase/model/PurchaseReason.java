package com.nauticana.purchase.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = PurchaseReason.TABLE_NAME)
public class PurchaseReason implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "purchaseReason";
	public  static final String   TABLE_NAME  = "PURCHASE_REASON";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "purchaseOrderId", column = @Column(name = "PURCHASE_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)),
			@AttributeOverride(name = "reasonType", column = @Column(name = "REASON_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "reasonId", column = @Column(name = "REASON_ID", nullable = false)),
			@AttributeOverride(name = "reasonLine", column = @Column(name = "REASON_LINE", nullable = false)) })
	private PurchaseReasonId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PURCHASE_ORDER_ID", referencedColumnName = "PURCHASE_ORDER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private PurchaseOrderItem purchaseOrderItem;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", length = 26)
	private Date begda;
}
