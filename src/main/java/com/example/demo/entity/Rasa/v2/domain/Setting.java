package com.example.demo.entity.Rasa.v2.domain;

import java.util.ArrayList;

public class Setting {

    private String name;
    private ArrayList<String> details;

    public Setting(){}
    public Setting(String name){
        setName(name);
        setDetails(new ArrayList<>());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "name='" + name + '\'' +
                ", details=" + details +
                '}';
    }
}
