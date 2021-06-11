package com.nauticana.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Category.TABLE_NAME)
public class Category implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "category";
	public  static final String   TABLE_NAME  = "CATEGORY";
	public  static final String   SEQUENCE_NAME = "CATEGORY_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "PARENT_ID")
	private Integer parentId;

	@Column(name="CAPTION", length=250, nullable = false)
	private String caption;

	@Column(name = "CAT_INDEX", length = 20, nullable = false)
	private String catIndex;

	@Column(name="DETAILS", length=100)
	private String details;

	@Column(name="UNIT", length=3)
	private String unit;

	@Column(name="UNIT2", length=3)
	private String unit2;

	@Column(name = "CAT_LEVEL", nullable = false)
	private byte catLevel;

	@OrderBy("TREE_CODE")
	@Column(name = "TREE_CODE", length = 20, nullable = false)
	private String treeCode;

	@Column(name = "ACCOUNT_SCHEMA_ID", length = 20)
	private Integer accountSchemaId;

	@Column(name="MAIN_FLAG", length=1)
	private String mainFlag;

	@Column(name="PROJECT_ID")
	private Integer projectId;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<ProjectWbs> projectWbses = new HashSet<ProjectWbs>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	private Set<CategoryText> categoryTexts = new HashSet<CategoryText>(0);
}
