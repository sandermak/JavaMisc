/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosupport.jee.playground;

import com.infosupport.jee.playground.cdi.CDIBean;
import com.infosupport.jee.playground.cdi.MyQualifier;
import com.infosupport.jee.playground.session.HelloWorldBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sbmak
 */
@WebServlet(urlPatterns="/test")
public class TestServlet extends HttpServlet {
    
    @Inject
    private HelloWorldBean helloWorldBean;
    
    @Inject @MyQualifier
    private CDIBean cDIBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        final PrintWriter writer = resp.getWriter();
        writer.write("Test servlet!\n\n" + helloWorldBean.hello("test"));
        writer.write("\n" + cDIBean.testCDIBean());
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        doGet(request, resp);
    }
    
}
