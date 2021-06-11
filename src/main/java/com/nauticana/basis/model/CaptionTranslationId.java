package com.nauticana.basis.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CaptionTranslationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;

	@Column(name = "LANGCODE", nullable = false, length = 2)
	private String langcode;

	public CaptionTranslationId(String keys) {
		String[] s = keys.split(",");
		this.caption = s[0];
		this.langcode = s[1];
	}

	@Override
	public String toString() {
		return this.caption + "," + this.langcode;
	}
}
