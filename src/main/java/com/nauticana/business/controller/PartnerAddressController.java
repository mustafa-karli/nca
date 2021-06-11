package com.nauticana.business.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.business.model.PartnerAddress;
import com.nauticana.business.model.PartnerAddressId;

@Controller
@ResponseBody
@RequestMapping("/" + PartnerAddress.ROOTMAPPING)
public class PartnerAddressController extends AbstractController<PartnerAddress, PartnerAddressId> {}

