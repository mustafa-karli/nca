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
public class UserFavoriteId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String	username;

	@Column(name = "FAVORITE_TYPE", nullable = false, length = 2)
	private String	favoriteType;

	@Column(name = "OBJECT_ID", nullable = false, length = 30)
	private String	objectId;

	public UserFavoriteId(String keys) {
		String[] s = keys.split(",");
		this.username = s[0];
		this.favoriteType = s[1];
		this.objectId = s[2];
	}

	@Override
	public String toString() {
		return this.username + "," + this.favoriteType + "," + this.objectId;
	}
}
