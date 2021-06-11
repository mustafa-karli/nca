package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.ContentRelation;
import com.nauticana.basis.model.ContentRelationId;

public interface ContentRelationRepository extends JpaRepository<ContentRelation, ContentRelationId>{
}
