package com.infosupport.payments.domain;

import com.infosupport.payments.domain.Payment.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PaymentDAO {
    
    @PersistenceContext EntityManager em;
    
    public List<Payment> getAllPayments() {
        final TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p", Payment.class);
        return query.getResultList();
    }

    public List<Payment> getAllPayments(Status status) {
        final TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p WHERE p.status = :status", Payment.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    public void store(Payment payment) {
        em.merge(payment);
    }
}
