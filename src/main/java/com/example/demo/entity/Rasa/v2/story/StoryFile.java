package com.example.demo.entity.Rasa.v2.story;

import com.example.demo.entity.Rasa.v2.action.ActionFile;

import java.util.ArrayList;

public class StoryFile {

    private ArrayList<StoryObject> storyList;

    public void setStoryList(ArrayList<StoryObject> storyList) {
        this.storyList = storyList;
    }

    public ArrayList<StoryObject> getStoryList() {
        return storyList;
    }

    @Override
    public String toString() {
        return "StoryFile{" +
                "storyList=" + storyList +
                '}';
    }

    public StoryFile copy(){
        StoryFile file = new StoryFile();
        file.setStoryList(getStoryList());
        return file;
    }
}
