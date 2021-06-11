package com.nauticana.basis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = AuthorityObjectAction.TABLE_NAME)
public class AuthorityObjectAction implements java.io.Serializable {

	public static final String   ROOTMAPPING = "authorityObjectAction";
	public static final String   TABLE_NAME  = "AUTHORITY_OBJECT_ACTION";

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "objectType", column = @Column(name = "OBJECT_TYPE", nullable = false, length = 30)),
			@AttributeOverride(name = "action", column = @Column(name = "ACTION", nullable = false, length = 30)) })
	private AuthorityObjectActionId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OBJECT_TYPE", nullable = false, insertable = false, updatable = false)
	private AuthorityObject authorityObject;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authorityObjectAction")
	private Set<ObjectAuthorization> objectAuthorizations = new HashSet<ObjectAuthorization>(0);

}
