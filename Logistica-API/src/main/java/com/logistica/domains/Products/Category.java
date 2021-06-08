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
@Table(name = "category")
public class Category extends BaseEntity {
    private String name;
    private Float defaultTva;
    private Long defaultStockMin;
    private Long defaultStockMax;
    private Long defaultStockSecurity;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Category parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Category> children = new ArrayList<>();
}
