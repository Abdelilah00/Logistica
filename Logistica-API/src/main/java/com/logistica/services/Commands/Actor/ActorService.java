package com.logistica.services.Commands.Actor;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.Actor;
import com.logistica.dtos.Commands.Actor.ActorCreateDto;
import com.logistica.dtos.Commands.Actor.ActorDto;
import com.logistica.dtos.Commands.Actor.ActorUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class ActorService extends BaseCrudServiceImpl<Actor, ActorDto, ActorCreateDto, ActorUpdateDto> implements IActorService {

    public ActorService() {
        super(Actor.class, ActorDto.class, ActorCreateDto.class, ActorUpdateDto.class);
    }
}
