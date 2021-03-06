package com.nauticana.business.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.business.model.ApplicationConfig;

@Controller
@ResponseBody
@RequestMapping("/applicationConfig")
public class ApplicationConfigController extends AbstractController<ApplicationConfig, Integer> {}