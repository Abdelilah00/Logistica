package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import com.logistica.domains.Commands.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfer")
public class Transfer extends BaseEntity {
    private Date date;
    private Integer delay;

    @OneToOne
    private Stock fromStock;
    @OneToOne
    private Stock toStock;

    @OneToMany
    private List<TransferDetails> transferDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    private Actor actor;
}
