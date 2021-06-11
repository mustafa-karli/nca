package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialTypeAttribute;
import com.nauticana.material.model.MaterialTypeAttributeId;

@Controller
@ResponseBody
@RequestMapping("/materialTypeAttribute")
public class MaterialTypeAttributeController extends AbstractController<MaterialTypeAttribute, MaterialTypeAttributeId> {}