package com.example.demo.entity.Rasa.v2.story;

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
}
