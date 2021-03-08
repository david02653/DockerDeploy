package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigureController {

    @GetMapping(value = "/ok")
    public ResponseEntity<String> ok(){
        return ResponseEntity.ok("ok there");
    }

    public ResponseEntity<Object> build(){
        // TODO: try to build something
        return null;
    }

    public ResponseEntity<Object> getCurrentConfig(){
        // TODO: get current config
        return null;
    }

    public ResponseEntity<Object> postNewConfig(){
        // TODO: post new config
        return null;
    }
}
