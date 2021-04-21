package com.logistica.services.Commands.Supplier;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Supplier;
import com.logistica.dtos.Commands.Supplier.SupplierCreateDto;
import com.logistica.dtos.Commands.Supplier.SupplierDto;
import com.logistica.dtos.Commands.Supplier.SupplierUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class SupplierService extends BaseCrudServiceImpl<Supplier, SupplierDto, SupplierCreateDto, SupplierUpdateDto> implements ISupplierService {

    public SupplierService() {
        super(Supplier.class, SupplierDto.class, SupplierCreateDto.class, SupplierUpdateDto.class);
    }
}
