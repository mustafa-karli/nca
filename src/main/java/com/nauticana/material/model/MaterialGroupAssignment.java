package com.nauticana.material.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = MaterialGroupAssignment.TABLE_NAME)
public class MaterialGroupAssignment implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "materialGroupAssignment";
	public  static final String   TABLE_NAME  = "MATERIAL_GROUP_ASSIGNMENT";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialGroupId", column = @Column(name = "MATERIAL_GROUP_ID", nullable = false)),
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)) })
	private MaterialGroupAssignmentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_GROUP_ID", nullable = false, insertable = false, updatable = false)
	private MaterialGroup materialGroup;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;
}
