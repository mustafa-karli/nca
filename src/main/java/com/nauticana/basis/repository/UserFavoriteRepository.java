package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.UserFavorite;
import com.nauticana.basis.model.UserFavoriteId;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, UserFavoriteId> {}