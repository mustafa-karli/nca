package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.AuthorityObjectAction;
import com.nauticana.basis.model.AuthorityObjectActionId;

public interface AuthorityObjectActionRepository extends JpaRepository<AuthorityObjectAction, AuthorityObjectActionId>{

}
