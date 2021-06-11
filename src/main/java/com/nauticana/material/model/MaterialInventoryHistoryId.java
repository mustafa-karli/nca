package com.nauticana.material.model;

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
public class MaterialInventoryHistoryId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int organizationId;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "PERIOD_END", nullable = false, length = 26)
	private Date periodEnd;

	public MaterialInventoryHistoryId(String keys) {
		String[] s = keys.split(",");
		this.organizationId = Integer.parseInt(s[0]);
		this.materialId = Integer.parseInt(s[1]);
		try {
			this.periodEnd = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.periodEnd = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.organizationId + "," + this.materialId + "," + Labels.dmyDF.format(periodEnd);
	}
}
