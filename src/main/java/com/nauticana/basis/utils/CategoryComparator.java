package com.nauticana.basis.utils;

import java.util.Comparator;

import com.nauticana.project.model.Category;

public class CategoryComparator implements Comparator<Category> {

	public int compare(Category c1, Category c2) {
		return c1.getTreeCode().compareTo(c2.getTreeCode());
	}
}
