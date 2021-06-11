package com.nauticana.personnel.model;

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
public class EmployeeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PERSON_ID", nullable = false)
	private int personId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "EMPLOYMENT", nullable = false, length = 26)
	private Date employment;

	public EmployeeId(String keys) {
		String[] s = keys.split(",");
		this.personId = Integer.parseInt(s[0]);
		try {
			this.employment = Labels.dmyDF.parse(s[1]);
		} catch (ParseException e) {
			this.employment = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.personId + "," + Labels.dmyDF.format(employment);
	}
}
