package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.DomainName;

public interface DomainNameRepository extends JpaRepository<DomainName, String>{

}
