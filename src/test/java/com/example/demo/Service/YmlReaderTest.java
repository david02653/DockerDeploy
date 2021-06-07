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
    private final String nlu_empty = "v2/emptyData/nluTestEmpty.yml";
    private final String STORY_TEST = "v2/storyTest.yml";
    private final String story_empty = "v2/emptyData/storyTestEmpty.yml";
    private final String RESP_TEST = "v2/respTest.yml";
    private final String DOM_TEST = "v2/domain.yml";
    private final String dom_empty = "v2/emptyData/domainTestEmpty.yml";
    private final String ACT_TEST = "v2/actionsTest.py";
    private final String act_empty = "v2/emptyData/actionTestEmpty.py";
    private final String DOM_TEST_SET = "v2/mergeData/domain/domainTestSet1.yml";
    private YmlReader reader;
    @BeforeEach
    void setUp() {
        reader = new YmlReader();
    }

    private String addPrefix(String path){
        return contentRootPrefix + path;
    }

    @Test
    void loadNluViaSnakeYml(){
//        NluFile result = reader.loadNlu(addPrefix(NLU_TEST));
        NluFile result = reader.loadNlu(addPrefix(nlu_empty));
        System.out.println(result);
    }

    @Test
    void loadStory(){
        StoryFile result = reader.loadStory(addPrefix(story_empty));
        System.out.println(result);
    }

    @Test
    void loadResponse(){
        ResponseFile result = reader.loadResponse(addPrefix(RESP_TEST));
        System.out.println(result);
    }

    @Test
    void testLoadDomainAsString(){
        HashMap<String, ArrayList<String>> map = reader.loadDomainAsString(addPrefix(dom_empty));
        map.forEach((k, v) -> {
            System.out.println(k + " >");
            System.out.println(v);
        });
    }

    @Test
    void testParseDomainMap(){
        HashMap<String, ArrayList<String>> map = reader.loadDomainAsString(addPrefix(dom_empty));
        HashMap<String, HashMap<String, Setting>> resultMap = reader.parseDomainMap(map);
        resultMap.forEach((k, v) -> {
            System.out.println(k + " >");
            System.out.println(v);
        });
    }

    @Test
    void testAction(){
        ActionFile file = reader.loadAction(addPrefix(act_empty));
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