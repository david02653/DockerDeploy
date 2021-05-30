package com.example.demo.entity.Rasa.v2.nlu;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NluFile {
    @JsonProperty("nlu")
    private List<NluObject> nlu;

    public void setNlu(List<NluObject> nlu) {
        this.nlu = nlu;
    }

    public List<NluObject> getNlu() {
        return nlu;
    }

    @Override
    public String toString() {
        return "NluFile{" +
                "EntityList=" + nlu +
                '}';
    }

    public void checkExample(){
        for(NluObject n: nlu){
            n.list();
        }
    }

    public boolean containsName(String name){
        return nlu.stream().anyMatch(obj -> obj.getIntentName().equals(name));
    }
}
