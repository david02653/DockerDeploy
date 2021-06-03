package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;

import static org.junit.jupiter.api.Assertions.*;

class YmlWriterTest {

    private final String DIR = "src/main/resources/result/v2/";
    private final String NLU_OUT = "nluOut.yml";
    private final String NLU_SET = "v2/nluTest.yml";
    private final String STR_OUT = "storyOut.yml";
    private final String STR_SET = "v2/storyTest.yml";
    private final String ACT_OUT = "actionOut.py";
    private final String ACT_SET = "v2/actionsTest.py";

    private YmlWriter writer;
    private YmlReader reader;

    private String dir(String path){
        return DIR + path;
    }

    @BeforeEach
    void setUp() {
        writer = new YmlWriter();
        reader = new YmlReader();
    }

    @Test
    void testWriteNlu(){
        NluFile nlu = reader.loadNlu(NLU_SET);
        writer.writeNlu(dir(NLU_OUT), nlu);
    }

    @Test
    void testWriteStory(){
        StoryFile file = reader.loadStory(STR_SET);
        writer.writeStory(dir(STR_OUT), file);
    }

    @Test
    void testWriteAction(){
        ActionFile file = reader.loadAction(ACT_SET);
        writer.writeAction(dir(ACT_OUT), file);
    }

    @Test
    void testWriteDomain(){
    }
}