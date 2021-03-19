package com.example.demo;

import com.example.demo.entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MdReader {

    public Nlu readNlu(String name){

        Nlu nlu = new Nlu();
        ArrayList<NluObject> list = new ArrayList<>();
        try{
            FileReader reader = new FileReader(name);
            BufferedReader buff = new BufferedReader(reader);
            String line;
            String[] token;
            NluObject nObj = null;
            ArrayList<String> element = null;
            while((line = buff.readLine())!= null){
                if(line.startsWith("##")){
                    // nlu object header found
                    nObj = new NluObject();
                    element = new ArrayList<>();
                    line = line.substring(3);
                    token = line.split(":");
                    nObj.setType(token[0]);
                    nObj.setName(token[1]);
                }else if(line.startsWith("-")){
                    if(element != null) element.add(line.substring(2));
                }else{
                    if(nObj != null) nObj.setContent(element);
                    list.add(nObj);
                }
            }
            nlu.setElements(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return nlu;
    }

    public Stories readStories(String name){

        Stories stories = new Stories();
        HashMap<Integer, Story> storyList = new HashMap<>();
        Story story = new Story();
        ArrayList<String> actionList = null;
        StoryIntent storyIntent = null;
        boolean editFlag = false;
        try{
            FileReader file = new FileReader(name);
            BufferedReader buff = new BufferedReader(file);
            String line;
            while((line = buff.readLine()) != null){
                if(line.startsWith("##")){
                    // new story found
                    line = line.substring(3);
                    story = new Story();
                    story.setContent(new ArrayList<>());
                    story.setNickName(line);
                }else if(line.startsWith("*")){
                    // if editFlag = true, still in previous story
                    // store story intent and create a new one
                    if(editFlag){
                        editFlag = false;
                        if(actionList != null) storyIntent.setActionList(actionList);
                        ArrayList<StoryIntent> temp = story.getContent();
                        temp.add(storyIntent);
                        story.setContent(temp);
                    }
                    line = line.substring(2);
                    storyIntent = new StoryIntent();
                    actionList = new ArrayList<>();
                    storyIntent.setIntentName(line);
                }else if(line.startsWith("  -")){
                    line = line.substring(4);
                    if(actionList != null) actionList.add(line);
                    editFlag = true;
                }else{
                    // story end detected, restore current story in list
                    if(actionList != null){
                        // add current action list in story intent
                        // add current story intent in story
                        storyIntent.setActionList(actionList);
                        ArrayList<StoryIntent> temp = story.getContent();
                        temp.add(storyIntent);
                        story.setContent(temp);
                        actionList = null;
                        storyIntent = null;
                        editFlag = false;
                    }
                    storyList.put(storyList.size(), story);
                }
            }
            stories.setStoryMap(storyList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return stories;
    }
}
