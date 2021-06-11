package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.ScreenPage;

@Controller
@ResponseBody
@RequestMapping("/" + ScreenPage.ROOTMAPPING)
public class ScreenPageController extends AbstractController<ScreenPage, String> {}
