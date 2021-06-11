package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.ProjectWbs;
import com.nauticana.project.model.ProjectWbsId;

public interface ProjectWbsRepository extends JpaRepository<ProjectWbs, ProjectWbsId> {

}
