package com.example.demo.entity.Rasa.legacy;

import java.util.ArrayList;
import java.util.HashMap;

public class DomainObj {

    public DomainObj(){}
    private ArrayList<String> intent;
    private ArrayList<String> actions;
    private HashMap<String, String> template;

    public void setTemplate(HashMap<String, String> template) {
        this.template = template;
    }

    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }

    public void setIntent(ArrayList<String> intent) {
        this.intent = intent;
    }

    public ArrayList<String> getIntent() {
        return intent;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public HashMap<String, String> getTemplate() {
        return template;
    }

    @Override
    public String toString() {
        return "DomainObj{" +
                "intent=" + intent +
                ", actions=" + actions +
                ", template=" + template +
                '}';
    }
}
