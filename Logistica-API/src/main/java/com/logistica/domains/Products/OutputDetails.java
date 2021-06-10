package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "outputdetails")
public class OutputDetails extends BaseEntity {
    private Integer article;
    private Integer lot;
    private Integer qte;
    private Date expDate;
    private Float priceHT;
    private Float tVA;
/*
    @OneToOne
    private Stock stock = new Stock();*/

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product = new Product();

    @ManyToOne(fetch = FetchType.LAZY)
    private Output output;
}
