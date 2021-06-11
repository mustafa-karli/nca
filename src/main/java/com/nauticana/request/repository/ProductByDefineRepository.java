package com.nauticana.request.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.request.model.ProductByDefine;
import com.nauticana.request.model.ProductByDefineId;

public interface ProductByDefineRepository extends JpaRepository<ProductByDefine, ProductByDefineId>{}
