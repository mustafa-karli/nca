package com.nauticana.shipment.model;

import java.math.BigDecimal;
import java.util.Date;

public class ViewShipmentLineStatus {

	private int        shipmentId;
	private char       shipmentType;
	private Date       shipDate;
	private char       status;
	private String     username;

	private String     invoiceId;
	private String     sourceAddress;
	private String     destinationAddress;

	private int        materialId;
	private String     caption;
	private BigDecimal quantity;
	private String     unit;
	private Character  refOrderType;
	private Integer    refOrderId;
	
	public ViewShipmentLineStatus(int shipmentId, char shipmentType, Date shipDate, char status, String username,
			String invoiceId, String sourceAddress, String destinationAddress, int materialId, String caption,
			BigDecimal quantity, String unit, Character refOrderType, Integer refOrderId) {
		super();
		this.shipmentId = shipmentId;
		this.shipmentType = shipmentType;
		this.shipDate = shipDate;
		this.status = status;
		this.username = username;
		this.invoiceId = invoiceId;
		this.sourceAddress = sourceAddress;
		this.destinationAddress = destinationAddress;
		this.materialId = materialId;
		this.caption = caption;
		this.quantity = quantity;
		this.unit = unit;
		this.refOrderType = refOrderType;
		this.refOrderId = refOrderId;
	}

	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public char getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(char shipmentType) {
		this.shipmentType = shipmentType;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Character getRefOrderType() {
		return refOrderType;
	}

	public void setRefOrderType(Character refOrderType) {
		this.refOrderType = refOrderType;
	}

	public Integer getRefOrderId() {
		return refOrderId;
	}

	public void setRefOrderId(Integer refOrderId) {
		this.refOrderId = refOrderId;
	}


}
