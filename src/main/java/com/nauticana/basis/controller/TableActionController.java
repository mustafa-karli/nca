package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.TableAction;
import com.nauticana.basis.model.TableActionId;

@Controller
@ResponseBody
@RequestMapping("/tableAction")
public class TableActionController extends AbstractController<TableAction, TableActionId>{}
