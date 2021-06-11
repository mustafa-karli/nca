package com.nauticana.sales.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.nauticana.material.model.MaterialAttribute;

public class ViewProductsForCart {

	private int materialId;
	private String caption;
	private String description;
	private String imageUrl;
	private BigDecimal price;
	private char favorite;

	private Set<MaterialAttribute> materialAttributes = new HashSet<MaterialAttribute>(0);
	
	public ViewProductsForCart() {}
	
	public ViewProductsForCart(int materialId, String caption, String description, String imageUrl, BigDecimal price, Set<MaterialAttribute> materialAttributes, char favorite) {
		super();
		this.materialId = materialId;
		this.caption = caption;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.materialAttributes = materialAttributes;
		this.favorite = favorite;
	}

	public int getId() {
		return materialId;
	}

	public void setId(int materialId) {
		this.materialId = materialId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Set<MaterialAttribute> getMaterialAttributes() {
		return materialAttributes;
	}

	public void setMaterialAttributes(Set<MaterialAttribute> materialAttributes) {
		this.materialAttributes = materialAttributes;
	}

	public char getFavorite() {
		return favorite;
	}

	public void setFavorite(char favorite) {
		this.favorite = favorite;
	}

}
