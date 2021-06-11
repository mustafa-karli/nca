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
public class EquipmentAssignmentId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PERSON_ID", nullable = false)
	private int personId;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@Column(name = "SERIAL_NO", nullable = false, length = 20)
	private String serialNo;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	public EquipmentAssignmentId(String keys) {
		String[] s = keys.split(",");
		this.personId = Integer.parseInt(s[0]);
		this.materialId = Integer.parseInt(s[1]);
		this.serialNo = s[2];
		try {
			this.begda = Labels.dmyDF.parse(s[3]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.personId + "," + this.materialId + "," + this.serialNo + "," + Labels.dmyDF.format(begda);
	}
}
