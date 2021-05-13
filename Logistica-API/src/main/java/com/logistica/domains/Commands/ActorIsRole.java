package com.logistica.domains.Commands;

import com.alexy.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actorisrole")
public class ActorIsRole extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    private Actor actor;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
}
