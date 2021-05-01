package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    //@OneToOne
    //private StockType stockType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StockRespo stockRespo = new StockRespo();

    @OneToMany(mappedBy = "stock")
    private List<StockProduct> stockProducts = new ArrayList<>();
    @OneToMany(mappedBy = "stock")
    private List<StockCharacteristic> stockCharacteristics = new ArrayList<>();

}
