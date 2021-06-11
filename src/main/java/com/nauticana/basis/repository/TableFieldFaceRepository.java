package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.TableFieldFace;
import com.nauticana.basis.model.TableFieldFaceId;

public interface TableFieldFaceRepository extends JpaRepository<TableFieldFace, TableFieldFaceId>{

}
