/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosupport.jee.playground.ws;

import com.infosupport.jee.playground.entity.Product;
import com.infosupport.jee.playground.session.ProductFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sanderma
 */
@WebService(serviceName = "ProductWebservice")
public class ProductWebservice {
    
    private static final Logger logger = Logger.getLogger("soap");
    
    @Inject
    private ProductFacade productFacade;
    
    /** This is a sample web service operation */
    @WebMethod(operationName = "getProduct")
    public Product hello(@WebParam(name = "id") int id) {
        logger.log(Level.INFO, "Got id: " + id);
        final Product product = productFacade.find(id);
        return product;
    }
}
