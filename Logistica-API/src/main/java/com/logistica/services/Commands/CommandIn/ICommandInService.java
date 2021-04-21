package com.logistica.services.Commands.CommandIn;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.CommandIn;
import com.logistica.dtos.Commands.CommandIn.CommandInCreateDto;
import com.logistica.dtos.Commands.CommandIn.CommandInDto;
import com.logistica.dtos.Commands.CommandIn.CommandInUpdateDto;
import org.springframework.stereotype.Service;

public interface ICommandInService extends IBaseCrudService<CommandIn, CommandInDto, CommandInCreateDto, CommandInUpdateDto> {

}
