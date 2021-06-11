package com.nauticana.helpdesk.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.model.UserAccount;
import com.nauticana.basis.utils.Labels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "SUPPORT_TICKET")
public class SupportTicket implements java.io.Serializable {

	public static final String SEQUENCE_NAME = "SUPPORT_TICKET_ID_SEQ";
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "TICKET_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPORT_AREA_ID", nullable = false)
	private SupportArea supportArea;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPORT_GROUP_ID")
	private SupportGroup supportGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPORT_USER")
	private UserAccount supportUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false)
	private UserAccount userAccount;

	@Column(name = "ASSET_NAME", nullable = false, length = 80)
	private String assetName;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ISSUE_DATE", nullable = false, length = 26)
	private Date issueDate;

	@Column(name = "PRIORITY", nullable = false, length = 1)
	private String priority;

	@Column(name = "TICKET_TYPE", nullable = false, length = 1)
	private String ticketType;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supportTicket")
	private Set<TicketEvent> ticketEvents = new HashSet<TicketEvent>(0);
}
