/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosupport.jee.playground.rest;

import com.infosupport.jee.playground.entity.Product;
import com.infosupport.jee.playground.session.ProductFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Singleton
@Path("/product/{id}")
public class ProductResource {
    
    private static final Logger logger = Logger.getLogger("rest");
    
    @Inject
    private ProductFacade productFacade;

    @GET
    @Produces("application/json")
    public Product getProduct(@PathParam("id") int id) {
        logger.log(Level.INFO, "Got id: " + id);
        final Product product = productFacade.find(id);
        return product;
    }
}
