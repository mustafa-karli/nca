package com.nauticana.purchase.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProposalToRfpItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PROPOSAL_ID", nullable = false)
	private int		proposalId;

	@Column(name = "RFP_ID", nullable = false)
	private int		rfpId;

	@Column(name = "LINE", nullable = false)
	private short	line;

	public ProposalToRfpItemId(String keys) {
		String[] s = keys.split(",");
		this.proposalId = Integer.parseInt(s[0]);
		this.rfpId = Integer.parseInt(s[1]);
		this.line = Short.parseShort(s[2]);
	}

	@Override
	public String toString() {
		return this.proposalId + "," + this.rfpId + "," + this.line;
	}
}
