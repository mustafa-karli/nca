package com.nauticana.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.finance.model.AccountTxTemplateItem;
import com.nauticana.finance.model.AccountTxTemplateItemId;

@Controller
@ResponseBody
@RequestMapping("/accountTxTemplateItem")
public class AccountTxTemplateItemController extends AbstractController<AccountTxTemplateItem, AccountTxTemplateItemId> {}