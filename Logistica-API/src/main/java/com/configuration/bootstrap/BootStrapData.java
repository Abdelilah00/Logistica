////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.bootstrap;


import com.configuration.TenantContext;
import com.configuration.security.repositories.IUserRepository;
import com.logistica.domains.Products.Category;
import com.logistica.domains.Products.Product;
import com.logistica.repositories.ITestRepository;
import com.logistica.repositories.Products.ICategoryRepository;
import com.logistica.repositories.Products.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootStrapData implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private ITestRepository iTestRepository;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public void run(String... args) throws Exception {

        /*var cat = new Category();
        cat.setName("category A");
        iCategoryRepository.save(cat);
        var prod = new Product();
        prod.setPriceHT(12.5f);
        prod.getCategory().setId(1);
        prod.setName("prod A");
        prod.setStockMax(10);
        prod.setStockMin(2);
        prod.setStockSecurity(5);
        //prod.set(5);
        prod.setStockSecurity(5);
        prod.setExpDate(new Date());
        iProductRepository.save(prod);;*/


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

/*      var tests = new ArrayList<Test>();
        for (int i = 0; i < 1000; i++) {
            var test = new Test();
            test.setValue(String.valueOf(i));
            tests.add(test);
        }

        iTestRepository.saveAll(tests);*/
    }
}
