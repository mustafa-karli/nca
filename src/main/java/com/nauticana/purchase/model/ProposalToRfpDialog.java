package com.nauticana.purchase.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PROPOSAL_TO_RFP_DIALOG")
public class ProposalToRfpDialog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "proposalId", column = @Column(name = "PROPOSAL_ID", nullable = false)),
		@AttributeOverride(name = "dtime", column = @Column(name = "DTIME", nullable = false)) })
	private ProposalToRfpDialogId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROPOSAL_ID", nullable = false, insertable = false, updatable = false)
	private ProposalToRfp proposalToRfp;

	@Column(name = "DTEXT", nullable = false, length = 250)
	private String dtext;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;
}
