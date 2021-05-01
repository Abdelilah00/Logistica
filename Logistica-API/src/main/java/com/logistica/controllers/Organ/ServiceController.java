package com.logistica.controllers.Organ;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Organ.Service;
import com.logistica.dtos.Organ.Service.ServiceCreateDto;
import com.logistica.dtos.Organ.Service.ServiceDto;
import com.logistica.dtos.Organ.Service.ServiceUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/services")
public class ServiceController extends BaseCrudController<Service, ServiceDto, ServiceCreateDto, ServiceUpdateDto> {
}
