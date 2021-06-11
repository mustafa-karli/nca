package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.TableAction;
import com.nauticana.basis.model.TableActionId;

public interface TableActionRepository extends JpaRepository<TableAction, TableActionId> {}