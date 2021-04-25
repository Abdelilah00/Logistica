package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productcharacteritic")
public class ProductCharacteritic extends BaseEntity {
    private String value;

    @ManyToOne
    private Product product = new Product();
    @ManyToOne
    private Characteristic characteristic = new Characteristic();
}
