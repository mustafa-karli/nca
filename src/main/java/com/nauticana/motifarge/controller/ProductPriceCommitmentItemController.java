package com.nauticana.motifarge.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.motifarge.model.ProductPriceCommitmentItem;
import com.nauticana.motifarge.model.ProductPriceCommitmentItemId;

@Controller
@ResponseBody
@RequestMapping("/productPriceCommitmentItem")
public class ProductPriceCommitmentItemController extends AbstractController<ProductPriceCommitmentItem, ProductPriceCommitmentItemId> {}