package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialSaleTax;
import com.nauticana.material.model.MaterialSaleTaxId;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialSaleTax.ROOTMAPPING)
public class MaterialSaleTaxController extends AbstractController<MaterialSaleTax, MaterialSaleTaxId> {}

