package com.nauticana.request.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.request.model.MaterialRequestItem;
import com.nauticana.request.model.MaterialRequestItemId;

@Controller
@ResponseBody
@RequestMapping("/materialRequestItemAttr")
public class MaterialRequestItemAttrController extends AbstractController<MaterialRequestItem, MaterialRequestItemId> {}