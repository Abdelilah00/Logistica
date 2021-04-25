package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
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
@Table(name = "supplier")
public class Supplier extends BaseEntity {
    private String name;
    private String adresse;
    private String nRCommerce;

    @OneToOne
    private Contact contact;
    @OneToOne
    private Sector sector;
    @OneToOne
    private Bank bank;
}
