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
public class MaintenanceId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@Column(name = "SERIAL_NO", nullable = false, length = 20)
	private String serialNo;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Column(name = "SERVICE_DATE", nullable = false, length = 26)
	private Date serviceDate;

	public MaintenanceId(String keys) {
		String[] s = keys.split(",");
		this.materialId = Integer.parseInt(s[0]);
		this.serialNo = s[1];
		try {
			this.serviceDate = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.serviceDate = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.materialId + "," + this.serialNo + "," + Labels.dmyDF.format(serviceDate);
	}
}
