package com.nauticana.basis.model;
// Generated Apr 3, 2018 2:42:59 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_NOTIFICATION", schema = "PUBLIC", catalog = "PUBLIC")
public class UserNotification implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public  static final String   SEQUENCE_NAME = "USER_NOTIFICATION_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "NOTIFICATION_ID", unique = true, nullable = false)
	private long				id;

	@JsonBackReference 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOTIFICATION_TYPE_ID", nullable = false)
	private NotificationType	notificationType;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false)
	private UserAccount			userAccount;

	@Column(name = "OBJECT_ID", nullable = false, length = 30)
	private String				objectId;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String				status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RAISE_DATE", nullable = false, length = 26)
	private Date				raiseDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUE_DATE", nullable = false, length = 26)
	private Date				dueDate;

	@Column(name = "DESCRIPTION", length = 250)
	private String				description;
}
