package com.infosupport.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@Named
@ConversationScoped
public class TestBean implements Serializable {

    private String content = "Initial content";
    private List<Person> persons = Arrays.asList(new Person("sander", "mak"), new Person("ruben", "mak"));

    public List<Person> getPersons() {
        return persons;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void save() {
        System.out.println(content);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved!"));
    }

    public PieChartModel getModel() {
        return model;
    }
    private PieChartModel model = new PieChartModel() {

        {
            set("a", 20);
            set("b", 80);
        }
    };

    public void change() {
        model.set("a", 30);
        model.set("b", 70);
    }
}
