package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.Actor;
import com.logistica.dtos.Commands.Actor.ActorCreateDto;
import com.logistica.dtos.Commands.Actor.ActorDto;
import com.logistica.dtos.Commands.Actor.ActorUpdateDto;
import com.logistica.services.Commands.Actor.IActorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/actors")
public class ActorController extends BaseCrudController<Actor, ActorDto, ActorCreateDto, ActorUpdateDto> {

    @GetMapping(path = "/getSuppliers")
    protected List<ActorDto> getSuppliers() throws ExecutionException, InterruptedException {
        return ((IActorService) service).getSuppliers().get();
    }

    @GetMapping(path = "/getClients")
    protected List<ActorDto> getClients() throws ExecutionException, InterruptedException {
        return ((IActorService) service).getClients().get();
    }

    @GetMapping(path = "/getResponsible")
    protected List<ActorDto> getResponsible() throws ExecutionException, InterruptedException {
        return ((IActorService) service).getResponsible().get();
    }
}
