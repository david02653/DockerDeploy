package com.example.demo.entity;

import java.util.ArrayList;

public class Nlu {

    public Nlu(){}

    private ArrayList<NluObject> elements;

    public void setElements(ArrayList<NluObject> elements) {
        this.elements = elements;
    }

    public ArrayList<NluObject> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "Nlu{" +
                "elements=" + elements +
                '}';
    }
}
