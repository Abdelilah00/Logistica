package com.logistica.services.Commands.Actor;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Actor;
import com.logistica.dtos.Commands.Actor.ActorCreateDto;
import com.logistica.dtos.Commands.Actor.ActorDto;
import com.logistica.dtos.Commands.Actor.ActorUpdateDto;

public interface IActorService extends IBaseCrudService<Actor, ActorDto, ActorCreateDto, ActorUpdateDto> {

}
