package com.logistica.dtos.Commands.Supplier;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Commands.Bank.BankDto;
import com.logistica.dtos.Commands.Contact.ContactDto;
import com.logistica.dtos.Commands.Sector.SectorDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto extends BaseDto {
    private String name;
    private String adresse;
    private String nRCommerce;
    private ContactDto contact;
    private SectorDto sector;
    private BankDto bank;
}
