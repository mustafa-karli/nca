package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.TableViewScenarioItem;
import com.nauticana.basis.model.TableViewScenarioItemId;

@Controller
@ResponseBody
@RequestMapping("/tableViewScenarioItem")
public class TableViewScenarioItemController extends AbstractController<TableViewScenarioItem, TableViewScenarioItemId> {}
