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
import com.nauticana.personnel.model.Person;

import lombok.Data;

@Data
@Entity
@Table(name = EquipmentAssignment.TABLE_NAME)
public class EquipmentAssignment implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "equipmentAssignment";
	public  static final String   TABLE_NAME  = "EQUIPMENT_ASSIGNMENT";

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "personId", column = @Column(name = "PERSON_ID", nullable = false)),
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private EquipmentAssignmentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SERIAL_NO", referencedColumnName = "SERIAL_NO", nullable = false, insertable = false, updatable = false) })
	private Equipment equipment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", nullable = false, insertable = false, updatable = false)
	private Person person;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", nullable = false, length = 26)
	private Date endda;

	@Column(name = "ASSIGNMENT_TYPE", length = 1)
	private Character assignmentType;
}
