package com.nauticana.basis.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = UserAuthorization.TABLE_NAME)
public class UserAuthorization implements java.io.Serializable {

	public static final String   ROOTMAPPING = "userAuthorization";
	public static final String   TABLE_NAME  = "USER_AUTHORIZATION";

	private static final long serialVersionUID = 1L;

    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="username", column=@Column(name="USERNAME", nullable=false, length=30) ), 
        @AttributeOverride(name="authorityGroup", column=@Column(name="AUTHORITY_GROUP", nullable=false, length=30) ) } )
	private UserAuthorizationId id;

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="AUTHORITY_GROUP", nullable=false, insertable=false, updatable=false)
	private AuthorityGroup authorityGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false, insertable = false, updatable = false)
	private UserAccount userAccount;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", length = 26)
	private Date endda;

}
