package com.example.demo.entity.Rasa.v2.domain;

import java.util.ArrayList;

public class DomainSettingFile {

    private String version;
    private ArrayList<Setting> intentList;
    private ArrayList<Setting> entityList;
    private ArrayList<Setting> slotList;
    private ArrayList<String> actionList;
    private ArrayList<Setting> formList;
    private SessionConfig sessionConfig;

    public void setVersion(String version) {
        this.version = version;
    }

    public void setSessionConfig(SessionConfig sessionConfig) {
        this.sessionConfig = sessionConfig;
    }

    public void setIntentList(ArrayList<Setting> intentList) {
        this.intentList = intentList;
    }

    public void setSlotList(ArrayList<Setting> slotList) {
        this.slotList = slotList;
    }

    public void setEntityList(ArrayList<Setting> entityList) {
        this.entityList = entityList;
    }

    public void setActionList(ArrayList<String> actionList) {
        this.actionList = actionList;
    }

    public void setFormList(ArrayList<Setting> formList) {
        this.formList = formList;
    }

    public String getVersion() {
        return version;
    }

    public SessionConfig getSessionConfig() {
        return sessionConfig;
    }

    public ArrayList<Setting> getIntentList() {
        return intentList;
    }

    public ArrayList<Setting> getSlotList() {
        return slotList;
    }

    public ArrayList<Setting> getEntityList() {
        return entityList;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }

    public ArrayList<Setting> getFormList() {
        return formList;
    }

    @Override
    public String toString() {
        return "DomainSettingFile{" +
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
