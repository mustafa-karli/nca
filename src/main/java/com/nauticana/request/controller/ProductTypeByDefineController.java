package com.nauticana.request.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.request.model.ProductTypeByDefine;
import com.nauticana.request.model.ProductTypeByDefineId;

@Controller
@ResponseBody
@RequestMapping("/productTypeByDefine")
public class ProductTypeByDefineController extends AbstractController<ProductTypeByDefine, ProductTypeByDefineId> {}