package com.logistica.services;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Test;
import com.logistica.dtos.Test.TestCreateDto;
import com.logistica.dtos.Test.TestDto;
import com.logistica.dtos.Test.TestUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class TestService extends BaseCrudServiceImpl<Test, TestDto, TestCreateDto, TestUpdateDto> implements ITestService {

    public TestService() {
        super(Test.class, TestDto.class, TestCreateDto.class, TestUpdateDto.class);
    }
}
