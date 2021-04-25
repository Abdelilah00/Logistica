package com.logistica.domains.Products;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "characteristic")
public class Characteristic extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "characteristic")
    private List<ProductCharacteritic> productCharacteritics = new ArrayList<>();
}
