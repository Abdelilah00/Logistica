package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import com.logistica.domains.Commands.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "output")
public class Output extends BaseEntity {
    private String ref;
    private Date date;
    private String description;

    private String askBy;
    private String DistinationType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Actor actor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "output")
    //todo: get a list of transaction details without need to relational table
    private List<TransactionDetail> transactionDetails = new ArrayList<>();
}
