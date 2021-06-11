package com.nauticana.finance.model;

import java.text.ParseException;
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
public class AccountBalanceId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ACCOUNT_SCHEMA_ID", nullable = false)
	private int accountSchemaId;

	@Column(name = "CURRENCY", nullable = false, length = 3)
	private String currency;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "REF_DATE", nullable = false, length = 26)
	private Date refDate;

	public AccountBalanceId(String keys) {
		String[] s = keys.split(",");
		this.accountSchemaId = Integer.parseInt(s[0]);
		this.currency = s[0];
		try {
			this.refDate = Labels.dmyDF.parse(s[1]);
		} catch (ParseException e) {
			this.refDate = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.accountSchemaId + "," + this.currency + "," + Labels.dmyDF.format(refDate);
	}
}
