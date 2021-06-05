package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import com.logistica.domains.Commands.Actor;
import com.logistica.domains.Organ.StructureUnit;
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
    private String ref;
    private String description;
    private Date date;
    private Integer delay;
    private Boolean isStocksTransfer = true;

    @OneToOne
    private StructureUnit fromStructure;
    @OneToOne
    private StructureUnit toStructure;
    @OneToOne
    private Stock fromStock;
    @OneToOne
    private Stock toStock;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transfer")
    private List<TransferDetails> transferDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    private Actor actor;
}
