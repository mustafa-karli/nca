package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialInventoryHistory;
import com.nauticana.material.model.MaterialInventoryHistoryId;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialInventoryHistory.ROOTMAPPING)
public class MaterialInventoryHistoryController extends AbstractController<MaterialInventoryHistory, MaterialInventoryHistoryId> {}

