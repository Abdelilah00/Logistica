package com.logistica.dtos.Commands.Actor;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Commands.Bank.BankCreateDto;
import com.logistica.dtos.Commands.Contact.ContactCreateDto;
import com.logistica.dtos.Commands.Sector.SectorCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorCreateDto extends BaseDto {
    private String name;
    private String adresse;
    private String nRCommerce;
    private String actorRole;
    private ContactCreateDto Contact;
    private SectorCreateDto Sector;
    private BankCreateDto Bank;
}
