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
import javax.persistence.OneToOne;
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
@Table(name = SalesOrder.TABLE_NAME)
public class SalesOrder implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "salesOrder";
	public  static final String   TABLE_NAME  = "SALES_ORDER";
	public  static final String   SEQUENCE_NAME = "SALES_ORDER_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "SALES_ORDER_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "CUSTOMER_ID", nullable = false)
	private int customerId;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE", nullable = false, length = 26)
	private Date orderDate;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUE_DATE", length = 26)
	private Date dueDate;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@Column(name = "USERNAME", length = 30)
	private String username;

	@Column(name = "DESCRIPTION", length = 250)
	private String description;

	@Column(name = "DISCOUNT", precision = 10)
	private BigDecimal discount;

	@Column(name = "ADVANCE_PAYMENT", precision = 10)
	private BigDecimal advancePayment;

	@Column(name = "ONLINE_ORDER", length = 1)
	private Character onlineOrder;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "salesOrder")
	private SalesDeliveryAddress salesDeliveryAddress;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrder")
	private Set<SalesOrderItem> salesOrderItems = new HashSet<SalesOrderItem>(0);
}
