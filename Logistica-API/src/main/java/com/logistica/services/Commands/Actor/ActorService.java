package com.logistica.services.Commands.Actor;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.Actor;
import com.logistica.dtos.Commands.Actor.ActorCreateDto;
import com.logistica.dtos.Commands.Actor.ActorDto;
import com.logistica.dtos.Commands.Actor.ActorUpdateDto;
import com.logistica.repositories.Commands.IActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ActorService extends BaseCrudServiceImpl<Actor, ActorDto, ActorCreateDto, ActorUpdateDto> implements IActorService {

    public ActorService() {
        super(Actor.class, ActorDto.class, ActorCreateDto.class, ActorUpdateDto.class);
    }

    public CompletableFuture<List<ActorDto>> getSuppliers() {
        List<Actor> actors = ((IActorRepository) repository).getSuppliers();
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(actors, ActorDto.class));
    }

    public CompletableFuture<List<ActorDto>> getClients() {
        List<Actor> actors = ((IActorRepository) repository).getClients();
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(actors, ActorDto.class));
    }

    public CompletableFuture<List<ActorDto>> getResponsible() {
        List<Actor> actors = ((IActorRepository) repository).getResponsible();
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(actors, ActorDto.class));
    }

}
