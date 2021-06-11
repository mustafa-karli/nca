package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Integer>{}
