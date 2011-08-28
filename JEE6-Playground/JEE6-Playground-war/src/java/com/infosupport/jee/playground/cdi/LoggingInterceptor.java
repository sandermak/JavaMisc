/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosupport.jee.playground.cdi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author sbmak
 */
@Logging @Interceptor
public class LoggingInterceptor {
    
    private Logger logger = Logger.getLogger(getClass().toString());
   
    
    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
        long start = System.currentTimeMillis();
        logger.log(Level.INFO, "Invoking method: {0}", ic.getMethod().toString());
        Object result = ic.proceed();
        logger.log(Level.INFO, "Execution time {0}ms", System.currentTimeMillis() - start);
        return result;
    }
}
