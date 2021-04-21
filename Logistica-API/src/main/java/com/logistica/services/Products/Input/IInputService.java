package com.logistica.services.Products.Input;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Input;
import com.logistica.dtos.Products.Input.InputCreateDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Input.InputUpdateDto;
import org.springframework.stereotype.Service;

public interface IInputService extends IBaseCrudService<Input, InputDto, InputCreateDto, InputUpdateDto> {

}
