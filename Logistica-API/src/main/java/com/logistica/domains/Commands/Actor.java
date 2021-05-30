package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
import com.logistica.domains.Products.Input;
import com.logistica.domains.Products.Output;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actor")
public class Actor extends BaseEntity {
    private String name;
    private String adresse;
    private String nRCommerce;

    @OneToOne(cascade = CascadeType.ALL)
    private ActorHasRole actorHasRole = new ActorHasRole();

    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
    @OneToOne(cascade = CascadeType.ALL)
    private Sector sector;
    @OneToOne(cascade = CascadeType.ALL)
    private Bank bank;

    @OneToMany(mappedBy = "actor")
    private List<Input> inputs;
    @OneToMany(mappedBy = "actor")
    private List<Output> outputs;

}
