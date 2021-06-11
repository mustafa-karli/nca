package com.nauticana.project.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTextId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)
	private int categoryId;

	@Column(name = "LANGCODE", nullable = false, length = 0)
	private String langcode;

	public CategoryTextId(String keys) {
		String[] s = keys.split(",");
		this.categoryId = Integer.parseInt(s[0]);
		this.langcode = s[1];
	}

	@Override
	public String toString() {
		return this.categoryId + "," + this.langcode;
	}


}
