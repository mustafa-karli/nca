package com.nauticana.basis.model;
// Generated Apr 17, 2018 3:48:44 PM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "TABLE_VIEW_SCENARIO")
public class TableViewScenario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SCENARIO_ID", unique = true, nullable = false)
	private int							id;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String						caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tableViewScenario")
	private Set<TableViewScenarioItem>	tableViewScenarioItems	= new HashSet<TableViewScenarioItem>(0);
}
