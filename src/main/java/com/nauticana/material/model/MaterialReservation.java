package com.nauticana.material.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = MaterialReservation.TABLE_NAME)
public class MaterialReservation implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "materialReservation";
	public  static final String   TABLE_NAME  = "MATERIAL_RESERVATION";
	public  static final String   SEQUENCE_NAME = "MATERIAL_RESERVATION_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "RESERVATION_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@Column(name = "ORDER_TYPE", nullable = false, length = 1)
	private String orderType;

	@Column(name = "ORDER_ID", nullable = false)
	private int orderId;
}
