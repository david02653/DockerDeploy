package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/config")
public class ConfigureController {

    @Autowired
    private ConfigureService service;

    @GetMapping(value = "/ok")
    public ResponseEntity<String> ok(){
        return ResponseEntity.ok("ok there");
    }

    @GetMapping(value = "/show")
    public ResponseEntity<String> show() {
        return ResponseEntity.ok(service.showFile());
    }

    @GetMapping(value = "/clear")
    public ResponseEntity<String> clear() {
        if(service.clearFile()) return ResponseEntity.ok("file cleared");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file clear failed !");
    }

    @GetMapping(value = "/append/{msg}")
    public ResponseEntity<String> append(@PathVariable("msg") String msg) {
        if(service.appendFile(msg)) return ResponseEntity.ok("append " + msg + " done");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("append failed");
    }

    @GetMapping(value = "/run")
    public ResponseEntity<String> build() {
        if(service.runShell()) return ResponseEntity.ok("> check !");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("> script failed...");
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
