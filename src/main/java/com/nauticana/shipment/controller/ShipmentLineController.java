package com.nauticana.shipment.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.shipment.model.ShipmentLine;
import com.nauticana.shipment.model.ShipmentLineId;

@Controller
@ResponseBody
@RequestMapping("/" + ShipmentLine.ROOTMAPPING)
public class ShipmentLineController extends AbstractController<ShipmentLine, ShipmentLineId> {}