package com.nauticana.business.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.business.model.BusinessContract;
import com.nauticana.business.model.BusinessContractId;

@Controller
@ResponseBody
@RequestMapping("/" + BusinessContract.ROOTMAPPING)
public class BusinessContractController extends AbstractController<BusinessContract, BusinessContractId> {}

