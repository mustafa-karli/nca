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
public class PositionAssignmentId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int organizationId;

	@Column(name = "POSITION", nullable = false, length = 20)
	private String position;

	@Column(name = "PERSON_ID", nullable = false)
	private int personId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "EMPLOYMENT", nullable = false, length = 26)
	private Date employment;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	public PositionAssignmentId(String keys) {
		String[] s = keys.split(",");
		this.ownerId = Integer.parseInt(s[0]);
		this.organizationId = Integer.parseInt(s[1]);
		this.position = s[2];
		this.personId = Integer.parseInt(s[3]);
		try {
			this.employment = Labels.dmyDF.parse(s[4]);
		} catch (ParseException e) {
			this.employment = new Date(System.currentTimeMillis());
		}
		try {
			this.begda = Labels.dmyDF.parse(s[5]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.ownerId + "," + this.organizationId + "," + this.position + "," + this.personId + "," + Labels.dmyDF.format(employment)  + "," + Labels.dmyDF.format(begda);
	}
}
