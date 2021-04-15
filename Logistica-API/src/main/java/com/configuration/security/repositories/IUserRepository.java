package com.configuration.security.repositories;

import com.configuration.security.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IUserRepository extends JpaRepository<User, Serializable> {
    User findByUserName(String userName);

    Boolean existsByUserName(String userName);
}
