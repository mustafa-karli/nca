package com.nauticana.request.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.request.model.ProductTypeByDefine;
import com.nauticana.request.model.ProductTypeByDefineId;

public interface ProductTypeByDefineRepository extends JpaRepository<ProductTypeByDefine, ProductTypeByDefineId>{}
