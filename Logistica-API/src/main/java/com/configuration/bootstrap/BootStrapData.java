////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.bootstrap;


import com.configuration.TenantContext;
import com.configuration.security.repositories.IUserRepository;
import com.logistica.domains.Commands.*;
import com.logistica.domains.Organization.Structure;
import com.logistica.domains.Organization.StructureUnit;
import com.logistica.domains.Products.*;
import com.logistica.repositories.Commands.IActorRepository;
import com.logistica.repositories.Commands.IActorRoleRepository;
import com.logistica.repositories.ITestRepository;
import com.logistica.repositories.Organization.IStructureRepository;
import com.logistica.repositories.Organization.IStructureUnitRepository;
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
    private IStockRepository iStockRepository;
    @Autowired
    private IInputRepository iInputRepository;
    @Autowired
    private IOutputRepository iOutputRepository;
    @Autowired
    private IActorRepository iActorRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private IActorRoleRepository iActorRoleRepository;
    @Autowired
    private IStructureRepository iStructureRepository;
    @Autowired
    private IStructureUnitRepository iStructureUnitRepository;

    @Override
    public void run(String... args) throws Exception {

        if (iInputRepository.findAll().size() > 0)
            return;

        //region organisation
        var act = new Actor();
        act.setName("jamal din lbarhouch");
        iActorRepository.save(act);
        var structureDepartament = new Structure();
        var structureBlock = new Structure();
        var structureService = new Structure();
        structureDepartament.setName("Departament");
        structureDepartament.setRang(1);
        structureBlock.setName("Block");
        structureBlock.setRang(2);
        structureService.setName("Service");
        structureService.setRang(3);
        var structures = new ArrayList<Structure>();
        structures.add(structureDepartament);
        structures.add(structureBlock);
        structures.add(structureService);
        iStructureRepository.saveAll(structures);

        var serviceEnf = new StructureUnit();
        serviceEnf.setName("service enfant");
        serviceEnf.setActor(act);
        serviceEnf.setStructure(structureService);
        serviceEnf.setActor(act);
        iStructureUnitRepository.save(serviceEnf);
        //endregion

        //region categories & products
        var catA = new Category();
        catA.setName("category A");
        iCategoryRepository.save(catA);
        var catB = new Category();
        catB.setName("category B");
        iCategoryRepository.save(catB);

        var prodA = new Product();
        prodA.setName("product A");
        prodA.setStockMax(1000);
        prodA.setStockMin(100);
        prodA.setStockSecurity(300);
        prodA.setCategory(catA);
        iProductRepository.save(prodA);
        var prodB = new Product();
        prodB.setName("product B");
        prodB.setStockMax(1500);
        prodB.setStockMin(50);
        prodB.setStockSecurity(500);
        prodB.setCategory(catB);
        iProductRepository.save(prodB);
        var prodC = new Product();
        prodC.setName("product C");
        prodC.setStockMax(1000);
        prodC.setStockMin(100);
        prodC.setStockSecurity(300);
        prodC.setCategory(catA);
        iProductRepository.save(prodC);
        //endregion

        //region actors roles
        var responsible = new ActorRole();
        responsible.setName("Responsible");
        iActorRoleRepository.save(responsible);
        var supplier = new ActorRole();
        supplier.setName("Supplier");
        iActorRoleRepository.save(supplier);
        var client = new ActorRole();
        client.setName("Client");
        iActorRoleRepository.save(client);
        //endregion

        //region actor
        var actor1 = new Actor();
        actor1.setName("responsible name");
        actor1.getActorHasRole().setActorType(responsible);
        iActorRepository.save(actor1);
        //endregion

        //region stocks && stockProduct
        var stock = new Stock();
        stock.setName("stock principal");
        stock.setAdresse("db allal elffasi");
        stock.setArea(1545d);
        stock.setDef(Boolean.TRUE);
        stock.setResponsible(actor1);
        var sp = new StockProduct();
        sp.setProduct(prodA);
        sp.setQte(100);
        sp.setStock(stock);
        var sp2 = new StockProduct();
        sp2.setProduct(prodB);
        sp2.setQte(100);
        sp2.setStock(stock);
        var sp3 = new StockProduct();
        sp3.setProduct(prodC);
        sp3.setQte(100);
        sp3.setStock(stock);
        stock.getStockProducts().add(sp);
        stock.getStockProducts().add(sp2);
        stock.getStockProducts().add(sp3);
        iStockRepository.save(stock);

        var stock2 = new Stock();
        stock2.setName("stock secondaire");
        stock2.setAdresse("db jakarta");
        stock2.setArea(655d);
        //stock2.setDef(Boolean.FALSE);
        stock2.setResponsible(actor1);
        var sp21 = new StockProduct();
        sp21.setProduct(prodA);
        sp21.setQte(50);
        sp21.setStock(stock2);
        var sp22 = new StockProduct();
        sp22.setProduct(prodB);
        sp22.setQte(50);
        sp22.setStock(stock2);
        stock2.getStockProducts().add(sp21);
        stock2.getStockProducts().add(sp22);
        iStockRepository.save(stock2);
        //endregion

        //region create supplier
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
        actor2.getActorHasRole().setActorType(supplier);
        iActorRepository.save(actor2);
        //endregion

        //region create client
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
        actor3.getActorHasRole().setActorType(client);
        iActorRepository.save(actor3);
        //endregion

        //region create input
        var input = new Input();
        input.setRef("Ref2145");
        input.setDate(new Date());
        input.setDescription("input u wanna details its just for test");
        input.setActor(actor2);

        var transactionDetails = new ArrayList<TransactionDetail>();
        var trans11 = new TransactionDetail();
        trans11.setExpDate(new Date());
        trans11.setProduct(prodA);
        trans11.setPriceHT(20f);
        trans11.setArticle(5);
        trans11.setLot(2);
        trans11.setTVA(0.25f);
        trans11.setQte(50);
        trans11.setInput(input);
        transactionDetails.add(trans11);
        var trans12 = new TransactionDetail();
        trans12.setExpDate(new Date());
        trans12.setProduct(prodB);
        trans12.setPriceHT(36f);
        trans12.setArticle(5);
        trans12.setLot(2);
        trans12.setTVA(0.6f);
        trans12.setQte(100);
        trans12.setInput(input);
        transactionDetails.add(trans12);
        input.setTransactionDetails(transactionDetails);
        iInputRepository.save(input);
        //endregion

        //region create output
        var output = new Output();
        output.setRef("Ref2145");
        output.setDate(new Date());
        output.setDescription("output u wanna details its just for test");
        output.setAskBy("part of Supplier A");
        output.setActor(actor3);

        var transactionDetails2 = new ArrayList<TransactionDetail>();
        var trans21 = new TransactionDetail();
        trans21.setProduct(prodC);
        trans21.setPriceHT(20f);
        trans21.setTVA(0.25f);
        trans21.setQte(50);
        trans21.setOutput(output);
        transactionDetails2.add(trans21);
        output.setTransactionDetails(transactionDetails2);
        iOutputRepository.save(output);
        //endregion
    }
}
