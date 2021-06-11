package com.nauticana.basis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MASTER_DETAIL_RELATION")
public class MasterDetailRelation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONSTRAINT_NAME", unique = true, nullable = false, length = 30)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MASTER_TABLE", nullable = false, insertable = false, updatable = false)
	private TableControllerStatic	masterTableController;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAIL_TABLE", nullable = false)
	private TableControllerStatic	detailTableController;

	@Column(name = "DETAILT_ATTRIBUTE", nullable = false, length = 80)
	private String					detailAttribute;

	@Column(name = "MD_VIEW", nullable = false, length = 1)
	private String					enable;

	@Column(name = "PAGING", length = 30)
	private String					paging;

	@Column(name = "FILTER", length = 200)
	private String					filter;

	@Column(name = "ORDERBY", length = 80)
	private String					orderby;

}
