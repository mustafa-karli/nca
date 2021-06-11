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
public class ExchangeRateId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "CDATE", nullable = false, length = 26)
	private Date cdate;

	@Column(name = "CURR1", nullable = false, length = 3)
	private String curr1;

	@Column(name = "CURR2", nullable = false, length = 3)
	private String curr2;

	public ExchangeRateId(String keys) {
		String[] s = keys.split(",");
		try {
			this.cdate = Labels.dmyDF.parse(s[0]);
		} catch (ParseException e) {
			this.cdate = new Date(System.currentTimeMillis());
		}
		this.curr1 = s[1];
		this.curr2 = s[2];
	}

	@Override
	public String toString() {
		return Labels.dmyDF.format(this.cdate) + "," + this.curr1 + "," + this.curr2;
	}
}
