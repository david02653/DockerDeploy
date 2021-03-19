package com.example.demo.entity;

import java.util.HashMap;

public class Stories {

    public Stories(){}

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
