package com.nauticana.finance.model;

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
@Table(name = "EXCHANGE_RATE")
public class ExchangeRate implements java.io.Serializable {

	private static final long	serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "cdate", column = @Column(name = "CDATE", nullable = false, length = 26)),
		@AttributeOverride(name = "curr1", column = @Column(name = "CURR1", nullable = false, length = 3)),
		@AttributeOverride(name = "curr2", column = @Column(name = "CURR2", nullable = false, length = 3))
	})
	private ExchangeRateId id;

	@Column(name = "RATE", nullable = false, precision = 12)
	private BigDecimal rate;
}
