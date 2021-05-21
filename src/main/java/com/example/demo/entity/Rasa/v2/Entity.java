package com.example.demo.entity.Rasa.v2;

import java.util.List;

public class Entity {
    private String name;
    private List<String> examples;

    public void setName(String name) {
        this.name = name;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public String getName() {
        return name;
    }

    public List<String> getExamples() {
        return examples;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", example=" + examples +
                '}';
    }
}
