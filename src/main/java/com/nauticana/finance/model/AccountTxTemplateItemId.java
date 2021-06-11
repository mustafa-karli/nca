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
public class AccountTxTemplateItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TEMPLATE_ID", nullable = false)
	private int templateId;

	@Column(name = "ACCOUNT_SCHEMA_ID", nullable = false)
	private int accountSchemaId;

	public AccountTxTemplateItemId(String keys) {
		String[] s = keys.split(",");
		this.templateId = Integer.parseInt(s[0]);
		this.accountSchemaId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.templateId + "," + this.accountSchemaId;
	}

}
