package com.nauticana.basis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "NOTIFICATION_TYPE")
public class NotificationType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String	SEQUENCE_NAME		= "NOTIFICATION_TYPE_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "NOTIFICATION_TYPE_ID", unique = true, nullable = false)
	private int							id;

	@Column(name = "OWNER_ID", nullable = false)
	private int							ownerId;

	@Column(name = "TABLENAME", nullable = false, length = 30)
	private String						tablename;

	@Column(name = "STATUS_FIELD", nullable = false, length = 30)
	private String						statusField;

	@Column(name = "STOP_VALUE", nullable = false, length = 30)
	private String						stopValue;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notificationType")
	private Set<NotificationRecipient>	notificationRecipients	= new HashSet<NotificationRecipient>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notificationType")
	private Set<UserNotification>		userNotifications		= new HashSet<UserNotification>(0);
}
