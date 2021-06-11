package com.nauticana.maintenance.model;
// Generated Feb 20, 2018 9:43:30 AM by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EquipmentId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int materialId;
	private String serialNo;

	public EquipmentId() {
	}

	public EquipmentId(int materialId, String serialNo) {
		this.materialId = materialId;
		this.serialNo = serialNo;
	}

	public EquipmentId(String keys) {
		String[] s = keys.split(",");
		this.materialId = Integer.parseInt(s[0]);
		this.serialNo = s[1];
	}

	@Override
	public String toString() {
		return this.materialId + "," + this.serialNo;
	}

	@Column(name = "MATERIAL_ID", nullable = false)
	public int getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	@Column(name = "SERIAL_NO", nullable = false, length = 20)
	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EquipmentId))
			return false;
		EquipmentId castOther = (EquipmentId) other;

		return (this.getMaterialId() == castOther.getMaterialId())
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this.getSerialNo() != null
						&& castOther.getSerialNo() != null && this.getSerialNo().equals(castOther.getSerialNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMaterialId();
		result = 37 * result + (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		return result;
	}

}
