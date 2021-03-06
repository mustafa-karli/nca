package com.nauticana.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.finance.model.AccountTransactionItem;
import com.nauticana.finance.model.AccountTransactionItemId;

@Controller
@ResponseBody
@RequestMapping("/accountTransactionItem")
public class AccountTransactionItemController extends AbstractController<AccountTransactionItem, AccountTransactionItemId> {}