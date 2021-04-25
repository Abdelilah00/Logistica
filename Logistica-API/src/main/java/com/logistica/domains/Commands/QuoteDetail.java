package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quotedetail")
public class QuoteDetail extends BaseEntity {
    private Integer qte;
    private Double unitPrice;
    private String productName;
    private String productProposedName;
    @ManyToOne
    private Quote quote;
}
