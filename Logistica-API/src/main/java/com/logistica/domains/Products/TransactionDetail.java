package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactiondetail")
public class TransactionDetail extends BaseEntity {
    private Integer lot;
    private Integer article;
    private Integer qte;
    private Float priceHT;
    private Float tVA;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Product product = new Product();
    @ManyToOne
    private Input input;
    @ManyToOne
    private Output output;
}
