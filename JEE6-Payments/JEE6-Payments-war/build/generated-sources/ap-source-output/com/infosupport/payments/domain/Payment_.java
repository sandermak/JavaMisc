package com.infosupport.payments.domain;

import com.infosupport.payments.domain.Account;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-08-31T12:44:00")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, Long> id;
    public static volatile SingularAttribute<Payment, Account> to;
    public static volatile SingularAttribute<Payment, BigDecimal> amount;
    public static volatile SingularAttribute<Payment, String> description;
    public static volatile SingularAttribute<Payment, Account> from;

}