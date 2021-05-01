package com.logistica.services.Organ.Service;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Organ.Service;
import com.logistica.dtos.Organ.Service.ServiceCreateDto;
import com.logistica.dtos.Organ.Service.ServiceDto;
import com.logistica.dtos.Organ.Service.ServiceUpdateDto;

@org.springframework.stereotype.Service
public class ServiceService extends BaseCrudServiceImpl<Service, ServiceDto, ServiceCreateDto, ServiceUpdateDto> implements IServiceService {

    public ServiceService() {
        super(Service.class, ServiceDto.class, ServiceCreateDto.class, ServiceUpdateDto.class);
    }
}
