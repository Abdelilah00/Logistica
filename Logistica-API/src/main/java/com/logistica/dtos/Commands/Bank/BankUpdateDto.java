package com.logistica.dtos.Commands.Bank;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankUpdateDto extends BaseDto {
private String name;
private String code;
private String accountNumber;
private String rIB;
}
