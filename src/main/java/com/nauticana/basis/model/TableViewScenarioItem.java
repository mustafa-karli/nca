package com.nauticana.basis.model;
// Generated Apr 17, 2018 3:48:44 PM by Hibernate Tools 5.2.8.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TABLE_VIEW_SCENARIO_ITEM")
public class TableViewScenarioItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "scenarioId", column = @Column(name = "SCENARIO_ID", nullable = false)), @AttributeOverride(name = "seq", column = @Column(name = "SEQ", nullable = false)) })
	private TableViewScenarioItemId	id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCENARIO_ID", nullable = false, insertable = false, updatable = false)
	private TableViewScenario		tableViewScenario;

	@Column(name = "MASTER_TABLE", nullable = false, length = 30)
	private String					masterTable;

	@Column(name = "METHOD", nullable = false, length = 80)
	private String					method;

	@Column(name = "CHILD_TABLE", nullable = false, length = 30)
	private String					childTable;

	@Column(name = "ACCESS_TYPE", nullable = false, length = 1)
	private String					accessType;

	@Column(name = "PARENT_SEQ", nullable = false)
	private short					parentSeq;
}
