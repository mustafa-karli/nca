package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, String>{}
