package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.PersonQualification;
import com.nauticana.personnel.model.PersonQualificationId;

public interface PersonQualificationRepository extends JpaRepository<PersonQualification, PersonQualificationId>{}
