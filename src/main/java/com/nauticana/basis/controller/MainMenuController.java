package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.MainMenu;

@Controller
@ResponseBody
@RequestMapping("/" + MainMenu.ROOTMAPPING)
public class MainMenuController extends AbstractController<MainMenu, String> {}
