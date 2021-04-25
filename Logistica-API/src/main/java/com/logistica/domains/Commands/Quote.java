package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
import com.logistica.domains.Products.QuoteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quote")
public class Quote extends BaseEntity {
    private Date expDate;
    private QuoteStatus quoteStatus;

    @OneToOne
    private Supplier supplier;
    @OneToOne
    private Offer offer;
    @OneToMany(mappedBy = "quote")
    private List<QuoteDetail> quoteDetail = new ArrayList<>();
}
