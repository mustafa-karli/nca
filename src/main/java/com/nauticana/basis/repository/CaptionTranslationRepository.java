package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.CaptionTranslation;
import com.nauticana.basis.model.CaptionTranslationId;

public interface CaptionTranslationRepository extends JpaRepository<CaptionTranslation, CaptionTranslationId>{

}
