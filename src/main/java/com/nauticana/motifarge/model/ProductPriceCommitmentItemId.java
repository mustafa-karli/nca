package com.nauticana.motifarge.model;

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
public class ProductPriceCommitmentItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@Column(name = "BUSINESS_PARTNER_ID", nullable = false)
	private int businessPartnerId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "ORDER_DEAD_LINE", nullable = false, length = 26)
	private Date orderDeadLine;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	public ProductPriceCommitmentItemId(String keys) {
		String[] s = keys.split(",");
		this.materialId = Integer.parseInt(s[0]);
		this.businessPartnerId = Integer.parseInt(s[1]);
		try {
			this.orderDeadLine = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.orderDeadLine = new Date(System.currentTimeMillis());
		}
		this.quantity = Integer.parseInt(s[3]);
	}

	@Override
	public String toString() {
		return this.materialId + "," + this.businessPartnerId + "," + Labels.dmyDF.format(orderDeadLine) + "," +  this.quantity;
	}
}
