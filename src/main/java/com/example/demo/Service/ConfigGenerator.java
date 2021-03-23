package com.example.demo.Service;

import com.example.demo.entity.Rasa.DomainObj;
import com.example.demo.entity.Rasa.Nlu;
import com.example.demo.entity.Rasa.PyMethod;
import com.example.demo.entity.Rasa.Stories;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigGenerator {

    // merge and generate new nlu config
    public void mergeNlu(Nlu custom, Nlu current){
    }

    // merge and generate new stories config
    public void mergeStories(Stories custom, Stories current){
    }

    // merge and generate new domain config
    public void mergeDomain(DomainObj custom, DomainObj current){
    }

    // merge and generate new action config
    public void mergeAction(ArrayList<PyMethod> custom, HashMap<String, ArrayList<String>> current){
    }
}
