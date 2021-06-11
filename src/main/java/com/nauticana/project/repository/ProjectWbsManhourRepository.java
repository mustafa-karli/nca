package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.ProjectWbsManhour;
import com.nauticana.project.model.ProjectWbsManhourId;

public interface ProjectWbsManhourRepository extends JpaRepository<ProjectWbsManhour, ProjectWbsManhourId> {

}
