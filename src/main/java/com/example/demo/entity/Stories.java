package com.example.demo.entity;

import java.util.ArrayList;

public class Stories {

    public Stories(){}

    private String nickName;
    private ArrayList<Story> stories;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }

    public String getNickName() {
        return nickName;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    @Override
    public String toString() {
        return "Stories{" +
                "nickName='" + nickName + '\'' +
                ", stories=" + stories +
                '}';
    }
}
