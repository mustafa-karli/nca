package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.CategoryText;
import com.nauticana.project.model.CategoryTextId;

public interface CategoryTextRepository extends JpaRepository<CategoryText, CategoryTextId> {

}
