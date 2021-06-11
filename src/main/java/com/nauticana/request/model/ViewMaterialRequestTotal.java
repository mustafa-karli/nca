package com.nauticana.request.model;

public class ViewMaterialRequestTotal {

	private int		materialId;
	private String	caption;
	private String	purpose;
	private float	quantity;
	private String	unit;
	private String	orderLines;

	public ViewMaterialRequestTotal() {
	}

	public ViewMaterialRequestTotal(int materialId, String caption, String purpose, float quantity, String unit) {
		super();
		this.materialId = materialId;
		this.caption = caption;
		this.purpose = purpose;
		this.quantity = quantity;
		this.unit = unit;
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(String orderLines) {
		this.orderLines = orderLines;
	}

}
