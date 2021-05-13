package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commandout")
public class CommandOut extends BaseEntity {
    private String description;
    @OneToOne
    private Needs needs;
    @OneToOne
    private Actor actor;

    //private  CommandDetail commandDetail;
    //private  PayementMethod payementMethod;
}
