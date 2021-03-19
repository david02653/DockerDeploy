package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Story {

    public Story(){}

    private String nickName;
    private ArrayList<StoryIntent> content;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setContent(ArrayList<StoryIntent> content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public ArrayList<StoryIntent> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Story{" +
                "nickName='" + nickName + '\'' +
                ", content=" + content +
                '}';
    }
}
