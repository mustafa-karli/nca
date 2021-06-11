package com.nauticana.basis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = AuthorityObject.TABLE_NAME)
public class AuthorityObject implements java.io.Serializable {

	public static final String   ROOTMAPPING = "authorityObject";
	public static final String   TABLE_NAME  = "AUTHORITY_OBJECT";
	private static final long serialVersionUID = 1L;

	@Id
	@OrderBy("OBJECT_TYPE")
	@Column(name="OBJECT_TYPE", length=30)
	private String id;
	
	@OrderBy("ACTION")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authorityObject")
	private Set<AuthorityObjectAction> authorityObjectActions = new HashSet<AuthorityObjectAction>(0);
}
