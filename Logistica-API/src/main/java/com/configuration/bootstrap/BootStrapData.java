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
import java.util.Collections;
import java.util.Date;
import java.util.Random;

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
    @Autowired
    private IInputRepository iInputRepository;
    @Autowired
    private IOutputRepository iOutputRepository;
    @Autowired
    private IStockProductRepository iStockProductRepository;


    @Override
    public void run(String... args) throws InterruptedException {

        if (iInputRepository.findAll().size() == 0) {

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

            // region organisation
            var act = new Actor();
            act.setName("jamal alpha");
            act.getActorHasRole().setActorRole(client);
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
            catA.setDefaultTva(20.1f);
            catA.setDefaultStockMin(500L);
            catA.setDefaultStockSecurity(2500L);
            catA.setDefaultStockMax(5000L);
            var catA1 = new Category();
            catA1.setName("category A1");
            catA1.setParent(catA);
            var catA2 = new Category();
            catA2.setName("category A2");
            catA2.setParent(catA);
            catA.getChildren().add(catA1);
            catA.getChildren().add(catA2);
            iCategoryRepository.save(catA);

            var catB = new Category();
            catB.setName("category B");
            var catB1 = new Category();
            catB1.setName("category B1");
            catB1.setParent(catB);
            catB.getChildren().add(catB1);
            iCategoryRepository.save(catB);

            var prodA = new Product();
            prodA.setName("product A");
            prodA.setStockMax(5000);
            prodA.setStockMin(1000);
            prodA.setStockSecurity(3000);
            prodA.setPriceHT(1.5F);
            prodA.setTVA(10f);
            prodA.setCategory(catA);
            iProductRepository.save(prodA);
            var prodB = new Product();
            prodB.setName("product B");
            prodA.setStockMax(5000);
            prodA.setStockMin(1000);
            prodA.setStockSecurity(3000);
            prodB.setPriceHT(1.5F);
            prodB.setTVA(10f);
            prodB.setCategory(catB);
            iProductRepository.save(prodB);
            var prodC = new Product();
            prodC.setName("product C");
            prodA.setStockMax(5000);
            prodA.setStockMin(1000);
            prodA.setStockSecurity(3000);
            prodC.setPriceHT(1.5F);
            prodC.setTVA(10f);
            prodC.setCategory(catA);
            iProductRepository.save(prodC);
            //endregion


            //region actor
            var actor1 = new Actor();
            actor1.setName("responsible name");
            actor1.getActorHasRole().setActorRole(responsible);
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
            sp.setQte(25);
            sp.setStock(stock);
            var sp2 = new StockProduct();
            sp2.setProduct(prodB);
            sp2.setQte(50);
            sp2.setStock(stock);
            var sp3 = new StockProduct();
            sp3.setProduct(prodC);
            sp3.setQte(50);
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
            sp21.setQte(25);
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
            actor2.getActorHasRole().setActorRole(supplier);
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
            actor3.getActorHasRole().setActorRole(client);
            iActorRepository.save(actor3);
            //endregion

            /*            //region create input
            var input = new Input();
            input.setRef("Ref2145");
            input.setDate(new Date());
            input.setDescription("input u wanna details its just for test");
            input.setActor(actor2);

            var transactionDetails = new ArrayList<InputDetails>();
            var trans11 = new InputDetails();
            trans11.setExpDate(new Date());
            trans11.setProduct(prodA);
            trans11.setArticle(5);
            trans11.setLot(2);
            trans11.setQte(50);
            trans11.setInput(input);
            transactionDetails.add(trans11);
            var trans12 = new InputDetails();
            trans12.setExpDate(new Date());
            trans12.setProduct(prodB);
            trans12.setArticle(5);
            trans12.setLot(2);
            trans12.setQte(100);
            trans12.setInput(input);
            transactionDetails.add(trans12);
            var trans13 = new InputDetails();
            trans13.setExpDate(new Date());
            trans13.setProduct(prodC);
            trans13.setArticle(5);
            trans13.setLot(2);
            trans13.setQte(100);
            trans13.setInput(input);
            transactionDetails.add(trans13);
            input.setInputDetails(transactionDetails);
            iInputRepository.save(input);
            //endregion

            //region create output
            var output = new Output();
            output.setRef("Ref2145");
            output.setDate(new Date());
            output.setDescription("output u wanna details its just for test");
            output.setAskBy("part of Supplier A");
            output.setActor(actor3);

            var transactionDetails2 = new ArrayList<OutputDetails>();
            var trans21 = new OutputDetails();
            trans21.setProduct(prodC);
            trans21.setPriceHT(20f);
            trans21.setTVA(0.25f);
            trans21.setQte(50);
            trans21.setOutput(output);
            transactionDetails2.add(trans21);
            output.setOutputDetails(transactionDetails2);
            iOutputRepository.save(output);
            //endregion*/

            statStockData();
        }

    }

    private void statStockData() {
        var random = new Random();
        var categories = new ArrayList<Category>();
        var products = new ArrayList<Product>();
        var inputs = new ArrayList<Input>();
        var outputs = new ArrayList<Output>();
        var actors = new ArrayList<Actor>();

        for (int i = 0; i < 50; i++) {
            var actor = new Actor();
            actor.setAdresse("db alla elfassi");
            actor.setName("client " + i);
            actor.setNRCommerce("sdf3256 5065655s4df");
            actor.getActorHasRole().getActorRole().setId(3L);
            actors.add(actor);
        }
        for (int i = 0; i < 20; i++) {
            var actor = new Actor();
            actor.setAdresse("db alla elfassi");
            actor.setName("supplier " + i);
            actor.setNRCommerce("sdf3256 5065655s4df");
            actor.getActorHasRole().getActorRole().setId(2L);
            actors.add(actor);
        }
        iActorRepository.saveAll(actors);

        for (int i = 1; i <= 5; i++) {
            var catA = new Category();
            catA.setName("category " + i);
            catA.setDefaultTva(20.1f);
            catA.setDefaultStockMin(500L);
            catA.setDefaultStockSecurity(2500L);
            catA.setDefaultStockMax(5000L);
            categories.add(catA);
        }
        iCategoryRepository.saveAll(categories);

        for (int i = 1; i <= 17; i++) {
            var prodA = new Product();
            prodA.setName("product " + i);
            prodA.setStockMax(5000);
            prodA.setStockMin(1000);
            prodA.setStockSecurity(3000);
            prodA.setPriceHT(1.5F);
            prodA.setTVA(10f);
            prodA.getCategory().setId(random.nextInt(4) + 1);
            products.add(prodA);

        }
        iProductRepository.saveAll(products);

        for (int i = 0; i < 300; i++) {
            Long pId = (long) (random.nextInt(19) + 1);
            Long sId = (long) (random.nextInt(1) + 1);
            Integer qte = random.nextInt(100) + 50;

            var input = new Input();
            input.setRef("Ref" + i);
            input.setDate(new Date());
            input.setDescription("input description");
            input.getActor().setId(random.nextInt(20) + 55);
            input.setRetour(random.nextBoolean());
            var trans = new InputDetails();
            trans.getProduct().setId(pId);
            trans.setQte(qte);
            //trans.getStock().setId(sId);
            trans.setInput(input);
            input.setInputDetails(Collections.singletonList(trans));

            var stockProd = iStockProductRepository.findByProductIdAndStockId(pId, sId);
            if (stockProd == null) {
                stockProd = new StockProduct();
                stockProd.getProduct().setId(pId);
                stockProd.getStock().setId(sId);
                stockProd.setQte(qte);
            } else {
                stockProd.setQte(stockProd.getQte() + qte);
            }
            inputs.add(input);
            iStockProductRepository.save(stockProd);
        }
        iInputRepository.saveAll(inputs);

        for (int i = 0; i < 1000; i++) {
            Long pId = (random.nextInt(19) + 1L);
            Long sId = (random.nextInt(1) + 1L);
            Integer qte = random.nextInt(50) + 50;
            Float price = (random.nextInt(2) + 1.5F);

            var output = new Output();
            output.setRef("Ref" + i);
            output.setDate(new Date());
            output.setDescription("input u wanna details its just for test");
            output.getActor().setId(random.nextInt(50) + 4);
            output.setIntern(random.nextBoolean());
            var trans = new OutputDetails();
            trans.getProduct().setId(pId);
            trans.setQte(qte);
            //trans.getStock().setId(sId);
            trans.setOutput(output);
            trans.setPriceHT(price);
            output.setOutputDetails(Collections.singletonList(trans));

            var stockProd = iStockProductRepository.findByProductIdAndStockId(pId, sId);
            if (stockProd.getQte() >= qte) {
                stockProd.setQte(stockProd.getQte() - qte);
            }
            //stockProducts.add(stockProd);
            outputs.add(output);
        }
        iOutputRepository.saveAll(outputs);
    }

}
