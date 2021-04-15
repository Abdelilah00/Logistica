package com.logistica.services;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Test;
import com.logistica.dtos.Test.TestCreateDto;
import com.logistica.dtos.Test.TestDto;
import com.logistica.dtos.Test.TestUpdateDto;

public interface ITestService extends IBaseCrudService<Test, TestDto, TestCreateDto, TestUpdateDto> {
}
