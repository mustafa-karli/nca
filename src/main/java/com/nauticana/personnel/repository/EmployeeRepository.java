package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.Employee;
import com.nauticana.personnel.model.EmployeeId;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId>{}
