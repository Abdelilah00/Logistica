package com.logistica.services.Organ.Service;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Organ.Service;
import com.logistica.dtos.Organ.Service.ServiceCreateDto;
import com.logistica.dtos.Organ.Service.ServiceDto;
import com.logistica.dtos.Organ.Service.ServiceUpdateDto;

public interface IServiceService extends IBaseCrudService<Service, ServiceDto, ServiceCreateDto, ServiceUpdateDto> {

}
