package com.example.demo.entity.Rasa.v2.domain;

import java.util.ArrayList;

public class SlotSet {

    private String slotName;
    private String type;
    // text, boolean, categorical, float, list, any, customized(python class path)
    // customized type of slot required customized definition (python)
    private boolean influenceConversation;
    private ArrayList<String> categoryList; // for categorical type
    private boolean autoFill;

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInfluenceConversation(boolean influenceConversation) {
        this.influenceConversation = influenceConversation;
    }

    public void setCategoryList(ArrayList<String> categoryList) {
        this.categoryList = categoryList;
    }

    public void setAutoFill(boolean autoFill) {
        this.autoFill = autoFill;
    }

    public String getSlotName() {
        return slotName;
    }

    public String getType() {
        return type;
    }

    public boolean isInfluenceConversation() {
        return influenceConversation;
    }

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public boolean isAutoFill() {
        return autoFill;
    }

    @Override
    public String toString() {
        return "SlotSet{" +
                "slotName='" + slotName + '\'' +
                ", type='" + type + '\'' +
                ", influenceConversation=" + influenceConversation +
                ", categoryList=" + categoryList +
                ", autoFill=" + autoFill +
                '}';
    }
}
