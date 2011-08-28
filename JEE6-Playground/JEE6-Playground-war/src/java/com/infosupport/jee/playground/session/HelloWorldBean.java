/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosupport.jee.playground.session;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 *
 * @author sbmak
 */
@Stateless
public class HelloWorldBean {
    
    public String hello(String hello) {
        return "Hello " + hello + " world!";
    }
    
    public void consumer(@Observes String msg) {
        System.out.println("Got event: " + msg);
    }
}
