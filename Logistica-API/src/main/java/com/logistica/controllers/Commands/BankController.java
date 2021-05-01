package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.Bank;
import com.logistica.dtos.Commands.Bank.BankCreateDto;
import com.logistica.dtos.Commands.Bank.BankDto;
import com.logistica.dtos.Commands.Bank.BankUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/banks")
public class BankController extends BaseCrudController<Bank, BankDto, BankCreateDto, BankUpdateDto> {
}
