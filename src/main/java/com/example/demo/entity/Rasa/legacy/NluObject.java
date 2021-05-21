package com.example.demo.entity.Rasa.legacy;

import java.util.ArrayList;

public class NluObject {

    public NluObject(){}

    private String name;
    private String type;
    private ArrayList<String> content;

    public boolean equal(NluObject obj){
        return this.name.equals(obj.getName()) && this.type.equals(obj.getType());
    }

    public String getTitle(){
        return this.type + ":" + this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "NluObject{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", content=" + content +
                '}';
    }
}
