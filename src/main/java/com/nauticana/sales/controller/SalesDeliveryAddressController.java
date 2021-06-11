package com.nauticana.sales.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.sales.model.SalesDeliveryAddress;

@Controller
@ResponseBody
@RequestMapping("/" + SalesDeliveryAddress.ROOTMAPPING)
public class SalesDeliveryAddressController extends AbstractController<SalesDeliveryAddress, Integer> {}