package com.nauticana.sales.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.sales.model.SalesAccountBalance;

@Controller
@RequestMapping("/salesAccountBalance")
public class SalesAccountBalanceController extends AbstractController<SalesAccountBalance, Integer> {}