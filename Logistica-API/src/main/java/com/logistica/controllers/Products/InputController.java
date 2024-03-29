package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.Input;
import com.logistica.dtos.Products.Input.InputCreateDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Input.InputUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inputs")
public class InputController extends BaseCrudController<Input, InputDto, InputCreateDto, InputUpdateDto> {
}
