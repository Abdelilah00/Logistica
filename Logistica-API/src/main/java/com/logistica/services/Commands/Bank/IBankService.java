package com.logistica.services.Commands.Bank;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Bank;
import com.logistica.dtos.Commands.Bank.BankCreateDto;
import com.logistica.dtos.Commands.Bank.BankDto;
import com.logistica.dtos.Commands.Bank.BankUpdateDto;

public interface IBankService extends IBaseCrudService<Bank, BankDto, BankCreateDto, BankUpdateDto> {

}
