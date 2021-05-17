package com.logistica.services.Commands.Actor;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Actor;
import com.logistica.dtos.Commands.Actor.ActorCreateDto;
import com.logistica.dtos.Commands.Actor.ActorDto;
import com.logistica.dtos.Commands.Actor.ActorUpdateDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IActorService extends IBaseCrudService<Actor, ActorDto, ActorCreateDto, ActorUpdateDto> {
    CompletableFuture<List<ActorDto>> getSuppliers();

    CompletableFuture<List<ActorDto>> getClients();

    CompletableFuture<List<ActorDto>> getResponsible();
}
