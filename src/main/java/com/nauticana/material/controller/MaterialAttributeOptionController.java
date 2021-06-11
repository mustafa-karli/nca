package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialAttributeOption;
import com.nauticana.material.model.MaterialAttributeOptionId;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialAttributeOption.ROOTMAPPING)
public class MaterialAttributeOptionController extends AbstractController<MaterialAttributeOption, MaterialAttributeOptionId> {}

