package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
    @OneToOne(cascade = CascadeType.ALL)
    private Sector sector;
    @OneToOne(cascade = CascadeType.ALL)
    private Bank bank;
}
