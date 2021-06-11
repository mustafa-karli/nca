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
public class SupportAgentId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SUPPORT_GROUP_ID", nullable = false)
	private int supportGroupId;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	public SupportAgentId(String keys) {
		String[] s = keys.split(",");
		this.supportGroupId = Integer.parseInt(s[0]);
		this.username = s[1];
		try {
			this.begda = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.supportGroupId + "," + this.username + "," + Labels.dmyDF.format(begda);
	}
}
