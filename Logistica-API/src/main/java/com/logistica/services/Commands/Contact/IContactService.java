package com.logistica.services.Commands.Contact;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Contact;
import com.logistica.dtos.Commands.Contact.ContactCreateDto;
import com.logistica.dtos.Commands.Contact.ContactDto;
import com.logistica.dtos.Commands.Contact.ContactUpdateDto;

public interface IContactService extends IBaseCrudService<Contact, ContactDto, ContactCreateDto, ContactUpdateDto> {

}
