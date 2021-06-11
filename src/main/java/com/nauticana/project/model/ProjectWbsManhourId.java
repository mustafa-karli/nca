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
public class ProjectWbsManhourId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)
	private int projectId;

	@Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)
	private int categoryId;

	@Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)
	private int teamId;

	@Column(name = "WORKER_ID", nullable = false, precision = 8, scale = 0)
	private int workerId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "ACTIVITY_DATE", nullable = false, length = 7)
	private Date activityDate;

	public ProjectWbsManhourId(String keys) {
		String[] s = keys.split(",");
		this.projectId = Integer.parseInt(s[0]);
		this.categoryId = Integer.parseInt(s[1]);
		this.teamId = Integer.parseInt(s[2]);
		this.workerId = Integer.parseInt(s[3]);
		try {
			this.activityDate = Labels.dmyDF.parse(s[4]);
		} catch (ParseException e) {
			this.activityDate = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.projectId + "," + this.categoryId + "," + this.teamId + "," + this.workerId + "," + Labels.dmyDF.format(activityDate);
	}
}
