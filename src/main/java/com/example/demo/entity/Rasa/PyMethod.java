package com.example.demo.entity.Rasa;

import java.util.ArrayList;

// unused
public class PyMethod {
    public PyMethod(){}

    private String className;
    private ArrayList<String> process;

    public void setClassName(String className) {
        this.className = className;
    }

    public void setProcess(ArrayList<String> process) {
        this.process = process;
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<String> getProcess() {
        return process;
    }

    @Override
    public String toString() {
        return "PyMethod{" +
                "className='" + className + '\'' +
                ", process=" + process +
                '}';
    }
}
