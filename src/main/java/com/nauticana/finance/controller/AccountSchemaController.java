package com.nauticana.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.finance.model.AccountSchema;

@Controller
@ResponseBody
@RequestMapping("/accountSchema")
public class AccountSchemaController extends AbstractController<AccountSchema, Integer> {}
