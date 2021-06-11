package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialPackUnit;
import com.nauticana.material.model.MaterialPackUnitId;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialPackUnit.ROOTMAPPING)
public class MaterialPackUnitController extends AbstractController<MaterialPackUnit, MaterialPackUnitId> {}

