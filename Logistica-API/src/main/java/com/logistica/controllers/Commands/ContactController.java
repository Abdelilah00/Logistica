package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Commands.Contact;
import com.logistica.dtos.Commands.Contact.ContactCreateDto;
import com.logistica.dtos.Commands.Contact.ContactDto;
import com.logistica.dtos.Commands.Contact.ContactUpdateDto;

@RestController
@RequestMapping("api/contacts")
public class ContactController extends BaseCrudController<Contact, ContactDto, ContactCreateDto, ContactUpdateDto> {
}
