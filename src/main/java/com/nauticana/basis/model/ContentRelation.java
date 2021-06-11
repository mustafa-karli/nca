package com.nauticana.basis.model;

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
@Table(name = ContentRelation.TABLE_NAME)
public class ContentRelation implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "contentRelation";
	public  static final String   TABLE_NAME  = "CONTENT_RELATION";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "objectType", column = @Column(name = "OBJECT_TYPE", nullable = false, length = 2)),
			@AttributeOverride(name = "objectId", column = @Column(name = "OBJECT_ID", nullable = false)),
			@AttributeOverride(name = "contentId", column = @Column(name = "CONTENT_ID", nullable = false)) })
	private ContentRelationId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTENT_ID", nullable = false, insertable = false, updatable = false)
	private ContentData contentData;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@Column(name = "PURPOSE", nullable = false, length = 20)
	private String purpose;

	@Column(name = "PRIORITY", nullable = false)
	private short priority;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = false, length = 26)
	private Date createTime;

}
