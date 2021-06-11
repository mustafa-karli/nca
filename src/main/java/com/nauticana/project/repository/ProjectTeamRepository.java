package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.ProjectTeam;
import com.nauticana.project.model.ProjectTeamId;

public interface ProjectTeamRepository extends JpaRepository<ProjectTeam, ProjectTeamId> {

}
