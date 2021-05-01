package com.logistica.services.Commands.Bank;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.Bank;
import com.logistica.dtos.Commands.Bank.BankCreateDto;
import com.logistica.dtos.Commands.Bank.BankDto;
import com.logistica.dtos.Commands.Bank.BankUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class BankService extends BaseCrudServiceImpl<Bank, BankDto, BankCreateDto, BankUpdateDto> implements IBankService {

    public BankService() {
        super(Bank.class, BankDto.class, BankCreateDto.class, BankUpdateDto.class);
    }
}
