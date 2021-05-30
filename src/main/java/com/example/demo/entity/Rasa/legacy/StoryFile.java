package com.example.demo.entity.Rasa.legacy;

import java.util.HashMap;

public class StoryFile {

    public StoryFile(){}

    private HashMap<Integer, Story> storyMap;

    public void setStoryMap(HashMap<Integer, Story> storyMap) {
        this.storyMap = storyMap;
    }

    public HashMap<Integer, Story> getStoryMap() {
        return storyMap;
    }

    @Override
    public String toString() {
        return "Stories{" +
                "story=" + storyMap +
                '}';
    }
}
