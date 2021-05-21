package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.NluFile;
import com.example.demo.entity.Rasa.v2.NluObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class YmlReaderTest {

    private final String contentRootPrefix = "./src/main/resources/data/";
    private final String NLU_TEST_PATH = "v2/nluTest.yml";
    private YmlReader reader;
    @BeforeEach
    void setUp() {
        reader = new YmlReader();
    }

    @Test
    void loadNlu() {
        // pending until Nlu bean complete
        reader.loadNlu();
    }

    @Test
    void snakeTest(){
//        Map<Boolean, NluFile> map = reader.snakeTest("data/v2/nlu.yml");
        reader.snakeTest(NLU_TEST_PATH);
//        map.forEach((k, v) -> {
//            if(k){
//                Map<String, NluFile> content = v;
////                System.out.println(content);
//                content.forEach((ck, cv)-> {
//                    System.out.println(ck);
////                    System.out.println(cv);
//                });
//            }
//        });
    }
}