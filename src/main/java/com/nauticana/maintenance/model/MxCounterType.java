package com.nauticana.maintenance.model;

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
@Table(name = MxCounterType.TABLE_NAME)
public class MxCounterType implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "mxCounterType";
	public  static final String   TABLE_NAME  = "MX_COUNTER_TYPE";

	@Id
	@Column(name = "COUNTER_TYPE", unique = true, nullable = false, length = 8)
	private String id;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mxCounterType")
	private Set<MaintenanceCounter> maintenanceCounters = new HashSet<MaintenanceCounter>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mxCounterType")
	private Set<MxCounterInterval> mxCounterIntervals = new HashSet<MxCounterInterval>(0);
}
