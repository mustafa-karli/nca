package com.nauticana.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.Subcontractor;
import com.nauticana.project.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

	Worker findByPersonId(Integer personId);

	List<Worker> findBySubcontractor(Subcontractor subcontractor);
}
