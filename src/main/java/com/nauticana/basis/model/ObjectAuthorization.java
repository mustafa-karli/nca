package com.nauticana.basis.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = ObjectAuthorization.TABLE_NAME)
public class ObjectAuthorization implements java.io.Serializable {
	
	public static final String   ROOTMAPPING = "objectAuthorization";
	public static final String   TABLE_NAME  = "OBJECT_AUTHORIZATION";

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "authorityGroup", column = @Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)),
			@AttributeOverride(name = "objectType", column = @Column(name = "OBJECT_TYPE", nullable = false, length = 30)),
			@AttributeOverride(name = "action", column = @Column(name = "ACTION", nullable = false, length = 30)),
			@AttributeOverride(name = "keyValue", column = @Column(name = "KEY_VALUE", nullable = false, length = 100)) })
	private ObjectAuthorizationId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHORITY_GROUP", nullable = false, insertable = false, updatable = false)
	private AuthorityGroup authorityGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OBJECT_TYPE", referencedColumnName = "OBJECT_TYPE", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "ACTION", referencedColumnName = "ACTION", nullable = false, insertable = false, updatable = false) })
	private AuthorityObjectAction authorityObjectAction;
	
}
