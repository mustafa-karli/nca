package com.nauticana.maintenance.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;
import com.nauticana.personnel.model.Organization;

import lombok.Data;

@Data
@Entity
@Table(name = EquipmentLocation.TABLE_NAME)
public class EquipmentLocation implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "equipmentLocation";
	public  static final String   TABLE_NAME  = "EQUIPMENT_LOCATION";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)) })
	private EquipmentLocationId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SERIAL_NO", referencedColumnName = "SERIAL_NO", nullable = false, insertable = false, updatable = false) })
	private Equipment equipment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false)
	private Organization organization;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", nullable = false, length = 26)
	private Date endda;
}
