package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.CreditCard;

@Controller
@ResponseBody
@RequestMapping("/creditCard")
public class CreditCardController extends AbstractController<CreditCard, String> {}