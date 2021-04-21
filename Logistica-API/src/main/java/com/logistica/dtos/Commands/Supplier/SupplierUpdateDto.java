package com.logistica.dtos.Commands.Supplier;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierUpdateDto extends BaseDto {
private String name;
private String adresse;
private String nRCommerce;
private String Contact;
private String Sector;
private String Bank;
}
