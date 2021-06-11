package com.nauticana.request.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.request.model.ProductByDefine;
import com.nauticana.request.model.ProductByDefineId;

@Controller
@ResponseBody
@RequestMapping("/productByDefine")
public class ProductByDefineController extends AbstractController<ProductByDefine, ProductByDefineId> {}