package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.domain.Setting;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class YmlWriterTest {

    private final String DIR = "src/main/resources/result/v2/";
    private final String SET_DIR = "src/main/resources/data/";
    private final String NLU_OUT = "nluOut.yml";
    private final String NLU_SET = "v2/nluTest.yml";
    private final String STR_OUT = "storyOut.yml";
    private final String STR_SET = "v2/storyTest.yml";
    private final String ACT_OUT = "actionOut.py";
    private final String ACT_SET = "v2/actionsTest.py";
    private final String DOM_OUT = "domainOut.yml";
    private final String DOM_SET = "v2/domainTest.yml";

    private YmlWriter writer;
    private YmlReader reader;

    private String dir(String path){
        return DIR + path;
    }
    private String setDir(String path){
        return SET_DIR + path;
    }

    @BeforeEach
    void setUp() {
        writer = new YmlWriter();
        reader = new YmlReader();
    }

    @Test
    void testWriteNlu(){
        NluFile nlu = reader.loadNlu(setDir(NLU_SET));
        try {
            writer.writeNlu(dir(NLU_OUT), nlu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteStory(){
        StoryFile file = reader.loadStory(setDir(STR_SET));
        try {
            writer.writeStory(dir(STR_OUT), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteAction(){
        ActionFile file = reader.loadAction(setDir(ACT_SET));
        try {
            writer.writeAction(dir(ACT_OUT), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteDomain(){
        HashMap<String, ArrayList<String>> rawFile = reader.loadDomainAsString(setDir(DOM_SET));
        HashMap<String, HashMap<String, Setting>> parsedFile = reader.parseDomainMap(rawFile);
        try {
            writer.writeDomain(dir(DOM_OUT), parsedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}