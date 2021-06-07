package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
import com.logistica.domains.Organization.StructureUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commandin")
public class CommandIn extends BaseEntity {
    private String ref;
    private String description;
    @OneToOne
    private Actor actor;
    @OneToOne
    private StructureUnit service;

    private PaymentMethod payementMethod;
    //private CommandDetail commandDetail;
}
