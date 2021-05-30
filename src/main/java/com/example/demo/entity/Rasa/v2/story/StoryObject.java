package com.example.demo.entity.Rasa.v2.story;

import java.util.ArrayList;

public class StoryObject {
    /**
     * story name
     */
    private String name;
    private ArrayList<String> steps;

    public StoryObject(){}
    public StoryObject(String storyName){
        setName(storyName);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return "StoryObject{" +
                "name='" + name + '\'' +
                ", steps=" + steps +
                '}';
    }
}
