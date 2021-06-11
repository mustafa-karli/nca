package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialReservation;

public interface MaterialReservationRepository extends JpaRepository<MaterialReservation, Integer>{}
