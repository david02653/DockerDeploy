package com.example.demo;

import com.example.demo.entity.Nlu;
import com.example.demo.entity.NluObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

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
}
