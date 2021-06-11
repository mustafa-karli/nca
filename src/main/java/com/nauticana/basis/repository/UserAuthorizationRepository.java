package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.UserAuthorization;
import com.nauticana.basis.model.UserAuthorizationId;

public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, UserAuthorizationId> {

}
