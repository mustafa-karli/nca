package com.nauticana.sales.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class ViewCommitment {

	private int						materialId;
	private int						manufacturerId;
	private int						businessPartnerId;
	private int						ownerId;
	private String					caption;
	private String					vendorCaption;
	private String					partNumber;
	private Date					orderDeadLine;
	private Date					deliveryPromise;
	private int						minQuantity;
	private int						maxQuantity;
	private String					unit;
	private BigDecimal				startPrice;
	private String					currency;
	private int						currentOrder;
	private BigDecimal				currentPrice;
	private double					discountPercent;

	private int						salesOrderId;
	private int						salesQuantity;
	private int						initialSequence;
	private BigDecimal				unitPrice;
	private BigDecimal				initialPrice;
	private String					status;

	private ArrayList<Integer>		quantities;
	private ArrayList<BigDecimal>	prices;
	
	public ViewCommitment(int materialId, int manufacturerId, int businessPartnerId, int ownerId, String caption, String vendorCaption, String partNumber, Date orderDeadLine, Date deliveryPromise, int minQuantity, int maxQuantity,
			String unit, BigDecimal startPrice, String currency, int currentOrder, BigDecimal currentPrice, String status) {
		super();
		this.materialId = materialId;
		this.manufacturerId = manufacturerId;
		this.businessPartnerId = businessPartnerId;
		this.ownerId = ownerId;
		this.caption = caption;
		this.vendorCaption = vendorCaption;
		this.partNumber = partNumber;
		this.orderDeadLine = orderDeadLine;
		this.deliveryPromise = deliveryPromise;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
		this.unit = unit;
		this.startPrice = startPrice;
		this.currency = currency;
		this.currentOrder = currentOrder;
		this.currentPrice = currentPrice;
		this.status = status;
		this.discountPercent = 100 * (startPrice.doubleValue()-currentPrice.doubleValue()) / startPrice.doubleValue();
	}

	public int getMaterialId() {
		return materialId;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public int getBusinessPartnerId() {
		return businessPartnerId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public String getCaption() {
		return caption;
	}

	public String getVendorCaption() {
		return vendorCaption;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public Date getOrderDeadLine() {
		return orderDeadLine;
	}

	public Date getDeliveryPromise() {
		return deliveryPromise;
	}

	public int getMinQuantity() {
		return minQuantity;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public String getUnit() {
		return unit;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public int getCurrentOrder() {
		return currentOrder;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public ArrayList<Integer> getQuantities() {
		return quantities;
	}

	public ArrayList<BigDecimal> getPrices() {
		return prices;
	}

	public void addDiscount(int quantity, BigDecimal price) {
		if (this.quantities == null) {
			this.quantities = new ArrayList<Integer>();
			this.prices = new ArrayList<BigDecimal>();
		}
		this.quantities.add(quantity);
		this.prices.add(price);
	}

	
	
	public int getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	public int getInitialSequence() {
		return initialSequence;
	}

	public void setInitialSequence(int initialSequence) {
		this.initialSequence = initialSequence;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
