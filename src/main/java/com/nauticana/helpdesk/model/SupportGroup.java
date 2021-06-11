package com.nauticana.helpdesk.model;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "SUPPORT_GROUP")
public class SupportGroup implements java.io.Serializable {

	public static final String SEQUENCE_NAME = "SUPPORT_GROUP_ID_SEQ";
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "SUPPORT_GROUP_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supportGroup")
	private Set<SupportAgent> supportAgents = new HashSet<SupportAgent>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supportGroup")
	private Set<SupportTicket> supportTickets = new HashSet<SupportTicket>(0);
}
