package com.nauticana.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.project.model.ProjectWbsQuantity;
import com.nauticana.project.model.ProjectWbsQuantityId;

public interface ProjectWbsQuantityRepository extends JpaRepository<ProjectWbsQuantity, ProjectWbsQuantityId> {

}
