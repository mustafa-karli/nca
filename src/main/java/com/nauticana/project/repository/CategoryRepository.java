package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
