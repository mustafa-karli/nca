package com.nauticana.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.AccountSchema;

public interface AccountSchemaRepository extends JpaRepository<AccountSchema, Integer> {}