package com.nauticana.basis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = MainMenu.TABLE_NAME)
public class MainMenu implements java.io.Serializable {

	public static final String   ROOTMAPPING = "mainMenu";
	public static final String   TABLE_NAME  = "MAIN_MENU";

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MENU", unique = true, nullable = false, length = 30)
	private String id;
	
	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;
	
	@Column(name = "ICON", nullable = false, length = 100)
	private String icon;
	
	@Column(name = "DISPLAY_ORDER")
	@OrderBy
	private short  displayOrder;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	private Set<ScreenPage> screenPages = new HashSet<ScreenPage>(0);

}
