package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.DomainValue;
import com.nauticana.basis.model.DomainValueId;

public interface DomainValueRepository extends JpaRepository<DomainValue, DomainValueId>{

}
