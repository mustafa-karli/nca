package com.nauticana.shipment.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.shipment.model.Shipment;

@Controller
@ResponseBody
@RequestMapping("/" + Shipment.ROOTMAPPING)
public class ShipmentController extends AbstractController<Shipment, Integer> {}