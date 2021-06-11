package com.nauticana.basis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = ScreenPage.TABLE_NAME)
public class ScreenPage implements java.io.Serializable {

	public static final String   ROOTMAPPING = "screenPage";
	public static final String   TABLE_NAME  = "SCREEN_PAGE";
	public static final String[] ACTIONS     = new String[] { "ACCESS" };

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PAGENAME", unique = true, nullable = false, length = 30)
	private String id;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;

	@Column(name = "ICON", nullable = false, length = 100)
	private String icon;

	@Column(name = "URL", nullable = false, length = 100)
	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU", nullable = false)
	private MainMenu menu;

	@Column(name = "DISPLAY_ORDER")
	@OrderBy
	private short displayOrder;
}
