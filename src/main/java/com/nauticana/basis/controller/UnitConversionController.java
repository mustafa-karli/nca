package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.UnitConversion;
import com.nauticana.basis.model.UnitConversionId;

@Controller
@ResponseBody
@RequestMapping("/" + UnitConversion.ROOTMAPPING)
public class UnitConversionController extends AbstractController<UnitConversion, UnitConversionId> {}
