package com.nauticana.helpdesk.model;

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

import com.nauticana.basis.model.UserAccount;
import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = "SUPPORT_AGENT")
public class SupportAgent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "supportGroupId", column = @Column(name = "SUPPORT_GROUP_ID", nullable = false)),
			@AttributeOverride(name = "username", column = @Column(name = "USERNAME", nullable = false, length = 30)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private SupportAgentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPORT_GROUP_ID", nullable = false, insertable = false, updatable = false)
	private SupportGroup supportGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false, insertable = false, updatable = false)
	private UserAccount userAccount;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", length = 26)
	private Date endda;

	@Column(name = "SERVICE_DAYS", length = 1)
	private Character serviceDays;

	@Column(name = "SERVICE_HOURS", length = 1)
	private Character serviceHours;

	@Column(name = "WORK_SHIFT", length = 1)
	private Character workShift;
}
