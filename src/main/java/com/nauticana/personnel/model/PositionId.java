package com.nauticana.personnel.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PositionId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int ownerId;
	private int organizationId;
	private String position;

	public PositionId() {
	}

	public PositionId(int ownerId, int organizationId, String position) {
		this.ownerId = ownerId;
		this.organizationId = organizationId;
		this.position = position;
	}

	public PositionId(String keys) {
		String[] s = keys.split(",");
		this.ownerId = Integer.parseInt(s[0]);
		this.organizationId = Integer.parseInt(s[1]);
		this.position = s[2];
	}

	@Override
	public String toString() {
		return this.ownerId + "," + this.organizationId + "," + this.position;
	}

	@Column(name = "OWNER_ID", nullable = false)
	public int getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	@Column(name = "ORGANIZATION_ID", nullable = false)
	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "POSITION", nullable = false, length = 20)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PositionId))
			return false;
		PositionId castOther = (PositionId) other;

		return (this.getOwnerId() == castOther.getOwnerId())
				&& (this.getOrganizationId() == castOther.getOrganizationId())
				&& ((this.getPosition() == castOther.getPosition()) || (this.getPosition() != null
						&& castOther.getPosition() != null && this.getPosition().equals(castOther.getPosition())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getOwnerId();
		result = 37 * result + this.getOrganizationId();
		result = 37 * result + (getPosition() == null ? 0 : this.getPosition().hashCode());
		return result;
	}
}
