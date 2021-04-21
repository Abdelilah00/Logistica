package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Commands.CommandIn;
import com.logistica.dtos.Commands.CommandIn.CommandInCreateDto;
import com.logistica.dtos.Commands.CommandIn.CommandInDto;
import com.logistica.dtos.Commands.CommandIn.CommandInUpdateDto;

@RestController
@RequestMapping("api/commandins")
public class CommandInController extends BaseCrudController<CommandIn, CommandInDto, CommandInCreateDto, CommandInUpdateDto> {
}
