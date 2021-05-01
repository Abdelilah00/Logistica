package com.logistica.services.Commands.Contact;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.Contact;
import com.logistica.dtos.Commands.Contact.ContactCreateDto;
import com.logistica.dtos.Commands.Contact.ContactDto;
import com.logistica.dtos.Commands.Contact.ContactUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class ContactService extends BaseCrudServiceImpl<Contact, ContactDto, ContactCreateDto, ContactUpdateDto> implements IContactService {

    public ContactService() {
        super(Contact.class, ContactDto.class, ContactCreateDto.class, ContactUpdateDto.class);
    }
}
