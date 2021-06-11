package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
