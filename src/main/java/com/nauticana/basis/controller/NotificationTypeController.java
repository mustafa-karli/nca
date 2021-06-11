package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.NotificationType;

@Controller
@ResponseBody
@RequestMapping("/notificationType")
public class NotificationTypeController extends AbstractController<NotificationType,Integer>{}