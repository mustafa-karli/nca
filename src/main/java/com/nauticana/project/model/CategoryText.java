package com.nauticana.project.model;

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
@Table(name = CategoryText.TABLE_NAME)
public class CategoryText implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String   ROOTMAPPING = "categoryText";
	public static final String   TABLE_NAME  = "CATEGORY_TEXT";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "caption", column = @Column(name = "CAPTION", nullable = false, length = 200)),
			@AttributeOverride(name = "langcode", column = @Column(name = "LANGCODE", nullable = false, length = 2)) })
	private CategoryTextId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
	private Category category;

	@Column(name = "CAPTION", nullable = false, length = 100)
	private String caption;
}
