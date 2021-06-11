package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.MasterDetailRelation;

public interface MasterDetailRelationRepository extends JpaRepository<MasterDetailRelation, String> {}