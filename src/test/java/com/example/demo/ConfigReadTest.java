package com.example.demo;

import com.example.demo.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ConfigReadTest {

    private MdReader reader;
    @BeforeEach
    void init(){
        reader = new MdReader();
    }

    @Test
    void testNluRead(){
        Nlu result = reader.readNlu("nlu.md");
        for(NluObject ele: result.getElements()){
            System.out.println("type: " + ele.getType());
            System.out.println("name: " + ele.getName());
            for(String cont: ele.getContent()){
                System.out.print("- ");
                System.out.println(cont);
            }
            System.out.println();
        }
    }
    @Test
    void testStoryRead(){
        Stories stories = reader.readStories("stories.md");
        HashMap<Integer, Story> map = stories.getStoryMap();
        for(Map.Entry<Integer, Story> entry: map.entrySet()){
            int key = entry.getKey();
            Story story = entry.getValue();
            System.out.println("nickName: " + story.getNickName());
            for(StoryIntent intent: story.getContent()){
                System.out.print("intent: ");
                System.out.println(intent.getIntentName());
                System.out.println(intent.getActionList());
            }
            System.out.println();
        }
        System.out.println(map.size());
    }
}
