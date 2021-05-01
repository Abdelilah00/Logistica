package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.CommandOut;
import com.logistica.dtos.Commands.CommandOut.CommandOutCreateDto;
import com.logistica.dtos.Commands.CommandOut.CommandOutDto;
import com.logistica.dtos.Commands.CommandOut.CommandOutUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/commandouts")
public class CommandOutController extends BaseCrudController<CommandOut, CommandOutDto, CommandOutCreateDto, CommandOutUpdateDto> {
}
