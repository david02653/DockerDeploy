package com.example.demo.entity.Rasa.v2.nlu;

import java.util.ArrayList;

public class NluObject {
    private String intentName;
    private String synonym;
    private String regex;
    private String lookup;
    private String examples;
    private ArrayList<String> exampleList;

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public void setExampleList(ArrayList<String> exampleList) {
        this.exampleList = exampleList;
    }

    public String getIntentName() {
        return intentName;
    }

    public String getSynonym() {
        return synonym;
    }

    public String getRegex() {
        return regex;
    }

    public String getLookup() {
        return lookup;
    }

    public String getExamples() {
        return examples;
    }

    public ArrayList<String> getExampleList() {
        return exampleList;
    }

    @Override
    public String toString() {
        return "NluEntity{" +
                "intent='" + intentName + '\'' +
                ", synonym='" + synonym + '\'' +
                ", regex='" + regex + '\'' +
                ", lookup='" + lookup + '\'' +
//                ", examples='" + examples + '\'' +
                ", exampleList=" + exampleList +
                '}';
    }

    public void list(){
        ArrayList<String> tmp = new ArrayList<>();
        String[] token = this.examples.split("\n");
        for(String t: token){
            if(t.strip().length() > 0){
                tmp.add(t.strip());
            }
        }
        setExampleList(tmp);
    }
}
