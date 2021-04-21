package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.Characteristic;
import com.logistica.dtos.Products.Characteristic.CharacteristicCreateDto;
import com.logistica.dtos.Products.Characteristic.CharacteristicDto;
import com.logistica.dtos.Products.Characteristic.CharacteristicUpdateDto;

@RestController
@RequestMapping("api/characteristics")
public class CharacteristicController extends BaseCrudController<Characteristic, CharacteristicDto, CharacteristicCreateDto, CharacteristicUpdateDto> {
}
