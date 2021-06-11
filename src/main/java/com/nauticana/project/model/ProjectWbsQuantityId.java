package com.nauticana.project.model;

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
public class ProjectWbsQuantityId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)
	private int projectId;

	@Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)
	private int categoryId;

	@Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)
	private int teamId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "BEGDA", nullable = false)
	private Date begda;

	public ProjectWbsQuantityId(String keys) {
		String[] s = keys.split(",");
		this.projectId = Integer.parseInt(s[0]);
		this.categoryId = Integer.parseInt(s[1]);
		try {
			this.begda = Labels.dmyDF.parse(s[2]);
		} catch (ParseException e) {
			this.begda = new Date(System.currentTimeMillis());
		}
	}
	
	@Override
	public String toString() {
		return this.projectId + "," + this.categoryId + "," + this.teamId + "," + Labels.dmyDF.format(begda);
	}
}
