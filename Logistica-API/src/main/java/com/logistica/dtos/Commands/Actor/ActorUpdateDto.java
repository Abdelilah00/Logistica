package com.logistica.dtos.Commands.Actor;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorUpdateDto extends BaseDto {
    private String name;
    private String adresse;
    private String nRCommerce;
    private String actorRole;
    private String Contact;
    private String Sector;
    private String Bank;
}
