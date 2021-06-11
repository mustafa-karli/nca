package com.nauticana.basis.model;

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
public class UserAuthorizationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)
	private String authorityGroup;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Column(name = "BEGDA", nullable = false)
	private Date   begda;

	public UserAuthorizationId(String keys) {
		String[] s = keys.split(",");
		this.username = s[0];
		this.authorityGroup = s[1];
		try {
			this.begda = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.username + "," + this.authorityGroup + "," + Labels.dmyDF.format(begda);
	}

}
