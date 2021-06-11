package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.TableFieldFace;
import com.nauticana.basis.model.TableFieldFaceId;

@Controller
@ResponseBody
@RequestMapping("/tableFieldFace")
public class TableFieldFaceController extends AbstractController<TableFieldFace, TableFieldFaceId>{}
