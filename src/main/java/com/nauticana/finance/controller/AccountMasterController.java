package com.nauticana.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.finance.model.AccountMaster;

@Controller
@ResponseBody
@RequestMapping("/accountMaster")
public class AccountMasterController extends AbstractController<AccountMaster, String> {}