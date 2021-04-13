////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.bootstrap;


import com.configuration.TenantContext;
import com.configuration.security.repositories.IUserRepository;
import com.logistica.domains.Test;
import com.logistica.repositories.ITestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BootStrapData implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private ITestRepository iTestRepository;

    @Override
    public void run(String... args) throws Exception {
        /*var user1 = new User();
        var admin = new Role();
        admin.setName(RoleName.ROLE_ADMIN);
        user1.setUserName("admin");
        user1.setPassword("admin");
        user1.setActive(true);
        user1.setEmail("admin@admin.com");
        user1.setRoles(Collections.singletonList(admin));
        iUserRepository.save(user1);
*/
        //////////////////////////////////////////

/*        var tests = new ArrayList<Test>();
        for (int i = 0; i < 1000; i++) {
            var test = new Test();
            test.setValue(String.valueOf(i));
            tests.add(test);
        }

        iTestRepository.saveAll(tests);*/
    }
}