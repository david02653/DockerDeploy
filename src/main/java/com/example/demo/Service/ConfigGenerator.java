package com.example.demo.Service;

import com.example.demo.entity.Rasa.*;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfigGenerator {

    private final String PREFIX = "./src/main/resources/result/";

    // merge and generate new nlu config
    public void mergeNlu(Nlu custom, Nlu current){
//        Nlu result = new Nlu();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(NluObject obj: current.getElements()){
            // add current nlu config in result list
            map.put(obj.getTitle(), obj.getContent());
        }
        for(NluObject obj: custom.getElements()){
            map.merge(obj.getTitle(), obj.getContent(), (old, nValue)->{
                for(String s: nValue)
                    if(!old.contains(s))
                        old.add(s);
                return old;
            });
        }
        // export new nlu config
        try{
            FileWriter file = new FileWriter(PREFIX + "nlu.md");
            BufferedWriter writer = new BufferedWriter(file);
            for(Map.Entry<String, ArrayList<String>> entry: map.entrySet()){
                String title = entry.getKey();
                ArrayList<String> content = entry.getValue();
                writer.write("## " + title);
                writer.newLine();
                for(String ele: content){
                    writer.write("- " + ele);
                    writer.newLine();
                }
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // merge and generate new stories config
    public void mergeStories(Stories custom, Stories current){
        HashMap<String, Story> map = new HashMap<>();
        for(Map.Entry<Integer, Story> entry: current.getStoryMap().entrySet()){
            map.put(entry.getValue().getNickName(), entry.getValue());
        }
        for(Map.Entry<Integer, Story> entry: custom.getStoryMap().entrySet()){
            map.merge(entry.getValue().getNickName(), entry.getValue(), (oldValue, nValue)-> {
                // fix this !
                return nValue;
            });
        }
        // export new stories config
        try{
            FileWriter file = new FileWriter(PREFIX + "stories.md");
            BufferedWriter writer = new BufferedWriter(file);
            for(Map.Entry<String, Story> entry: map.entrySet()){
                String nickName = entry.getKey();
                Story content = entry.getValue();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // merge and generate new domain config
    public void mergeDomain(DomainObj custom, DomainObj current){
    }

    // merge and generate new action config
    public void mergeAction(ArrayList<PyMethod> custom, HashMap<String, ArrayList<String>> current){
    }

    // temporary method for testing
    public void splitConfig(String content){
        Gson gson = new Gson();
        AllConfig config;
        config = gson.fromJson(content, new AllConfig(){}.getClass());
    }
}
