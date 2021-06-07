package com.example.demo.entity.Rasa.v2.nlu;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
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
        if(nlu == null)
            return;
        for(NluObject n: nlu){
            n.list();
        }
    }

    /**
     * only check intent, should check others
     * @param name target name
     * @return if exist
     */
    public boolean containsName(String name){
        return nlu.stream().anyMatch(obj -> obj.findName().equals(name));
    }
}
