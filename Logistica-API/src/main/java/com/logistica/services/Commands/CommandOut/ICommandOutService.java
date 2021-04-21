package com.logistica.services.Commands.CommandOut;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.CommandOut;
import com.logistica.dtos.Commands.CommandOut.CommandOutCreateDto;
import com.logistica.dtos.Commands.CommandOut.CommandOutDto;
import com.logistica.dtos.Commands.CommandOut.CommandOutUpdateDto;
import org.springframework.stereotype.Service;

public interface ICommandOutService extends IBaseCrudService<CommandOut, CommandOutDto, CommandOutCreateDto, CommandOutUpdateDto> {

}
