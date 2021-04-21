package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.Cosmetic;
import com.logistica.dtos.Products.Cosmetic.CosmeticCreateDto;
import com.logistica.dtos.Products.Cosmetic.CosmeticDto;
import com.logistica.dtos.Products.Cosmetic.CosmeticUpdateDto;

@RestController
@RequestMapping("api/cosmetics")
public class CosmeticController extends BaseCrudController<Cosmetic, CosmeticDto, CosmeticCreateDto, CosmeticUpdateDto> {
}
