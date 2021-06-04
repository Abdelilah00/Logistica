package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactiondetail")
public class TransactionDetail extends BaseEntity {
    private Integer article;
    private Integer lot;
    private Integer qte;
    private Float priceHT;
    private Float tVA;
    private Date expDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product = new Product();

    @ManyToOne(fetch = FetchType.LAZY)
    private Input input;
    @ManyToOne(fetch = FetchType.LAZY)
    private Output output;
}
