package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "offer")
public class Offer extends BaseEntity {
    private Date dateStart;
    private Date dateEnd;

    @OneToMany
    private List<Needs> needs = new ArrayList<>();

    //private  Status status;
    //private OffreDetail offreDetail;
}
