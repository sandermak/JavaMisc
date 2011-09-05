package com.infosupport.payments.web;

import com.infosupport.payments.domain.Payment;
import com.infosupport.payments.domain.Payment.Status;
import com.infosupport.payments.domain.PaymentDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class PaymentController implements Serializable {
    private Payment.Status filter;

    @Produces @RequestScoped @Named
    private Payment newPayment = new Payment();
    
    @Inject PaymentDAO paymentDAO;
    
    @Produces @RequestScoped @Named
    public List<Payment> getPayments() {
        if(filter == null) {
            return paymentDAO.getAllPayments();
        } else {
            return paymentDAO.getAllPayments(filter);
        }
    }
    
    @Produces @Named
    public Payment.Status[] getPaymentStatuses() {
        return Payment.Status.values();
    }
    
    public List<Payment.Status> getPaymentStatusesWithout(Payment.Status status) {
        Payment.Status[] statuses = Payment.Status.values();
        List<Payment.Status> filtered = new ArrayList<Payment.Status>();
        for(Payment.Status st: statuses) {
            if(status != st)
                filtered.add(st);
        }
        return filtered;
    }
    
    public String storePayment() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Created new payment"));
        paymentDAO.store(newPayment);
        newPayment = new Payment();
        return "index.xhtml?faces-redirect=true";
    }
    
    public String storePayment(Payment payment) {
        System.out.println("Storing " + payment.toString() + " with status " + payment.getStatus());
        paymentDAO.store(payment);
        return "index.xhtml?faces-redirect=true";
    }
    
    public Status getFilter() {
        return filter;
    }

    public void setFilter(Status filter) {
        this.filter = filter;
    }
}
