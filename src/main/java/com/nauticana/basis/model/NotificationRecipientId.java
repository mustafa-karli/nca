package com.nauticana.basis.model;
// Generated Apr 10, 2018 1:40:53 PM by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRecipientId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "NOTIFICATION_TYPE_ID", nullable = false)
	private int		notificationTypeId;
	
	@Column(name = "USERNAME", nullable = false, length = 30)
	private String	username;

	@Column(name = "EVENT", nullable = false, length = 10)
	private String	event;

	public NotificationRecipientId(String keys) {
		String[] s = keys.split(",");
		this.notificationTypeId = Integer.parseInt(s[0]);
		this.username = s[1];
		this.event = s[2];
	}

	@Override
	public String toString() {
		return this.notificationTypeId + "," + this.username + "," + this.event;
	}
}
