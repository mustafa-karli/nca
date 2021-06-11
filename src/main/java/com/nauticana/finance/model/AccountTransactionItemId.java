package com.nauticana.finance.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TRANSACTION_ID", nullable = false)
	private long transactionId;

	@Column(name = "ACCOUNT_SCHEMA_ID", nullable = false)
	private int accountSchemaId;

	public AccountTransactionItemId(String keys) {
		String[] s = keys.split(",");
		this.transactionId = Long.parseLong(s[0]);
		this.accountSchemaId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.transactionId + "," + this.accountSchemaId;
	}
}
