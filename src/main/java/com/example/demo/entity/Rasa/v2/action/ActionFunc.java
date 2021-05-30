package com.example.demo.entity.Rasa.v2.action;

import java.util.ArrayList;

public class ActionFunc {
    private String actionName;
    private ArrayList<String> content;

    public ActionFunc(){}
    public ActionFunc(String name){
        setActionName(name);
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public String getActionName() {
        return actionName;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ActionFunc{" +
                "actionName='" + actionName + '\'' +
                ", content=" + content +
                '}';
    }
}
