package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.Contact;

@Repository
public interface IContactRepository extends IBaseJpaRepository<Contact> {
}
