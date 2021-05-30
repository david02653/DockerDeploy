package com.example.demo.entity.Rasa.v2.domain;

import java.util.ArrayList;

public class IntentSet {
    private String intentName;
    private ArrayList<String> availableEntity;
    private ArrayList<String> ignoreEntity;

    public IntentSet(){
    }
    public IntentSet(String name){
        setIntentName(name);
        this.availableEntity = new ArrayList<>();
        this.ignoreEntity = new ArrayList<>();
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public void setAvailableEntity(ArrayList<String> availableEntity) {
        this.availableEntity = availableEntity;
    }

    public void setIgnoreEntity(ArrayList<String> ignoreEntity) {
        this.ignoreEntity = ignoreEntity;
    }

    public String getIntentName() {
        return intentName;
    }

    public ArrayList<String> getAvailableEntity() {
        return availableEntity;
    }

    public ArrayList<String> getIgnoreEntity() {
        return ignoreEntity;
    }

    @Override
    public String toString() {
        return "IntentSet{" +
                "intentName='" + intentName + '\'' +
                ", availableEntity=" + availableEntity +
                ", ignoreEntity=" + ignoreEntity +
                '}';
    }
}
