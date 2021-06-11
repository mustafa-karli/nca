package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.NotificationRecipient;
import com.nauticana.basis.model.NotificationRecipientId;

@Controller
@ResponseBody
@RequestMapping("/notificationRecipient")
public class NotificationRecipientController extends AbstractController<NotificationRecipient,NotificationRecipientId>{}