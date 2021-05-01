////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.bootstrap;


import com.configuration.TenantContext;
import com.configuration.security.repositories.IUserRepository;
import com.logistica.domains.Commands.Bank;
import com.logistica.domains.Commands.Contact;
import com.logistica.domains.Commands.Sector;
import com.logistica.domains.Commands.Supplier;
import com.logistica.domains.Products.*;
import com.logistica.repositories.Commands.ISupplierRepository;
import com.logistica.repositories.ITestRepository;
import com.logistica.repositories.Products.ICategoryRepository;
import com.logistica.repositories.Products.IInputRepository;
import com.logistica.repositories.Products.IProductRepository;
import com.logistica.repositories.Products.IStockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    @Autowired
    private IStockRepository iStockRepository;
    @Autowired
    private IInputRepository iInputRepository;
    @Autowired
    private ISupplierRepository iSupplierRepository;

    @Override
    public void run(String... args) throws Exception {

        if (iCategoryRepository.findAll().size() > 0)
            return;

        var catA = new Category();
        catA.setName("category A");
        var prodA = new Product();
        prodA.getCategory().setId(1);
        prodA.setName("product A");
        prodA.setStockMax(1000);
        prodA.setStockMin(100);
        prodA.setStockSecurity(300);
        prodA.setCategory(catA);
        //iProductRepository.save(prodA);

        var catB = new Category();
        catB.setName("category B");
        var prodB = new Product();
        prodB.getCategory().setId(1);
        prodB.setName("product B");
        prodB.setStockMax(1500);
        prodB.setStockMin(50);
        prodB.setStockSecurity(500);
        prodB.setCategory(catB);
        //iProductRepository.save(prodB);

        var input = new Input();
        input.setDate(new Date());
        input.setDescription("input u wanna details its just for test");
        input.setSupplierName("Supplier A");

        var transactionDetails = new ArrayList<TransactionDetail>();
        var trans1 = new TransactionDetail();
        trans1.setExpDate(new Date());
        trans1.setProduct(prodA);
        trans1.setPriceHT(20f);
        trans1.setArticle(5);
        trans1.setLot(2);
        trans1.setTVA(0.25f);
        trans1.setQte(50);
        transactionDetails.add(trans1);
        var trans2 = new TransactionDetail();
        trans2.setExpDate(new Date());
        trans2.setProduct(prodB);
        trans2.setPriceHT(36f);
        trans2.setArticle(5);
        trans2.setLot(2);
        trans2.setTVA(0.6f);
        trans2.setQte(100);
        transactionDetails.add(trans2);
        input.setTransactionDetails(transactionDetails);

        iInputRepository.save(input);


        var respo = new StockRespo();
        respo.setName("Abdelilah");
        var stock = new Stock();
        stock.setName("stock 11");
        stock.setAdresse("db allal elffasi");
        stock.setArea(1545d);
        stock.setStockRespo(respo);
        iStockRepository.save(stock);

        var supplier = new Supplier();
        supplier.setAdresse("db alla elfassi");
        supplier.setName("jabri ilyass");
        supplier.setNRCommerce("sdf3256 5065655s4df ");
        var sector = new Sector();
        sector.setName("l7aliib");
        supplier.setSector(sector);
        var bank = new Bank();
        bank.setAccountNumber("25154545");
        bank.setCode("25154545");
        bank.setName("CIH");
        bank.setRIB("254545456465465468789");
        supplier.setBank(bank);
        var contact = new Contact();
        contact.setEmail("abdelilah@gmail.com");
        contact.setPhone("0676958566");
        contact.setWebSite("360tech.com");
        supplier.setContact(contact);
        iSupplierRepository.save(supplier);
    }
}
