package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.TableContentType;
import com.nauticana.basis.model.TableContentTypeId;

public interface TableContentTypeRepository extends JpaRepository<TableContentType, TableContentTypeId> {}