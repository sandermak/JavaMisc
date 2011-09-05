package com.infosupport.payments.domain;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DataInserter {
    
    @PersistenceContext EntityManager em;
    
    @PostConstruct
    public void insertData() {
        Account acc1 = new Account("Sander", "123");
        Account acc2 = new Account("Ruben", "456");
        Payment p1 = new Payment(acc1, acc2, "for fun!", new BigDecimal(3));
        em.persist(acc1);
        em.persist(acc2);
        em.persist(p1);
    }
}
