package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer>{}
