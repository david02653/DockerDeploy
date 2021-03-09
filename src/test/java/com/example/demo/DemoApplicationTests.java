package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {

    private String msg;
    private ExecuteShell execute;
    @BeforeEach
    void init(){
        msg = "> THIS IS TESTING MESSAGE";
        execute = new ExecuteShell();
    }

    @Test
    void testRunEcho() throws Exception {
        execute.runEcho(msg);
    }

    @Test
    void testScript() throws Exception {
        execute.runScript("testShell.sh");
    }

}
