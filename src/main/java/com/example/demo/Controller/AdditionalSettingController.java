package com.example.demo.Controller;

import com.example.demo.Service.ConfigureService;
import com.example.demo.Service.MergeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/update")
public class AdditionalSettingController {

    @Autowired
    private MergeService mergeService;
    @Autowired
    private ConfigureService configureService;

    @GetMapping(value = "/testNlu")
    public ResponseEntity<?> testInit(){
        mergeService.testInitLoad();
        return ResponseEntity.ok().build();
    }

    /**
     * display api welcome message, testing purpose, not necessary
     * @return welcome message, status 200
     */
    @GetMapping(value = "/welcome")
    public ResponseEntity<?> welcome(){
        System.out.println("[GET] /welcome method");
        return ResponseEntity.ok("welcome");
    }

    @GetMapping(value = "/reload")
    public ResponseEntity<?> reload(){
        System.out.println("[GET] /reload method");
        return ResponseEntity.ok("reload");
    }

    /**
     * merge current setting with new nlu setting
     * @param input new nlu setting from user input
     * @return 500 if something goes wrong, basically io error; 200 if success
     */
    @PostMapping(value = "/nlu")
    public ResponseEntity<?> addNluSetting(@RequestBody String input){
        try{
            System.out.println("[POST] /nlu: update nlu setting");
            mergeService.mergeNlu(input);
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed update nlu setting !");
        }
        return ResponseEntity.ok("ok, nlu setting adding success.");
    }
    @PostMapping(value = "/stories")
    public ResponseEntity<?> addStorySetting(@RequestBody String input){
        try{
            System.out.println("[POST] /stories: update story setting");
            mergeService.mergeStories(input);
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed update stories setting");
        }
        return ResponseEntity.ok("ok, stories setting adding success.");
    }
    @PostMapping(value = "/action")
    public ResponseEntity<?> addActionSetting(@RequestBody String input){
        try{
            System.out.println("[POST] /action: update action setting");
            mergeService.mergeAction(input);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed update action setting");
        }
        return ResponseEntity.ok("ok, action setting adding success.");
    }
    @PostMapping(value = "/domain")
    public ResponseEntity<?> addDomainSetting(@RequestBody String input){
        try{
            System.out.println("[POST] /domain: update domain setting");
            mergeService.mergeDomain(input);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed update domain setting");
        }
        return ResponseEntity.ok("ok, domain setting adding success");
    }

    @PostMapping(value = "/all")
    public ResponseEntity<?> addAll(@RequestBody String input){
        try{
            System.out.println("[POST] /all: update all rasa setting");
            mergeService.mergeAll(input);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something goes wrong");
        }
        return ResponseEntity.ok("ok, setting merged, check output file !");
    }

    @GetMapping(value = "/restart")
    public ResponseEntity<?> restartRasa(){
        return configureService.runSpecShell("rebootRasa.sh")? ResponseEntity.ok("success"): ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something goes wrong");
    }

    /**
     * advanced feature
     * allow user to upload setting file
     * @return execute status: 500 if error, 200 if success
     */
    public ResponseEntity<?> addSettingByFile(){
        // todo: post method, add rasa setting by upload file
        return null;
    }
}
