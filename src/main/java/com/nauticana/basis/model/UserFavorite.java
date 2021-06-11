package com.nauticana.basis.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_FAVORITE")
public class UserFavorite implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "username", column = @Column(name = "USERNAME", nullable = false, length = 30)),
		@AttributeOverride(name = "favoriteType", column = @Column(name = "FAVORITE_TYPE", nullable = false, length = 2)),
		@AttributeOverride(name = "objectId", column = @Column(name = "OBJECT_ID", nullable = false, length = 30)) })
	private UserFavoriteId	id;
	
	@Column(name = "DESCRIPTION", length = 80)
	private String			description;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false, insertable = false, updatable = false)
	private UserAccount		userAccount;

}
