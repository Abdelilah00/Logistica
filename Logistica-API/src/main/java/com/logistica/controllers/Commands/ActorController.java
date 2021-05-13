package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.Actor;
import com.logistica.dtos.Commands.Actor.ActorCreateDto;
import com.logistica.dtos.Commands.Actor.ActorDto;
import com.logistica.dtos.Commands.Actor.ActorUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/actors")
public class ActorController extends BaseCrudController<Actor, ActorDto, ActorCreateDto, ActorUpdateDto> {
}
