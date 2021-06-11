package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.Country;

public interface CountryRepository extends JpaRepository<Country, String>{

}
