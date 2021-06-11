package com.nauticana.basis.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = UnitConversion.TABLE_NAME)
public class UnitConversion implements java.io.Serializable {

	public static final String   ROOTMAPPING = "unitConversion";
	public static final String   TABLE_NAME  = "UNIT_CONVERSION";

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="source", column=@Column(name="SOURCE", nullable=false, length=2) ), 
        @AttributeOverride(name="target", column=@Column(name="TARGET", nullable=false, length=2) ) } )
	private UnitConversionId id;

	@Column(name="MULTIPLIER", precision=20, scale=10)
	private BigDecimal multiplier;
}
