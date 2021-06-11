package com.nauticana.helpdesk.model;

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
@Table(name = "TICKET_EVENT")
public class TicketEvent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "ticketId", column = @Column(name = "TICKET_ID", nullable = false)),
		@AttributeOverride(name = "eventDate", column = @Column(name = "EVENT_DATE", nullable = false, length = 26)) })
	private TicketEventId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TICKET_ID", nullable = false, insertable = false, updatable = false)
	private SupportTicket supportTicket;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false)
	private UserAccount userAccount;

	@Column(name = "OLD_STATUS", nullable = false)
	private int oldStatus;

	@Column(name = "NEW_STATUS", nullable = false)
	private int newStatus;

	@Column(name = "TEXT_TYPE")
	private Integer textType;

	@Column(name = "DESCRIPTION", length = 2000)
	private String description;
}
