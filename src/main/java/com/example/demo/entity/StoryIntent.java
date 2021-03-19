package com.example.demo.entity;

import java.util.ArrayList;

public class StoryIntent {

    public StoryIntent(){}

    private String intentName;
    private ArrayList<String> actionList;

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public void setActionList(ArrayList<String> actionList) {
        this.actionList = actionList;
    }

    public String getIntentName() {
        return intentName;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }

    @Override
    public String toString() {
        return "StoryIntent{" +
                "intentName='" + intentName + '\'' +
                ", actionList=" + actionList +
                '}';
    }
}
