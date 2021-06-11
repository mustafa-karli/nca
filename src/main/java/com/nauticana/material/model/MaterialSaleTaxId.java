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
public class MaterialSaleTaxId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	@Column(name = "TAX_ID", nullable = false, length = 8)
	private String taxId;

	public MaterialSaleTaxId(String keys) {
		String[] s = keys.split(",");
		this.materialId = Integer.parseInt(s[0]);
		try {
			this.begda = Labels.dmyDF.parse(s[1]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
		this.taxId = s[2];
	}

	@Override
	public String toString() {
		return this.materialId + "," + Labels.dmyDF.format(begda) + "," + this.taxId;
	}
}
