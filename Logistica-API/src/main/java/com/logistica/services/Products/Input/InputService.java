package com.logistica.services.Products.Input;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Input;
import com.logistica.dtos.Products.Input.InputCreateDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Input.InputUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class InputService extends BaseCrudServiceImpl<Input, InputDto, InputCreateDto, InputUpdateDto> implements IInputService {

    public InputService() {
        super(Input.class, InputDto.class, InputCreateDto.class, InputUpdateDto.class);
    }
}
