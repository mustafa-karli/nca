package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.ProjectTeamTemplate;
import com.nauticana.project.model.ProjectTeamTemplateId;

public interface ProjectTeamTemplateRepository extends JpaRepository<ProjectTeamTemplate, ProjectTeamTemplateId> {

}
