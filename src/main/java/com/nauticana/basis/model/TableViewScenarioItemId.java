package com.nauticana.basis.model;
// Generated Apr 17, 2018 3:48:44 PM by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TableViewScenarioItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SCENARIO_ID", nullable = false)
	private int		scenarioId;

	@Column(name = "SEQ", nullable = false)
	private short	seq;

	public TableViewScenarioItemId(String keys) {
		String[] s = keys.split(",");
		this.scenarioId = Integer.parseInt(s[0]);
		this.seq = Short.parseShort(s[1]);
	}

	@Override
	public String toString() {
		return this.scenarioId + "," + this.seq;
	}
}
