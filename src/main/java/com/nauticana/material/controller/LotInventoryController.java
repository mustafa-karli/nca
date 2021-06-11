package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.LotInventory;
import com.nauticana.material.model.LotInventoryId;

@Controller
@ResponseBody
@RequestMapping("/" + LotInventory.ROOTMAPPING)
public class LotInventoryController extends AbstractController<LotInventory, LotInventoryId> {}

