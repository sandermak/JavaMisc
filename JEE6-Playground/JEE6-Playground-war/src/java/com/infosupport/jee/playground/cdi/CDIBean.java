/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosupport.jee.playground.cdi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author sbmak
 */
@MyQualifier
@Logging
public class CDIBean {

    @Inject
    Event<String> logEvent;
    
    private Logger logger = Logger.getLogger(getClass().toString());

    @PostConstruct
    public void init() {
        final String msg = "CDIBean initialized!!";
        logEvent.fire(msg);
        logger.log(Level.INFO, msg);
    }

    public String testCDIBean() {
        return "It works!";
    }
}
