/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosupport.jee.playground.session;

import com.infosupport.jee.playground.entity.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sanderma
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "JEE6-Playground-warPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
}
