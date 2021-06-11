package com.nauticana.maintenance.model;

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
public class ServiceChargeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SERVICE_TYPE_ID", nullable = false)
	private int serviceTypeId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	public ServiceChargeId(String keys) {
		String[] s = keys.split(",");
		this.serviceTypeId = Integer.parseInt(s[0]);
		try {
			this.begda = Labels.dmyDF.parse(s[1]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.serviceTypeId + "," + Labels.dmyDF.format(begda);
	}
}
