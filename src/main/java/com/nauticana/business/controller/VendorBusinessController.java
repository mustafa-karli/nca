package com.nauticana.business.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.business.model.VendorBusiness;
import com.nauticana.business.model.VendorBusinessId;

@Controller
@ResponseBody
@RequestMapping("/" + VendorBusiness.ROOTMAPPING)
public class VendorBusinessController extends AbstractController<VendorBusiness, VendorBusinessId> {}

