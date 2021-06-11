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
public class PersonQualificationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PERSON_ID", nullable = false)
	private int personId;

	@Column(name = "QUALIFICATION_ID", nullable = false)
	private int qualificationId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	public PersonQualificationId(String keys) {
		String[] s = keys.split(",");
		this.personId = Integer.parseInt(s[0]);
		this.qualificationId = Integer.parseInt(s[1]);
		try {
			this.begda = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.personId + "," + this.qualificationId + "," + Labels.dmyDF.format(begda);
	}
}
