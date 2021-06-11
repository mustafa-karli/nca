package com.nauticana.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.finance.model.AccountBalance;
import com.nauticana.finance.model.AccountBalanceId;

@Controller
@ResponseBody
@RequestMapping("/accountBalance")
public class AccountBalanceController extends AbstractController<AccountBalance, AccountBalanceId> {}