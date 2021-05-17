////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.bootstrap;


import com.configuration.TenantContext;
import com.configuration.security.repositories.IUserRepository;
import com.logistica.domains.Commands.*;
import com.logistica.domains.Products.*;
import com.logistica.repositories.Commands.IActorRepository;
import com.logistica.repositories.ITestRepository;
import com.logistica.repositories.Products.*;
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
    private IOutputRepository iOutputRepository;
    @Autowired
    private IActorRepository iActorRepository;

    @Override
    public void run(String... args) throws Exception {

        if (iCategoryRepository.findAll().size() > 0)
            return;

        //create categories
        var catA = new Category();
        catA.setName("category A");
        var prodA = new Product();
        prodA.getCategory().setId(1);
        prodA.setName("product A");
        prodA.setStockMax(1000);
        prodA.setStockMin(100);
        prodA.setStockSecurity(300);
        prodA.setCategory(catA);

        var catB = new Category();
        catB.setName("category B");
        var prodB = new Product();
        prodB.getCategory().setId(1);
        prodB.setName("product B");
        prodB.setStockMax(1500);
        prodB.setStockMin(50);
        prodB.setStockSecurity(500);
        prodB.setCategory(catB);

        //create input
        var input = new Input();
        input.setRef("Ref2145");
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

        //create output
        var output = new Output();
        output.setRef("Ref2145");
        output.setDate(new Date());
        output.setDescription("input u wanna details its just for test");
        output.setAskBy("Supplier A");
        output.setDistination("Service IT");

        var transactionDetails2 = new ArrayList<TransactionDetail>();
        var trans21 = new TransactionDetail();
        trans21.getProduct().setId(1);
        trans21.setPriceHT(20f);
        trans21.setTVA(0.25f);
        trans21.setQte(50);
        transactionDetails2.add(trans21);
        output.setTransactionDetails(transactionDetails2);
        iOutputRepository.save(output);

        //create stock/stock-respo
        var actor1 = new Actor();
        actor1.setName("responsible name");
        var responsible = new ActorType();
        responsible.setName("Responsible");
        actor1.getActorIsType().setActorType(responsible);
        var stock = new Stock();
        stock.setName("stock 11");
        stock.setAdresse("db allal elffasi");
        stock.setArea(1545d);
        stock.setResponsible(actor1);
        iStockRepository.save(stock);

        //create supplier
        var actor2 = new Actor();
        actor2.setAdresse("db alla elfassi");
        actor2.setName("supplier name");
        actor2.setNRCommerce("sdf3256 5065655s4df ");
        var sector = new Sector();
        sector.setName("l7aliib");
        actor2.setSector(sector);
        var bank = new Bank();
        bank.setAccountNumber("25154545");
        bank.setCode("25154545");
        bank.setName("CIH");
        bank.setRIB("254545456465465468789");
        actor2.setBank(bank);
        var contact = new Contact();
        contact.setEmail("abdelilah@gmail.com");
        contact.setPhone("0676958566");
        contact.setWebSite("360tech.com");
        actor2.setContact(contact);
        var supplier = new ActorType();
        supplier.setName("Supplier");
        actor2.getActorIsType().setActorType(supplier);
        iActorRepository.save(actor2);

        //create client
        var actor3 = new Actor();
        actor3.setAdresse("db alla elfassi");
        actor3.setName("client name");
        actor3.setNRCommerce("sdf3256 5065655s4df ");
        var sector2 = new Sector();
        sector.setName("jabir");
        actor3.setSector(sector2);
        var bank2 = new Bank();
        bank.setAccountNumber("25154545");
        bank.setCode("25154545");
        bank.setName("CIH");
        bank.setRIB("254545456465465468789");
        actor3.setBank(bank2);
        var contact2 = new Contact();
        contact.setEmail("abdelilah@gmail.com");
        contact.setPhone("0676958566");
        contact.setWebSite("360tech.com");
        actor3.setContact(contact2);
        var client = new ActorType();
        client.setName("Client");
        actor3.getActorIsType().setActorType(client);
        iActorRepository.save(actor3);
    }
}
