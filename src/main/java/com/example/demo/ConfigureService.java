package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.*;

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
}
