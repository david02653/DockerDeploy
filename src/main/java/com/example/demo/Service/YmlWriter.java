package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.action.ActionFunc;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.nlu.NluObject;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import com.example.demo.entity.Rasa.v2.story.StoryObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YmlWriter {

    public void writeNlu(String path, NluFile content){
        try{
            FileWriter wr = new FileWriter(path);
            BufferedWriter buff = new BufferedWriter(wr);
            List<NluObject> list = content.getNlu();
            // write nlu file header
            buff.write("nlu:");
            buff.newLine();
            // loop all nluObject
            for(NluObject obj: list){
                // check object type and name
                buff.write("- " + obj.findType() + ": " + obj.findName());
                buff.newLine();
                // write setting details (loop array list or display string)
                buff.write("  examples: |");
                buff.newLine();
                for(String cont: obj.getExampleList()){
                    buff.write("    " + cont);
                    buff.newLine();
                }
            }
            buff.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeAction(String path, ActionFile file){
        try{
            FileWriter wr = new FileWriter(path);
            BufferedWriter buff = new BufferedWriter(wr);
            HashMap<String, ActionFunc> list = file.getActionList();
            ArrayList<String> config = file.getImportConfig();
            // write import config
            for(String token: config){
                buff.write(token);
                buff.newLine();
            }buff.newLine();
            // write action
            for(Map.Entry<String, ActionFunc> act: list.entrySet()){
                String name = act.getKey();
                ActionFunc func = act.getValue();
                for(String detail: func.getContent()){
                    buff.write(detail);
                    buff.newLine();
                }
            }
            buff.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeStory(String path, StoryFile file){
        try{
            FileWriter wr = new FileWriter(path);
            BufferedWriter buff = new BufferedWriter(wr);
            ArrayList<StoryObject> list = file.getStoryList();
            // write story file header
            buff.write("stories:");
            buff.newLine();
            // write story file content
            for(StoryObject obj: list){
                buff.write("  - story: " + obj.getName());
                buff.newLine();
                buff.write("    steps:");
                buff.newLine();
                for(String step: obj.getSteps()){
                    buff.write("      " + step);
                    buff.newLine();
                }
            }
            buff.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeDomain(){
    }
}
