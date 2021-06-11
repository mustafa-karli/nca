package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.UserAccountOwner;
import com.nauticana.personnel.model.UserAccountOwnerId;

@Controller
@ResponseBody
@RequestMapping("/" + UserAccountOwner.ROOTMAPPING)
public class UserAccountOwnerController extends AbstractController<UserAccountOwner, UserAccountOwnerId> {}

