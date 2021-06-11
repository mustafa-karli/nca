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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = MaterialSalePrice.TABLE_NAME)
public class MaterialSalePrice implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "materialSalePrice";
	public  static final String   TABLE_NAME  = "MATERIAL_SALE_PRICE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private MaterialSalePriceId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", nullable = false, length = 26)
	private Date endda;

	@Column(name = "PRICE", nullable = false, precision = 10)
	private BigDecimal price;
}
