package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.CaptionTranslation;
import com.nauticana.basis.model.CaptionTranslationId;

@Controller
@ResponseBody
@RequestMapping("/" + CaptionTranslation.ROOTMAPPING)
public class CaptionTranslationController extends AbstractController<CaptionTranslation, CaptionTranslationId> {}
