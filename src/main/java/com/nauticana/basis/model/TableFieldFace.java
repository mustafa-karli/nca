package com.nauticana.basis.model;
// Generated Mar 19, 2018 1:39:18 PM by Hibernate Tools 5.2.8.Final

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
@Table(name = "TABLE_FIELD_FACE", schema = "PUBLIC", catalog = "PUBLIC")
public class TableFieldFace implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "tablename", column = @Column(name = "TABLENAME", nullable = false, length = 30)),
			@AttributeOverride(name = "fieldname", column = @Column(name = "FIELDNAME", nullable = false, length = 30)) })
	private TableFieldFaceId		id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLENAME", nullable = false, insertable = false, updatable = false)
	private TableControllerStatic	tableControllerStatic;

	@Column(name = "EDIT_STYLE", nullable = false, length = 30)
	private String					editStyle;

	@Column(name = "EDIT_JSTL_PATH", nullable = false, length = 80)
	private String					editJstlPath;

	@Column(name = "VIEW_JSTL_PATH", nullable = false, length = 80)
	private String					viewJstlPath;

	@Column(name = "SEARCH_STYLE", nullable = false, length = 1)
	private String					searchStyle;

	@Column(name = "LOOKUP_STYLE", nullable = false, length = 1)
	private String					lookupStyle;

	@Column(name = "TRANSLATED", nullable = false, length = 1)
	private String					translated;

	@Column(name = "MINVALUE", length = 30)
	private String					minvalue;

	@Column(name = "MAXVALUE", length = 30)
	private String					maxvalue;
}
