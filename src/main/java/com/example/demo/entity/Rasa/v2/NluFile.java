package com.example.demo.entity.Rasa.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NluFile {
    @JsonProperty("nlu")
    private List<NluObject> intentList;

    public void setIntentList(List<NluObject> intentList) {
        this.intentList = intentList;
    }

    public List<NluObject> getIntentList() {
        return intentList;
    }

    @Override
    public String toString() {
        return "NluFile{" +
                "intentList=" + intentList +
                '}';
    }
}
