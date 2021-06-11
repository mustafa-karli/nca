package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.Language;

public interface LanguageRepository extends JpaRepository<Language, String> {

}
