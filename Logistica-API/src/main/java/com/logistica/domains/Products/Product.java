package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
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
@Table(name = "product")
public class Product extends BaseEntity {
    private String name;

    private Integer stockMin;
    private Integer stockMax;
    private Integer stockSecurity;


    //Many to One
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category = new Category();

    //One to one
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Defective> defectives = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Reviews> reviews = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductUnits> productUnits = new ArrayList<>();

    //One To Many
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<TransactionDetail> transactionDetails = new ArrayList<>();

    //Many to many
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<StockProduct> stockProducts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductCharacteritic> productCharacteritics = new ArrayList<>();

    //private Category parent = new Category();
    @Formula("(select IFNULL(SUM(sp.qte), 0) from product p inner join stockproduct sp on p.id = sp.product_id where p.id = id)")
    private Integer qteInStocks;
}
