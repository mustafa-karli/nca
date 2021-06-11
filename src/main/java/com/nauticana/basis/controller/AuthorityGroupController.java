package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.AuthorityGroup;

@Controller
@ResponseBody
@RequestMapping("/" + AuthorityGroup.ROOTMAPPING)
public class AuthorityGroupController extends AbstractController<AuthorityGroup, String> {}
