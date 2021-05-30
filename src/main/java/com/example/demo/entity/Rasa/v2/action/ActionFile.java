package com.example.demo.entity.Rasa.v2.action;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionFile {
    private ArrayList<String> importConfig;
    private HashMap<String, ActionFunc> actionList;

    public void setImportConfig(ArrayList<String> importConfig) {
        this.importConfig = importConfig;
    }

    public void setActionList(HashMap<String, ActionFunc> actionList) {
        this.actionList = actionList;
    }

    public ArrayList<String> getImportConfig() {
        return importConfig;
    }

    public HashMap<String, ActionFunc> getActionList() {
        return actionList;
    }

    @Override
    public String toString() {
        return "ActionFile{" +
                "importConfig=" + importConfig +
                ", actionList=" + actionList +
                '}';
    }
}
