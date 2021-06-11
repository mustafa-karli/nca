package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialInventory;
import com.nauticana.material.model.MaterialInventoryId;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialInventory.ROOTMAPPING)
public class MaterialInventoryController extends AbstractController<MaterialInventory, MaterialInventoryId> {}

