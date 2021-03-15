package com.example.demo.entity;

import java.util.ArrayList;

public class Story {

    public Story(){}

    private NluObject intent;
    private ArrayList<String> action;

    public void setIntent(NluObject intent) {
        this.intent = intent;
    }

    public void setAction(ArrayList<String> action) {
        this.action = action;
    }

    public NluObject getIntent() {
        return intent;
    }

    public ArrayList<String> getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Story{" +
                "intent=" + intent +
                ", action=" + action +
                '}';
    }
}
