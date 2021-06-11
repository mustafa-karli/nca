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
public class ContentRelationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "OBJECT_TYPE", nullable = false, length = 2)
	private String objectType;

	@Column(name = "OBJECT_ID", nullable = false)
	private int objectId;

	@Column(name = "CONTENT_ID", nullable = false)
	private long contentId;

	public ContentRelationId(String keys) {
		String[] s = keys.split(",");
		this.objectType = s[0];
		this.objectId = Integer.parseInt(s[1]);
		this.contentId = Long.parseLong(s[1]);
	}

	@Override
	public String toString() {
		return this.objectType + "," + this.objectId + "," + this.contentId;
	}

}
