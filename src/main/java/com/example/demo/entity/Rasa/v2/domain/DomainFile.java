package com.example.demo.entity.Rasa.v2.domain;

import java.util.ArrayList;

/**
 * currently ignore response setting
 * move response setting to other file
 */
public class DomainFile {

    private String version;
    private ArrayList<IntentSet> intentList;
    private ArrayList<EntitySet> entityList;
    private ArrayList<SlotSet> slotList;
    private ArrayList<String> actionList;
    private ArrayList<FormSet> formList;
    private SessionConfig sessionConfig;

    public DomainFile(){}
    public DomainFile(String version){
        setVersion(version);
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setIntentList(ArrayList<IntentSet> intentList) {
        this.intentList = intentList;
    }

    public void setEntityList(ArrayList<EntitySet> entityList) {
        this.entityList = entityList;
    }

    public void setSlotList(ArrayList<SlotSet> slotList) {
        this.slotList = slotList;
    }

    public void setActionList(ArrayList<String> actionList) {
        this.actionList = actionList;
    }

    public void setFormList(ArrayList<FormSet> formList) {
        this.formList = formList;
    }

    public void setSessionConfig(SessionConfig sessionConfig) {
        this.sessionConfig = sessionConfig;
    }

    public String getVersion() {
        return version;
    }

    public ArrayList<IntentSet> getIntentList() {
        return intentList;
    }

    public ArrayList<EntitySet> getEntityList() {
        return entityList;
    }

    public ArrayList<SlotSet> getSlotList() {
        return slotList;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }

    public ArrayList<FormSet> getFormList() {
        return formList;
    }

    public SessionConfig getSessionConfig() {
        return sessionConfig;
    }

    @Override
    public String toString() {
        return "DomainFile{" +
                "version='" + version + '\'' +
                ", intentList=" + intentList +
                ", entityList=" + entityList +
                ", slotList=" + slotList +
                ", actionList=" + actionList +
                ", formList=" + formList +
                ", sessionConfig=" + sessionConfig +
                '}';
    }
}
