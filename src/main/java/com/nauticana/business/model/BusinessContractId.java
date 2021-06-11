package com.nauticana.business.model;

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
public class BusinessContractId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BUSINESS_PARTNER_ID", nullable = false)
	private int businessPartnerId;

	@Column(name = "BUSINESS_SERVICE_ID", nullable = false, length = 20)
	private String businessServiceId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	public BusinessContractId(String keys) {
		String[] s = keys.split(",");
		this.businessPartnerId = Integer.parseInt(s[0]);
		this.businessServiceId = s[1];
		try {
			this.begda = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.businessPartnerId + "," + this.businessServiceId + "," + Labels.dmyDF.format(begda);
	}
}
