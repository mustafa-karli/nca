package com.nauticana.material.model;
// Generated Feb 20, 2018 9:43:30 AM by Hibernate Tools 5.2.8.Final

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
@Table(name = MaterialMovement.TABLE_NAME)
public class MaterialMovement implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "materialMovement";
	public  static final String   TABLE_NAME  = "MATERIAL_MOVEMENT";
	public  static final String   SEQUENCE_NAME = "MATERIAL_MOVEMENT_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "MM_TX_ID", unique = true, nullable = false)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@Column(name = "MOVE_TYPE", nullable = false)
	private short moveType;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@Column(name = "ORDER_TYPE", length = 1)
	private Character orderType;

	@Column(name = "ORDER_ID")
	private Integer orderId;

	@Column(name = "REVERSED", length = 1)
	private Character reversed;

	@Column(name = "REVERS_TX_ID")
	private Integer reversTxId;

	@Column(name = "FROM_TYPE", length = 1)
	private Character fromType;

	@Column(name = "FROM_ID")
	private Integer fromId;

	@Column(name = "TO_TYPE", length = 1)
	private Character toType;

	@Column(name = "TO_ID")
	private Integer toId;
}
