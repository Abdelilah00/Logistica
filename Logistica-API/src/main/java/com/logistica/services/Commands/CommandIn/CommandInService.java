package com.logistica.services.Commands.CommandIn;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.CommandIn;
import com.logistica.dtos.Commands.CommandIn.CommandInCreateDto;
import com.logistica.dtos.Commands.CommandIn.CommandInDto;
import com.logistica.dtos.Commands.CommandIn.CommandInUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class CommandInService extends BaseCrudServiceImpl<CommandIn, CommandInDto, CommandInCreateDto, CommandInUpdateDto> implements ICommandInService {

    public CommandInService() {
        super(CommandIn.class, CommandInDto.class, CommandInCreateDto.class, CommandInUpdateDto.class);
    }
}
