package com.nauticana.basis.model;
// Generated Mar 19, 2018 1:39:18 PM by Hibernate Tools 5.2.8.Final

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
@Table(name = "TABLE_CONTROLLER_STATIC")
public class TableControllerStatic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "TABLENAME", unique = true, nullable = false, length = 30)
	private String						id;
	@Column(name = "ROOTMAPPING", nullable = false, length = 80)
	private String						rootmapping;
	@Column(name = "MODULE", nullable = false, length = 80)
	private String						module;
	@Column(name = "CUSTOMER_SPECIFIC", length = 1)
	private String						customerSpecific;
	@Column(name = "ORGANIZATION_CHECK", length = 1)
	private String						organizationCheck;
	@Column(name = "CACHE_IN_HASH", length = 1)
	private String						cacheInHash;
	@Column(name = "SEARCH_VIEW", length = 80)
	private String						searchView;
	@Column(name = "LIST_VIEW", length = 80)
	private String						listView;
	@Column(name = "EDIT_VIEW", length = 80)
	private String						editView;
	@Column(name = "SHOW_VIEW", length = 80)
	private String						showView;
	@Column(name = "SELECT_VIEW", length = 80)
	private String						selectView;
	@Column(name = "ORDERBY", length = 80)
	private String						orderby;
	@Column(name = "SEQUENCE_NAME", length = 1)
	private String						sequenceName;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tableControllerStatic")
	private Set<TableContentType>		tableContentTypes					= new HashSet<TableContentType>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "masterTableController")
	private Set<MasterDetailRelation>	masterDetailRelationsForMasterTable	= new HashSet<MasterDetailRelation>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "detailTableController")
	private Set<MasterDetailRelation>	masterDetailRelationsForDetailTable	= new HashSet<MasterDetailRelation>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tableControllerStatic")
	private Set<TableAction>			tableActions						= new HashSet<TableAction>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tableControllerStatic")
	private Set<TableFieldFace>			tableFieldFaces						= new HashSet<TableFieldFace>(0);
}
