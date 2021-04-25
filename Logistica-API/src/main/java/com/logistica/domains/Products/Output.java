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
@Table(name = "output")
public class Output extends BaseEntity {
    private String distination;
    private String askBy;
    private String DistinationType;

    @OneToMany(mappedBy = "output")
    //todo: get a list of transaction details without need to relational table
    private List<TransactionDetail> transactionDetails = new ArrayList<>();
}
