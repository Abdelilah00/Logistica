package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Contact;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends IBaseJpaRepository<Contact> {
}
