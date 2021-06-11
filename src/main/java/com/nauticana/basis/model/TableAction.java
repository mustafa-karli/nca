package com.nauticana.basis.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TABLE_ACTION")
public class TableAction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "tablename", column = @Column(name = "TABLENAME", nullable = false, length = 30)), @AttributeOverride(name = "action", column = @Column(name = "ACTION", nullable = false, length = 30)) })
	private TableActionId			id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLENAME", nullable = false, insertable = false, updatable = false)
	private TableControllerStatic	tableControllerStatic;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String					caption;

	@Column(name = "METHOD", nullable = false, length = 30)
	private String					method;

	@Column(name = "ENABLE", nullable = false, length = 1)
	private String					enable;

	@Column(name = "AUTHORITY_CHECK", nullable = false, length = 1)
	private String					authorityCheck;

	@Column(name = "RECORD_SPECIFIC", nullable = false, length = 1)
	private String					recordSpecific;

}
