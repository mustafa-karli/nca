package com.nauticana.sales.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.nauticana.business.model.BusinessPartner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "SALES_ACCOUNT_BALANCE")
public class SalesAccountBalance implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "businessPartner"))
	@GeneratedValue(generator = "generator")
	@Column(name = "OWNER_ID", unique = true, nullable = false)
	private int id;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private BusinessPartner businessPartner;

	@Column(name = "REFUND", nullable = false, precision = 12)
	private BigDecimal refund;

	@Column(name = "TOTAL_SALE", nullable = false, precision = 12)
	private BigDecimal totalSale;

	@Column(name = "TOTAL_PURCHASE", nullable = false, precision = 12)
	private BigDecimal totalPurchase;

	@Column(name = "PROVISION", nullable = false, precision = 12)
	private BigDecimal provision;
}
