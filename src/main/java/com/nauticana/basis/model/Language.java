package com.nauticana.basis.model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Language.TABLE_NAME)
public class Language implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String   ROOTMAPPING = "language";
	public static final String   TABLE_NAME  = "LANGUAGE";

	@Id
	@Column(name = "LANGCODE", unique = true, nullable = false, length = 2)
	private String id;

	@OrderBy("CAPTION DESC")
	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;

	@Column(name = "LOCALE_STR", nullable = false, length = 5)
	private String localeStr;

	@Column(name = "DIRECTION", nullable = false, length = 10)
	private String direction;

	@Column(name = "FLAG", nullable = false, length = 30)
	private String flag;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
	private Set<CaptionTranslation> captionTranslations = new HashSet<CaptionTranslation>(0);

	@Transient
	public Hashtable<String, String> translations;

	@Transient
	public String getText(String caption) {
		return translations.get(caption);
	}
}
