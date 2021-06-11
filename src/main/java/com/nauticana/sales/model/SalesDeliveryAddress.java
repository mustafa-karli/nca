package com.nauticana.sales.model;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = SalesDeliveryAddress.TABLE_NAME)
public class SalesDeliveryAddress implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "salesDeliveryAddress";
	public  static final String   TABLE_NAME  = "SALES_DELIVERY_ADDRESS";


	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "salesOrder"))
	@GeneratedValue(generator = "generator")
	@Column(name = "SALES_ORDER_ID", unique = true, nullable = false)
	private int id;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private SalesOrder salesOrder;

	@Column(name = "ADDRESS", nullable = false, length = 80)
	private String address;

	@Column(name = "CITY", nullable = false, length = 20)
	private String city;

	@Column(name = "STATE", length = 2)
	private String state;

	@Column(name = "COUNTRY", length = 2)
	private String country;

	@Column(name = "LONGITUDE", precision = 64, scale = 0)
	private Double longitude;

	@Column(name = "LATITUDE", precision = 64, scale = 0)
	private Double latitude;

	@Column(name = "ALTITUDE", precision = 64, scale = 0)
	private Double altitude;
}
