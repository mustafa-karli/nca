package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{}
