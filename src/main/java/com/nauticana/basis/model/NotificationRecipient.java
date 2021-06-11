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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "NOTIFICATION_RECIPIENT")
public class NotificationRecipient implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "notificationTypeId", column = @Column(name = "NOTIFICATION_TYPE_ID", nullable = false)),
		@AttributeOverride(name = "username", column = @Column(name = "USERNAME", nullable = false, length = 30)), @AttributeOverride(name = "event", column = @Column(name = "EVENT", nullable = false, length = 10)) })
	private NotificationRecipientId	id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOTIFICATION_TYPE_ID", nullable = false, insertable = false, updatable = false)
	private NotificationType		notificationType;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false, insertable = false, updatable = false)
	private UserAccount				userAccount;

	@Column(name = "ENABLE", nullable = false, length = 1)
	private String					enable;

}
