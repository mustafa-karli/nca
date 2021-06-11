package com.nauticana.shipment.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;
import com.nauticana.personnel.model.Organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Shipment.TABLE_NAME)
public class Shipment implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "shipment";
	public  static final String   TABLE_NAME  = "SHIPMENT";
	public  static final String   SEQUENCE_NAME = "SHIPMENT_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "SHIPMENT_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "SHIPMENT_TYPE", nullable = false, length = 1)
	private String shipmentType;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SHIP_DATE", nullable = false, length = 26)
	private Date shipDate;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@Column(name = "COST", precision = 10)
	private BigDecimal cost;

	@Column(name = "INVOICE_ID", length = 20)
	private String invoiceId;

	@Column(name = "FROM_ADDRESS", length = 80)
	private String fromAddress;

	@Column(name = "FROM_CITY", length = 20)
	private String fromCity;

	@Column(name = "FROM_STATE", length = 2)
	private String fromState;

	@Column(name = "FROM_COUNTRY", length = 2)
	private String fromCountry;

	@Column(name = "TO_ADDRESS", nullable = false, length = 80)
	private String toAddress;

	@Column(name = "TO_CITY", nullable = false, length = 20)
	private String toCity;

	@Column(name = "TO_STATE", length = 2)
	private String toState;

	@Column(name = "TO_COUNTRY", length = 2)
	private String toCountry;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shipment")
	private Set<ShipmentLine> shipmentLines = new HashSet<ShipmentLine>(0);
}
