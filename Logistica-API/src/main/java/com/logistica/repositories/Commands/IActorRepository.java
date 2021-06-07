package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Actor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IActorRepository extends IBaseJpaRepository<Actor> {
    @Query("select a from Actor a " +
            "inner join ActorHasRole ait on a.actorHasRole.id = ait.id " +
            "inner join ActorRole at on ait.actorType.id=at.id " +
            "where at.name='Supplier'")
    List<Actor> getSuppliers();

    @Query("select a from Actor a " +
            "inner join ActorHasRole ait on a.actorHasRole.id = ait.id " +
            "inner join ActorRole at on ait.actorType.id=at.id " +
            "where at.name='Client'")
    List<Actor> getClients();

    @Query("select a from Actor a " +
            "inner join ActorHasRole ait on a.actorHasRole.id = ait.id " +
            "inner join ActorRole at on ait.actorType.id=at.id " +
            "where at.name='Responsible'")
    List<Actor> getResponsible();
}

