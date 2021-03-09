package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ConfigureServiceTest {

    private ConfigureService service;
    @BeforeEach
    void init(){
        service = new ConfigureService();
    }

    @Test
    void readFileTest() throws IOException {
        service.readFile();
    }

    @Test
    void writeFileTest() throws IOException {
        service.appendFile("line");
    }
}
