package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, String>{}
