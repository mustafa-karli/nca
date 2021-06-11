package com.nauticana.basis.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = CaptionTranslation.TABLE_NAME)
public class CaptionTranslation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String   ROOTMAPPING = "captionTranslation";
	public static final String   TABLE_NAME  = "CAPTION_TRANSLATION";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "caption", column = @Column(name = "CAPTION", nullable = false, length = 30)),
			@AttributeOverride(name = "langcode", column = @Column(name = "LANGCODE", nullable = false, length = 2)) })
	private CaptionTranslationId id;

	@MapsId("language")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LANGCODE", nullable = false, insertable = false, updatable = false)
	private Language language;

	@Column(name = "LABELUPPER", nullable = false, length = 200)
	private String labelupper;

	@Column(name = "LABELLOWER", nullable = false, length = 200)
	private String labellower;
}
