package com.logistica.services.Commands.Supplier;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Supplier;
import com.logistica.dtos.Commands.Supplier.SupplierCreateDto;
import com.logistica.dtos.Commands.Supplier.SupplierDto;
import com.logistica.dtos.Commands.Supplier.SupplierUpdateDto;
import org.springframework.stereotype.Service;

public interface ISupplierService extends IBaseCrudService<Supplier, SupplierDto, SupplierCreateDto, SupplierUpdateDto> {

}
