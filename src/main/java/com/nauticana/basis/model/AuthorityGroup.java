package com.nauticana.basis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = AuthorityGroup.TABLE_NAME)
public class AuthorityGroup implements java.io.Serializable {

	public static final String   ROOTMAPPING = "authorityGroup";
	public static final String   TABLE_NAME  = "AUTHORITY_GROUP";
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AUTHORITY_GROUP", unique = true, nullable = false, length = 30)
	private String id;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authorityGroup")
	private Set<UserAuthorization> userAuthorizations = new HashSet<UserAuthorization>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authorityGroup")
	private Set<ObjectAuthorization> objectAuthorizations = new HashSet<ObjectAuthorization>(0);

}
