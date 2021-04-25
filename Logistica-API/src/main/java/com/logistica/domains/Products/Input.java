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
@Table(name = "input")
public class Input extends BaseEntity {
    private String acteur;
    private String ActeurType;
    private String Stage;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "input")
    //todo: get a list of transaction details without need to relational table
    private List<TransactionDetail> transactionDetails = new ArrayList<>();
}
