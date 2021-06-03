package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import com.logistica.domains.Commands.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
public class Stock extends BaseEntity {
    private String name;
    private String adresse;
    private Double area;
    private Boolean def = false;
    //@OneToOne
    //private StockType stockType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Actor responsible = new Actor();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
    private List<StockProduct> stockProducts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
    private List<StockCharacteristic> stockCharacteristics = new ArrayList<>();

    @Formula("(select IFNULL(SUM(sp.qte), 0) from stock s inner join stockproduct sp on s.id = sp.stock_id where s.id = id)")
    private Integer qteOfProducts;
}
