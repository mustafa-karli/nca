package com.nauticana.basis.model;
// Generated Mar 26, 2018 9:44:28 AM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nauticana.basis.utils.Labels;
import com.nauticana.helpdesk.model.SupportAgent;
import com.nauticana.helpdesk.model.SupportTicket;
import com.nauticana.helpdesk.model.TicketEvent;
import com.nauticana.personnel.model.UserAccountOwner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 30)
	private String						id;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String						status;

	@Column(name = "FIRST_NAME", nullable = false, length = 40)
	private String						firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 40)
	private String						lastName;

	@Column(name = "EMAIL_ADDRESS", length = 80)
	private String						emailAddress;

	@Column(name = "PASSTEXT", length = 128)
	@DateTimeFormat(pattern = Labels.dmyFullStr)
//	@Temporal(TemporalType.TIMESTAMP)
	private String						passtext;

	@Column(name = "PASSDATE", length = 26)
	private Date						passdate;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTLOGON", columnDefinition = "TIMESTAMP")
	private Date						lastlogon;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private Set<TicketEvent>			ticketEvents					= new HashSet<TicketEvent>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private Set<UserAuthorization>		userAuthorizations				= new HashSet<UserAuthorization>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private Set<UserFavorite>			userFavorites					= new HashSet<UserFavorite>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private Set<UserNotification>		userNotifications				= new HashSet<UserNotification>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private Set<UserAccountOwner>		userAccountOwners				= new HashSet<UserAccountOwner>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private Set<SupportAgent>			supportAgents					= new HashSet<SupportAgent>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supportUser")
	private Set<SupportTicket>			supportTicketsForSupportUser	= new HashSet<SupportTicket>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private Set<SupportTicket>			supportTicketsForUsername		= new HashSet<SupportTicket>(0);

	public boolean checkPassword(String password) {
		if (this.passtext == null) return false;
		return this.passtext.equals(password);
	}
}
