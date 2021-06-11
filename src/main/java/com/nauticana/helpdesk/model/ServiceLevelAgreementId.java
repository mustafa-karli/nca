package com.nauticana.helpdesk.model;

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
public class ServiceLevelAgreementId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "BUSINESS_SERVICE_ID", nullable = false, length = 20)
	private String businessServiceId;

	@Column(name = "BUSINESS_PARTNER_ID", nullable = false)
	private int businessPartnerId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	public ServiceLevelAgreementId(String keys) {
		String[] s = keys.split(",");
		this.ownerId = Integer.parseInt(s[0]);
		this.businessServiceId = s[1];
		this.businessPartnerId = Integer.parseInt(s[2]);
		try {
			this.begda = Labels.dmyDF.parse(s[3]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.ownerId + "," + this.businessServiceId + "," + this.businessPartnerId + ","	+ Labels.dmyDF.format(begda);
	}
}
