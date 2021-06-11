package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {}
