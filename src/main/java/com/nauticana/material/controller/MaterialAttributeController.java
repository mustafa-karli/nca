package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialAttribute;
import com.nauticana.material.model.MaterialAttributeId;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialAttribute.ROOTMAPPING)
public class MaterialAttributeController extends AbstractController<MaterialAttribute, MaterialAttributeId> {}

