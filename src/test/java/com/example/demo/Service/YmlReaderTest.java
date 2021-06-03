package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.action.ActionFunc;
import com.example.demo.entity.Rasa.v2.domain.Setting;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.response.ResponseFile;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class YmlReaderTest {

    private final String contentRootPrefix = "./src/main/resources/data/";
    private final String NLU_TEST = "v2/nluTest.yml";
    private final String STORY_TEST = "v2/storyTest.yml";
    private final String RESP_TEST = "v2/respTest.yml";
    private final String DOM_TEST = "v2/domainTest.yml";
    private final String ACT_TEST = "v2/actionsTest.py";
    private final String DOM_TEST_SET = "v2/mergeData/domain/domainTestSet1.yml";
    private YmlReader reader;
    @BeforeEach
    void setUp() {
        reader = new YmlReader();
    }

    @Test
    void loadNluViaSnakeYml(){
        NluFile result = reader.loadNlu(NLU_TEST);
        System.out.println(result);
    }

    @Test
    void loadStory(){
        StoryFile result = reader.loadStory(STORY_TEST);
        System.out.println(result);
    }

    @Test
    void loadResponse(){
        ResponseFile result = reader.loadResponse(RESP_TEST);
        System.out.println(result);
    }

    @Test
    void testLoadDomainAsString(){
        HashMap<String, ArrayList<String>> map = reader.loadDomainAsString(DOM_TEST);
        map.forEach((k, v) -> {
            System.out.println(k + " >");
            System.out.println(v);
        });
    }

    @Test
    void testParseDomainMap(){
        HashMap<String, ArrayList<String>> map = reader.loadDomainAsString(DOM_TEST);
        HashMap<String, HashMap<String, Setting>> resultMap = reader.parseDomainMap(map);
        resultMap.forEach((k, v) -> {
            System.out.println(k + " >");
            System.out.println(v);
        });
    }

    @Test
    void testAction(){
        ActionFile file = reader.loadAction(ACT_TEST);
        ArrayList<String> config = file.getImportConfig();
        HashMap<String, ActionFunc> actionMap = file.getActionList();
        System.out.println("config >");
        System.out.println(config);
        actionMap.forEach((k, v) -> {
            System.out.println(k);
            for(String t: v.getContent()){
                System.out.println(t);
            }
            System.out.println("---");
        });
    }
}