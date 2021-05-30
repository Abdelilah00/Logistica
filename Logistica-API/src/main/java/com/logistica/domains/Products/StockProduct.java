package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stockproduct")
public class StockProduct extends BaseEntity {
    private Integer qte;

    @ManyToOne(cascade = CascadeType.ALL)
    private Stock stock;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
}
