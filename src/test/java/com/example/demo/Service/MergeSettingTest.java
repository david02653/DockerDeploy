package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.domain.Setting;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import com.example.demo.entity.Rasa.v2.story.StoryObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class MergeSettingTest {

    private final String SOURCE_DIR = "src/main/resources/data/v2/mergeData/";
    private final String NLU_SET1 = "nlu/nluTestSet1.yml";
    private final String NLU_SET2 = "nlu/nluTestSet2.yml";
    private final String ACT_SET1 = "action/actionsTestSet1.py";
    private final String ACT_SET2 = "action/actionsTestSet2.py";
    private final String STR_SET1 = "story/storyTestSet1.yml";
    private final String STR_SET2 = "story/storyTestSet2.yml";
    private final String DOM_SET1 = "domain/domainTestSet1.yml";
    private final String DOM_SET2 = "domain/domainTestSet2.yml";
    private MergeSetting test;
    private YmlReader reader;

    private String dir(String name){
        return SOURCE_DIR + name;
    }

    @BeforeEach
    void setUp() {
        test = new MergeSetting();
        reader = new YmlReader();
    }

    @Test
    void testMergeNlu() {
        NluFile set1 = reader.loadNlu(dir(NLU_SET1));
        NluFile set2 = reader.loadNlu(dir(NLU_SET2));
        System.out.println("set1 > " + set1.getNlu().size());
        set1.getNlu().forEach(obj -> {
            System.out.println(obj.findName());
        });
        System.out.println("set2 > " + set2.getNlu().size());
        set2.getNlu().forEach(obj -> {
            System.out.println(obj.findName());
        });
        NluFile result = test.mergeNlu(set1, set2);
        System.out.println("result > " + result.getNlu().size());
        result.getNlu().forEach(obj -> {
            System.out.println(obj.findName());
        });
    }

    @Test
    void testMergeAction(){
        ActionFile set1 = reader.loadAction(dir(ACT_SET1));
        ActionFile set2 = reader.loadAction(dir(ACT_SET2));
        System.out.println("set1 > " + set1.getActionList().size());
        set1.getActionList().forEach((name, func) -> {
            System.out.println(name);
        });
        System.out.println("set2 > " + set2.getActionList().size());
        set2.getActionList().forEach((name, func) -> {
            System.out.println(name);
        });
        ActionFile result = test.mergeAction(set1, set2);
        System.out.println("res > " + result.getActionList().size());
        result.getActionList().forEach((name, func) -> {
            System.out.println(name);
        });
        System.out.println("config > " + result.getImportConfig().size());
        for(String config: result.getImportConfig()){
            System.out.println(config);
        }
    }

    @Test
    void testMergeStory(){
        StoryFile set1 = reader.loadStory(dir(STR_SET1));
        StoryFile set2 = reader.loadStory(dir(STR_SET2));
        System.out.println("set1 > " + set1.getStoryList().size());
        for(StoryObject obj: set1.getStoryList()){
            System.out.println(obj.getName());
        }
        System.out.println("set2 > " + set2.getStoryList().size());
        for(StoryObject obj: set2.getStoryList()){
            System.out.println(obj.getName());
        }
        StoryFile result = test.mergeStory(set1, set2);
        System.out.println("res > " + result.getStoryList().size());
        for(StoryObject obj: result.getStoryList()){
            System.out.println(obj.getName());
        }
    }

    @Test
    void testMergeDomain(){
        HashMap<String, ArrayList<String>> rawSet1 = reader.loadDomainAsString(dir(DOM_SET1));
        HashMap<String, HashMap<String, Setting>> set1 = reader.parseDomainMap(rawSet1);
        HashMap<String, ArrayList<String>> rawSet2 = reader.loadDomainAsString(dir(DOM_SET2));
        HashMap<String, HashMap<String, Setting>> set2 = reader.parseDomainMap(rawSet2);
        System.out.println(set1);
        set1.forEach((k, v) -> {
            System.out.println(k + " > " + v.size());
        });
        System.out.println(set2);
        set2.forEach((k, v) -> {
            System.out.println(k + " > " + v.size());
        });
        HashMap<String, HashMap<String, Setting>> result = test.mergeDomain(set1, set2);
        System.out.println(result);
        result.forEach((k, v) -> {
            System.out.println(k + " > " + v.size());
        });
    }
}