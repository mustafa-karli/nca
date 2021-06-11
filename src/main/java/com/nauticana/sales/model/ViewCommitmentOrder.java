package com.nauticana.sales.model;

import java.util.Date;

public class ViewCommitmentOrder {

	private int initialSequence;
	private double quantity;
	private double unitPrice;
	private double discount;
	private String currency;
	private String status;
	private int salesOrderId;
	private Date orderDate;
	private double advancePayment;
	private String description;
	private String caption;
	private String username;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String address;
	private String city;
	private String state;
	private String country;
	
	public ViewCommitmentOrder(int initialSequence, double quantity, double unitPrice, double discount, String currency, String status, int salesOrderId, Date orderDate, double advancePayment, String description, String caption,
			String username, String firstName, String lastName, String emailAddress, String address, String city, String state, String country) {
		super();
		this.initialSequence = initialSequence;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.currency = currency;
		this.status = status;
		this.salesOrderId = salesOrderId;
		this.orderDate = orderDate;
		this.advancePayment = advancePayment;
		this.description = description;
		this.caption = caption;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public int getInitialSequence() {
		return initialSequence;
	}

	public double getQuantity() {
		return quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public String getCurrency() {
		return currency;
	}

	public String getStatus() {
		return status;
	}

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public double getAdvancePayment() {
		return advancePayment;
	}

	public String getDescription() {
		return description;
	}

	public String getCaption() {
		return caption;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	
	
}
