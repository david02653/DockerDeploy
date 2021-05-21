package com.example.demo.Service;

import com.example.demo.entity.Rasa.legacy.AllConfig;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * only work on old version: 0.x
 */
// TODO: update reader to current version (2.x)
@Service
public class ConfigureService {
    // execute shell
    // get file from git
    // generate new config file
    // rebuild and redeploy new service docker

    public void readFile() throws IOException {
        File target = new File("./src/main/resources/data/target.txt");
        FileReader reader = new FileReader(target);
        BufferedReader buffer = new BufferedReader(reader);
        String line;
        while((line = buffer.readLine())!= null){
            System.out.println(line);
        }
        reader.close();
        buffer.close();
    }

    public String showFile() {
        try{
            StringBuilder builder = new StringBuilder();
            File target = new File("./src/main/resources/data/target.txt");
            FileReader reader = new FileReader(target);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }
            reader.close();
            buffer.close();
            return builder.toString();
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }

    }

    public boolean clearFile() {
        try{
            File file = new File("./src/main/resources/data/target.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean appendFile(String msg) {
        try{
            File file = new File("./src/main/resources/data/target.txt");
            FileWriter writer = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(writer);
            printer.println(msg);
            printer.close();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean runShell() {
        try{
            ExecuteShell runner = new ExecuteShell();
//            runner.runScript("simpleBuild.sh");
            runner.runScript("buildWithVolume.sh");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean runSpecShell(String filename){
        try{
            ExecuteShell runner = new ExecuteShell();
            runner.runScript(filename);
        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // temporary method: split config
    public void splitConfig(String config){
        // split one config to nlu, domain, stories and action
        String[] token = config.split("-----\n");
        AllConfig settings = new AllConfig();
        // split nlu settings
        ArrayList<String> list = new ArrayList<>(Arrays.asList(token[0].split("\n")));
        settings.setNlu(list);
        // split domain settings
        list = new ArrayList<>(Arrays.asList(token[1].split("\n")));
        settings.setDomain(list);
        // split action settings
        list = new ArrayList<>(Arrays.asList(token[2].split("\n")));
        settings.setAction(list);
        // split stories settings
        list = new ArrayList<>(Arrays.asList(token[3].split("\n")));
        settings.setStories(list);
        ConfigGenerator generator = new ConfigGenerator();
        generator.generateSetting(settings, "test");
        // print config
//        printSplitResult(settings);
    }
    // temporary method for split testing
    public void printSplitResult(AllConfig config){
        System.out.println(">> NLU");
        for(String s: config.getNlu()){
            System.out.println(s);
        }
        System.out.println(">> DOMAIN");
        for(String s: config.getDomain()){
            System.out.println(s);
        }
        System.out.println(">> STORIES");
        for(String s: config.getStories()){
            System.out.println(s);
        }
        System.out.println(">> ACTIONS");
        for(String s: config.getAction()){
            System.out.println(s);
        }
    }
}
