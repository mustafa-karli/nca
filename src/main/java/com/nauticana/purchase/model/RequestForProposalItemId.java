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
public class RequestForProposalItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "RFP_ID", nullable = false)
	private int		rfpId;

	@Column(name = "LINE", nullable = false)
	private short	line;

	public RequestForProposalItemId(String keys) {
		String[] s = keys.split(",");
		this.rfpId = Integer.parseInt(s[0]);
		this.line = Short.parseShort(s[1]);
	}

	@Override
	public String toString() {
		return this.rfpId + "," + this.line;
	}
}
