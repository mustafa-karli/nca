package com.nauticana.material.model;

import java.math.BigDecimal;
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
@Table(name = LotInventory.TABLE_NAME)
public class LotInventory implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "lotInventory";
	public  static final String   TABLE_NAME  = "LOT_INVENTORY";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)),
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "lotId", column = @Column(name = "LOT_ID", nullable = false, length = 10)) })
	private LotInventoryId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", nullable = false, insertable = false, updatable = false) })
	private MaterialInventory materialInventory;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal quantity;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;
}
