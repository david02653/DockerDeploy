package com.example.demo.Service;

import com.example.demo.entity.Rasa.legacy.Nlu;
import com.example.demo.entity.Rasa.v2.NluFile;
import com.example.demo.entity.Rasa.v2.NluObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

/**
 * read rasa 2.x version setting data
 */
public class YmlReader {

    private static final String SOURCE_PREFIX = "./src/main/resources/data/";

    public boolean loadNlu(){
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper();
        List<NluFile> contentList;
        try{
            YAMLParser parser = yamlFactory.createParser(new File(SOURCE_PREFIX + "v2/nlu.yml"));
            contentList = mapper.readValues(parser, NluFile.class).readAll();
            System.out.println(contentList);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Map<Boolean, NluFile> snakeTest(String path){
        // snake yml test
        Yaml yaml = new Yaml();
//        Map<Boolean, NluFile> result = new HashMap<>();
//        try{
//            InputStream inputStream = this.getClass()
//                    .getClassLoader()
//                    .getResourceAsStream(path);
//            NluFile obj = yaml.load(inputStream);
//            System.out.println(obj);
//            result.put(true, obj);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.put(false, null);
//        }
//        return result;
        // gson parse test
        File file = new File(path);
        try{
            Object loadedYaml = yaml.load(new FileReader(SOURCE_PREFIX + path));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(loadedYaml, LinkedHashMap.class);
            System.out.println(jsonString);
            NluFile parsedFile = gson.fromJson(jsonString, NluFile.class);
            System.out.println(parsedFile);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void jacksonSolution(String path){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        NluFile file = null;
        try {
            file = mapper.readValue(SOURCE_PREFIX + path, NluFile.class);
            ObjectMapper jsonMapper = new ObjectMapper();
            System.out.println(jsonMapper.writeValueAsString(file));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
