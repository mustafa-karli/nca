package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialReservation;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialReservation.ROOTMAPPING)
public class MaterialReservationController extends AbstractController<MaterialReservation, Integer> {}

