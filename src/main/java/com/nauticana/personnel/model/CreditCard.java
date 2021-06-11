package com.nauticana.personnel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CARD_NUMBER", unique = true, nullable = false, length = 24)
	private String	id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", nullable = false)
	private Person	person;

	@Column(name = "CVC2", nullable = false, length = 3)
	private String	cvc2;

	@Column(name = "EXPIRE_YEAR", nullable = false)
	private short	expireYear;

	@Column(name = "EXPIRE_MONTH", nullable = false)
	private short	expireMonth;

	@Column(name = "NAME_ON_CARD", nullable = false, length = 80)
	private String	nameOnCard;
}
