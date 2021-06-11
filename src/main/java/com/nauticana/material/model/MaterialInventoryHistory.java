package com.nauticana.material.model;
// Generated Feb 20, 2018 9:43:30 AM by Hibernate Tools 5.2.8.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nauticana.personnel.model.Organization;

import lombok.Data;

@Data
@Entity
@Table(name = MaterialInventoryHistory.TABLE_NAME)
public class MaterialInventoryHistory implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "materialInventoryHistory";
	public  static final String   TABLE_NAME  = "MATERIAL_INVENTORY_HISTORY";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)),
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "periodEnd", column = @Column(name = "PERIOD_END", nullable = false, length = 26)) })
	private MaterialInventoryHistoryId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false)
	private Organization organization;

	@Column(name = "YEAR")
	private Short year;

	@Column(name = "QUARTER")
	private Byte quarter;

	@Column(name = "MONTH")
	private Byte month;

	@Column(name = "WEEK")
	private Byte week;

	public MaterialInventoryHistoryId getId() {
		return this.id;
	}
}
