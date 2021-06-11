package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.ObjectAuthorization;
import com.nauticana.basis.model.ObjectAuthorizationId;

public interface ObjectAuthorizationRepository extends JpaRepository<ObjectAuthorization, ObjectAuthorizationId> {

}
