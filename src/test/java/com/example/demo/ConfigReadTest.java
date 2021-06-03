package com.example.demo;

import com.example.demo.Service.MdReader;
import com.example.demo.entity.Rasa.legacy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ConfigReadTest {

    private MdReader reader;
    private final String prefixRoute = "./src/main/resources/data/legacy/";
    @BeforeEach
    void init(){
        reader = new MdReader();
    }

    @Test
    void testNluRead(){
        Nlu result = reader.readNlu(prefixRoute + "nlu.md");
        for(NluObject ele: result.getElements()){
            System.out.println("type: " + ele.getType());
            System.out.println("name: " + ele.getName());
            for(String cont: ele.getContent()){
//                System.out.print("- ");
                System.out.println(cont);
            }
            System.out.println();
        }
    }
    @Test
    void testStoryRead(){
        StoryFile stories = reader.readStories(prefixRoute + "stories.md");
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

    @Test
    void testDomainRead(){
        DomainObj domain = reader.readDomain(prefixRoute + "domain.yml");
        ArrayList<String> intent = domain.getIntent();
        ArrayList<String> action = domain.getActions();
        HashMap<String, String> tmp = domain.getTemplate();
        System.out.println("intent: ");
        for(String s: intent){
            System.out.println(s);
        }
        System.out.println("action: ");
        for(String s: action){
            System.out.println(s);
        }
        System.out.println("template: ");
        for(Map.Entry<String, String> entry: tmp.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.print("[" + key + "]> ");
            System.out.println(value);
        }
    }

    @Test
    void testFuncNameGet(){
        String temp = "a b(c):";
        System.out.println(reader.getFuncName(temp));
    }

    @Test
    void testPyRead(){
        HashMap<String, ArrayList<String>> map = reader.readAction(prefixRoute + "actions.py");
        System.out.println(map.size());
        for(Map.Entry<String, ArrayList<String>> entry: map.entrySet()){
            String name = entry.getKey();
            ArrayList<String> content = entry.getValue();
            System.out.println("> " + name + " :");
            for(String s: content){
                System.out.println(s);
            }
        }
    }
}
