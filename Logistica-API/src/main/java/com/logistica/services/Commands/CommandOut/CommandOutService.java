package com.logistica.services.Commands.CommandOut;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.CommandOut;
import com.logistica.dtos.Commands.CommandOut.CommandOutCreateDto;
import com.logistica.dtos.Commands.CommandOut.CommandOutDto;
import com.logistica.dtos.Commands.CommandOut.CommandOutUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class CommandOutService extends BaseCrudServiceImpl<CommandOut, CommandOutDto, CommandOutCreateDto, CommandOutUpdateDto> implements ICommandOutService {

    public CommandOutService() {
        super(CommandOut.class, CommandOutDto.class, CommandOutCreateDto.class, CommandOutUpdateDto.class);
    }
}
