package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Commands.Supplier;
import com.logistica.dtos.Commands.Supplier.SupplierCreateDto;
import com.logistica.dtos.Commands.Supplier.SupplierDto;
import com.logistica.dtos.Commands.Supplier.SupplierUpdateDto;

@RestController
@RequestMapping("api/suppliers")
public class SupplierController extends BaseCrudController<Supplier, SupplierDto, SupplierCreateDto, SupplierUpdateDto> {
}
