package com.nauticana.purchase.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProposalToRfpDialogId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PROPOSAL_ID", nullable = false)
	private int		proposalId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "DTIME", nullable = false, length = 26)
	private Date	dtime;

	public ProposalToRfpDialogId(String keys) {
		String[] s = keys.split(",");
		this.proposalId = Integer.parseInt(s[0]);
		try {
			this.dtime = Labels.dmyDF.parse(s[1]);
		} catch (Exception e) {
			this.dtime = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.proposalId + "," + Labels.dmyDF.format(dtime);
	}
}
