package com.example.demo.entity.Rasa.v2.nlu;

import java.util.ArrayList;

public class NluObject {
    // possible data type
    private String intent;
    private String synonym;
    private String regex;
    private String lookup;
    // examples in one string
    private String examples;
    // arrayList of examples
    private ArrayList<String> exampleList;

    public void setIntent(String intent) {
        this.intent = intent;
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

    public String getIntent() {
        return intent;
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
                "intent='" + intent + '\'' +
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

    public String findName(){
        if(intent != null) return intent;
        if(synonym != null) return synonym;
        if(regex != null) return regex;
        if(lookup != null) return lookup;
        return "";
    }

    public String findType(){
        if(intent != null) return "intent";
        if(synonym != null) return "synonym";
        if(regex != null) return "regex";
        return "lookup";
    }
}
