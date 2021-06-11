package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.ProjectTeamPerson;
import com.nauticana.project.model.ProjectTeamPersonId;

public interface ProjectTeamPersonRepository extends JpaRepository<ProjectTeamPerson, ProjectTeamPersonId> {

}
