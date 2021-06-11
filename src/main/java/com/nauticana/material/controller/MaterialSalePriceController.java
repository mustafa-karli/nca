package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialSalePrice;
import com.nauticana.material.model.MaterialSalePriceId;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialSalePrice.ROOTMAPPING)
public class MaterialSalePriceController extends AbstractController<MaterialSalePrice, MaterialSalePriceId> {}

