package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{}
