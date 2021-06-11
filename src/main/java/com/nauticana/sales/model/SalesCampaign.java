package com.nauticana.sales.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = SalesCampaign.TABLE_NAME)
public class SalesCampaign implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "salesCampaign";
	public  static final String   TABLE_NAME  = "SALES_CAMPAIGN";
	public  static final String   SEQUENCE_NAME = "SALES_CAMPAIGN_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "SALES_CAMPAIGN_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", nullable = false, length = 26)
	private Date endda;

	@Column(name = "DISCOUNT_TYPE", nullable = false, length = 1)
	private String discountType;

	@Column(name = "QUANTITY_THRESHOLD")
	private Short quantityThreshold;

	@Column(name = "DISCOUNT_PERCENT")
	private Short discountPercent;

	@Column(name = "DISCOUNT_QUANTITY")
	private Short discountQuantity;

	@Column(name = "SALE_THRESHOLD", precision = 12)
	private BigDecimal saleThreshold;

	@Column(name = "DISCOUNT_AMOUNT", precision = 12)
	private BigDecimal discountAmount;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesCampaign")
	private Set<SalesCampaignItem> salesCampaignItems = new HashSet<SalesCampaignItem>(0);
}
