package com.logistica.controllers;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Test;
import com.logistica.dtos.Test.TestCreateDto;
import com.logistica.dtos.Test.TestDto;
import com.logistica.dtos.Test.TestUpdateDto;

@RestController
@RequestMapping("api/tests")
public class TestController extends BaseCrudController<Test, TestDto, TestCreateDto, TestUpdateDto> {
}
