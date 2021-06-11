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
@Table(name = "TABLE_CONTENT_TYPE", schema = "PUBLIC", catalog = "PUBLIC")
public class TableContentType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "tablename", column = @Column(name = "TABLENAME", nullable = false, length = 30)),
			@AttributeOverride(name = "objectType", column = @Column(name = "OBJECT_TYPE", nullable = false, length = 2)) })
	private TableContentTypeId		id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLENAME", nullable = false, insertable = false, updatable = false)
	private TableControllerStatic	tableControllerStatic;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String					caption;

	@Column(name = "MIMETYPE", length = 200)
	private String					mimetype;
}
