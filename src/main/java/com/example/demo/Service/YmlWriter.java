package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.action.ActionFunc;
import com.example.demo.entity.Rasa.v2.domain.Setting;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.nlu.NluObject;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import com.example.demo.entity.Rasa.v2.story.StoryObject;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YmlWriter {

    public void writeNlu(String outputPath, NluFile content) throws IOException {
        FileWriter wr = new FileWriter(outputPath);
        BufferedWriter buff = new BufferedWriter(wr);
        List<NluObject> list = content.getNlu();
        // write nlu file header
        buff.write("nlu:");
        buff.newLine();
        // loop all nluObject
        for(NluObject obj: list){
            // check object type and name
            buff.write("- " + obj.findType() + ": " + obj.findName());
            buff.newLine();
            // write setting details (loop array list or display string)
            buff.write("  examples: |");
            buff.newLine();
            for(String cont: obj.getExampleList()){
                buff.write("    " + cont);
                buff.newLine();
            }
        }
        buff.close();
    }

    public void writeNlu(String outputPath, ArrayList<String> content)throws IOException{
        FileWriter file = null;
        BufferedWriter writer = null;
        file = new FileWriter(outputPath);
        writer = new BufferedWriter(file);
        for(String s: content){
            writer.write(s);
            writer.newLine();
        }
        writer.close();
    }

    public void writeAction(String outputPath, ActionFile file) throws IOException {
        FileWriter wr = new FileWriter(outputPath);
        BufferedWriter buff = new BufferedWriter(wr);
        HashMap<String, ActionFunc> list = file.getActionList();
        ArrayList<String> config = file.getImportConfig();
        // write import config
        for(String token: config){
            buff.write(token);
            buff.newLine();
        }buff.newLine();
        // write action
        for(Map.Entry<String, ActionFunc> act: list.entrySet()){
            String name = act.getKey();
            ActionFunc func = act.getValue();
            for(String detail: func.getContent()){
                buff.write(detail);
                buff.newLine();
            }
        }
        buff.close();
    }

    public void writeStory(String outputPath, StoryFile file) throws IOException {
        FileWriter wr = new FileWriter(outputPath);
        BufferedWriter buff = new BufferedWriter(wr);
        ArrayList<StoryObject> list = file.getStoryList();
        // write story file header
        buff.write("stories:");
        buff.newLine();
        // write story file content
        for(StoryObject obj: list){
            buff.write("  - story: " + obj.getName());
            buff.newLine();
            buff.write("    steps:");
            buff.newLine();
            for(String step: obj.getSteps()){
                buff.write("      " + step);
                buff.newLine();
            }
        }
        buff.close();
    }

    /**
     * write domain setting to file
     * @param outputPath output file path
     * @param file current domain setting, Map< domain_data_type, Map< data_name, data_content>>
     */
    public void writeDomain(String outputPath, HashMap<String, HashMap<String, Setting>> file) throws IOException {
        FileWriter wr = new FileWriter(outputPath);
        BufferedWriter buff = new BufferedWriter(wr);
        HashMap<String, Setting> content;
        // write file header
        buff.write("version: '2.0'");
        buff.newLine();
        // write session_config
        buff.write("session_config:");
        buff.newLine();
        content = file.get("session_config");
        for(Map.Entry<String, Setting> entry: content.entrySet()){
            buff.write("  " + entry.getKey());
            buff.write(": " + entry.getValue().getDetails().get(0));
            buff.newLine();
        }
        // write intents
        buff.write("intents:");
        buff.newLine();
        content = file.get("intents");
        for(Map.Entry<String, Setting> entry: content.entrySet()){
            buff.write("- " + entry.getKey());
            // check if intent have detail setting
            printDomainDetail(buff, entry);
        }
        // write slots
        buff.write("slots:");
        buff.newLine();
        content = file.get("slots");
        for(Map.Entry<String, Setting> entry: content.entrySet()){
            buff.write("  " + entry.getKey());
            printDomainDetail(buff, entry);
        }
        // write entities
        buff.write("entities:");
        buff.newLine();
        content = file.get("entities");
        for(Map.Entry<String, Setting> entry: content.entrySet()){
            buff.write("- " + entry.getKey());
            buff.newLine();
        }
        // write responses
        buff.write("responses:");
        buff.newLine();
        content = file.get("responses");
        for(Map.Entry<String, Setting> entry: content.entrySet()){
            buff.write("  " + entry.getKey());
            printDomainDetail(buff, entry);
        }
        // write actions
        buff.write("actions:");
        buff.newLine();
        content = file.get("actions");
        for(Map.Entry<String, Setting> entry: content.entrySet()){
            buff.write("- " + entry.getKey());
            buff.newLine();
        }
        // write form
        buff.write("forms:");
        buff.newLine();
        content = file.get("forms");
        for(Map.Entry<String, Setting> entry: content.entrySet()){
            buff.write("  " + entry.getKey());
            printDomainDetail(buff, entry);
        }
        buff.close();
    }

    /**
     * write detail settings in domain file
     * @param buff current buffered writer
     * @param entry domain setting map entry
     * @throws IOException io error if anything goes wrong
     */
    private void printDomainDetail(BufferedWriter buff, Map.Entry<String, Setting> entry) throws IOException {
        if(entry.getValue().getDetails().size() > 0){
            buff.write(":");
            buff.newLine();
            for(String detail: entry.getValue().getDetails()){
                buff.write(detail);
                buff.newLine();
            }
        }else
            buff.newLine();
    }
}
