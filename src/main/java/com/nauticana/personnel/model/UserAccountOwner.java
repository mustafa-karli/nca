package com.nauticana.personnel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nauticana.basis.model.UserAccount;

import lombok.Data;

@Data
@Entity
@Table(name = UserAccountOwner.TABLE_NAME)
public class UserAccountOwner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String   ROOTMAPPING = "userAccountOwner";
	public static final String   TABLE_NAME  = "USER_ACCOUNT_OWNER";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "username", column = @Column(name = "USERNAME", nullable = false, length = 30)),
			@AttributeOverride(name = "personId", column = @Column(name = "PERSON_ID", nullable = false)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private UserAccountOwnerId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", nullable = false, insertable = false, updatable = false)
	private Person person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false, insertable = false, updatable = false)
	private UserAccount userAccount;
}
