package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.DomainLookup;
import com.nauticana.basis.model.DomainLookupId;

public interface DomainLookupRepository extends JpaRepository<DomainLookup, DomainLookupId> {
}
