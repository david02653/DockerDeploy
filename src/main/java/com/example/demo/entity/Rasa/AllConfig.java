package com.example.demo.entity.Rasa;

import java.util.ArrayList;

public class AllConfig {

    public AllConfig(){}

    private ArrayList<String> nlu;
    private ArrayList<String> domain;
    private ArrayList<String> stories;
    private ArrayList<String> action;

    public void setNlu(ArrayList<String> nlu) {
        this.nlu = nlu;
    }

    public void setDomain(ArrayList<String> domain) {
        this.domain = domain;
    }

    public void setStories(ArrayList<String> stories) {
        this.stories = stories;
    }

    public void setAction(ArrayList<String> action) {
        this.action = action;
    }

    public ArrayList<String> getNlu() {
        return nlu;
    }

    public ArrayList<String> getDomain() {
        return domain;
    }

    public ArrayList<String> getStories() {
        return stories;
    }

    public ArrayList<String> getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "AllConfig{" +
                "nlu=" + nlu +
                ", domain=" + domain +
                ", stories=" + stories +
                ", action=" + action +
                '}';
    }
}
