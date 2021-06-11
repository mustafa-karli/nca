package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.UserFavorite;
import com.nauticana.basis.model.UserFavoriteId;

@Controller
@ResponseBody
@RequestMapping("/userFavorite")
public class UserFavoriteController extends AbstractController<UserFavorite, UserFavoriteId>{}
