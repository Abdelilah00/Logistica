package com.logistica.dtos.Commands.Supplier;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Commands.Bank.BankCreateDto;
import com.logistica.dtos.Commands.Contact.ContactCreateDto;
import com.logistica.dtos.Commands.Sector.SectorCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierCreateDto extends BaseDto {
    private String name;
    private String adresse;
    private String nRCommerce;
    private ContactCreateDto Contact;
    private SectorCreateDto Sector;
    private BankCreateDto Bank;
}
