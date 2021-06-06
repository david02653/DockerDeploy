package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.action.ActionFunc;
import com.example.demo.entity.Rasa.v2.domain.*;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.response.ResponseFile;
import com.example.demo.entity.Rasa.v2.response.ResponseObject;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import com.example.demo.entity.Rasa.v2.story.StoryObject;
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
    private final Yaml yaml = new Yaml();

    // status when loading domain file
    private enum DomainEditFlag {SESSION_CONFIG, INTENTS, ENTITIES, SLOTS, RESPONSES, ACTIONS, FORMS, NONE}

    /**
     * suspend
     * @return nlu file object
     */
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

    /**
     * read nlu.yml, use snakeYml and gson library, load with javaBean
     * because of NluObject data type, need to check all intent type to make sure if 'intent_name' exist
     * @param path file path
     * @return result nlu file object
     */
    public NluFile loadNlu(String path){
        // gson parse test
        NluFile parsedFile = null;
        File file = new File(path);
        try{
            System.out.println("[YmlReader.loadNlu] load path: [" + path + "]");
//            Object loadedYaml = yaml.load(new FileReader(SOURCE_PREFIX + path)); // testing file path
            Object loadedYaml = yaml.load(new FileReader(path));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(loadedYaml, LinkedHashMap.class);
//            System.out.println(jsonString);
            parsedFile = gson.fromJson(jsonString, NluFile.class);
            parsedFile.checkExample();
//            System.out.println(parsedFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        return parsedFile;
    }
    public NluFile loadNlu(ArrayList<String> input) throws IOException{
        YmlWriter writer = new YmlWriter();
        writer.writeNlu(SOURCE_PREFIX + "tmp/nlu.yml", input);
        return loadNlu(SOURCE_PREFIX + "tmp/nlu.yml");
    }

    /**
     * load stories.yml, read line by line because i don't know how many steps will be in each stories
     * @param path file path
     */
    public StoryFile loadStory(String path){
        StoryFile storyFile = new StoryFile();
        try{
            System.out.println("[YmlReader.loadStory] load path: [" + path + "]");
//            FileReader reader = new FileReader(SOURCE_PREFIX + path); // testing file path
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            ArrayList<String> step = null;
            ArrayList<StoryObject> storyList = new ArrayList<>();

            StoryObject story = null;
            String line;
            while((line = bufferedReader.readLine())!= null){
                line = line.strip();
                if(line.startsWith("#") || line.startsWith("stories")) continue;
                if(line.startsWith("- story")){
                    // create new story or store previous story
                    if(story != null){
                        // previous story exist
                        story.setSteps(step);
                        storyList.add(story);
                    }
                    String name = line.split(":")[1];
                    story = new StoryObject(name.strip()); // create new story
                }else if(line.startsWith("steps")){
                    // create new steps list
                    step = new ArrayList<>();
                }else{
                    // should be steps content, store it
                    if(line.length() < 1) continue;
                    if(step != null)
                        step.add(line);
                }
            }
            if(story != null)
                story.setSteps(step);
            storyList.add(story);
            storyFile.setStoryList(storyList);
        }catch (IOException e){
            e.printStackTrace();
        }
//        System.out.println(storyFile);
        return storyFile;
    }
    public StoryFile loadStory(ArrayList<String> input) throws IOException{
        StoryFile storyFile = new StoryFile();
        ArrayList<String> step = null;
        ArrayList<StoryObject> storyList = new ArrayList<>();
        StoryObject story = null;
        for(String line: input){
            line = line.strip();
            if(line.startsWith("#") || line.startsWith("stories")) continue;
            if(line.startsWith("- story")){
                // create new story or store previous story
                if(story != null){
                    // previous story exist
                    story.setSteps(step);
                    storyList.add(story);
                }
                String name = line.split(":")[1];
                story = new StoryObject(name.strip()); // create new story
            }else if(line.startsWith("steps")){
                // create new steps list
                step = new ArrayList<>();
            }else{
                // should be steps content, store it
                if(line.length() < 1) continue;
                if(step != null)
                    step.add(line);
            }
        }
        if(story != null)
            story.setSteps(step);
        storyList.add(story);
        storyFile.setStoryList(storyList);
        return storyFile;
    }

    /**
     * load response.yml file, read line
     * @param path file path
     */
    public ResponseFile loadResponse(String path){
        ResponseFile file = new ResponseFile();
        try{
            System.out.println("[YmlReader.loadResponse] load path: [" + path + "]");
//            FileReader reader = new FileReader(SOURCE_PREFIX + path); // testing file path
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            ArrayList<ResponseObject> list = new ArrayList<>();
            ArrayList<String> content = null;
            ResponseObject currentResponse = null;
            String line;
            while((line = bufferedReader.readLine())!= null){
                if(line.strip().startsWith("#") || line.strip().startsWith("responses")) continue;
                if(line.strip().startsWith("utter_")){
                    if(currentResponse != null){
                        // previous response exist
                        currentResponse.setResponseContent(content);
                        list.add(currentResponse);
                    }
                    // create new response action
                    currentResponse = new ResponseObject(line.strip().split(":")[0]);
                    content = new ArrayList<>();
                }else{
                    // should be response action content
                    if(line.strip().length() < 1) continue;
                    if(content != null) content.add(line);
                }
            }
            if(currentResponse != null)
                currentResponse.setResponseContent(content);
            list.add(currentResponse);
            file.setResponseList(list);
        }catch (IOException e){
            e.printStackTrace();
        }
//        System.out.println(file);
        return file;
    }

    /**
     * load domain.yml
     * mainly for local rasa domain setting file
     * ignore several settings currently, need to add more things for complete domain setting
     * currently un-completed
     * suspend
     * @param path file path
     */
    public void loadDomainToObj(String path){
        DomainFile file = new DomainFile();
        DomainEditFlag editFlag = DomainEditFlag.NONE;
        try{
            System.out.println("[YmlReader.loadDomainToObj] load path: [" + path + "]");
//            FileReader reader = new FileReader(SOURCE_PREFIX + path); // testing file path
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            ArrayList<IntentSet> intentList = new ArrayList<>();
            ArrayList<EntitySet> entityList;
            ArrayList<SlotSet> slotList;
            ArrayList<String> actionList;
            ArrayList<FormSet> formList;
            SessionConfig sessionConfig = new SessionConfig();
            IntentSet intent = null;
            ArrayList<String> tmp = null;
            String line;
            String intentEditStatus = "none";
            while((line = bufferedReader.readLine())!= null){
                if(line.strip().startsWith("#")) continue;
                // currently ignore responses setting
                if(line.strip().startsWith("responses:")) continue;
                if(line.strip().startsWith("version")){
                    // set version
                    file.setVersion(line.split(":")[1].strip());
                }
                // check edit status(flag), decide which is the current status
                editFlag = checkDomainEditFlag(line);
                // do stuff for specific status(flag)
                if(editFlag == DomainEditFlag.SESSION_CONFIG){
                    if(line.contains("time")){
                        sessionConfig.setExpireTime(Integer.parseInt(line.split(":")[1].strip()));
                    }else{
                        sessionConfig.setCarrySlot(Boolean.parseBoolean(line.split(":")[1].strip()));
                    }
                }
                if(editFlag == DomainEditFlag.INTENTS){
                    // store intent list
                    // start with whitespace = inner config
                    // start with '-' = new intent or inner config
                    // contains ':' = intent header with config or inner config header
                    if(line.startsWith("-")){
                        if(intent != null){
                            // suppose exist previous intent, store it
                            intentList.add(intent);
                        }
                        // new intent
                        String name = line.split(" ")[1];
                        name = name.replace(":", "");
                        intent = new IntentSet(name);
                        // set intent inner setting to none
                        intentEditStatus = "none";
                    }else if(line.startsWith(" ")){
                        line = line.strip();
                        if(line.startsWith("use_entities:")){
                            // suppose exist previous editing inner setting
                            // create new inner config: available entity
                            // set intent edit status to use
                            intentEditStatus = "use";
                        }else if(line.startsWith("ignore_entities:")){
                            // suppose exist previous editing inner setting
                            // create new inner config: ignore entity
                            // set intent edit status to ignore
                            intentEditStatus = "ignore";
                        }
                        if(line.startsWith("-")){
                            // inner config
                            if(intentEditStatus.startsWith("use")){
                                tmp = intent.getAvailableEntity();
                                tmp.add(line.split(" ")[1]);
                                intent.setAvailableEntity(tmp);
                            }else if(intentEditStatus.startsWith("ignore")){
                                tmp = intent.getIgnoreEntity();
                                tmp.add(line.split(" ")[1]);
                                intent.setIgnoreEntity(tmp);
                            }
                        }
                    }
                }
            }
            // save everything loaded
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(file);
    }

    /**
     * check which block in domain file is editing currently
     * @param line current line
     * @return edit status
     */
    private DomainEditFlag checkDomainEditFlag(String line){
        if(line.startsWith("session_config")){
            return DomainEditFlag.SESSION_CONFIG;
        }else if(line.startsWith("intents")){
            return DomainEditFlag.INTENTS;
        }else if(line.startsWith("entities")){
            return DomainEditFlag.ENTITIES;
        }else if(line.startsWith("slots")){
            return DomainEditFlag.SLOTS;
        }else if(line.startsWith("actions")){
            return DomainEditFlag.ACTIONS;
        }else if(line.startsWith("forms")){
            return DomainEditFlag.FORMS;
        }else if(line.startsWith("responses")){
            return DomainEditFlag.RESPONSES;
        }
        return DomainEditFlag.NONE;
    }

    /**
     * try to load domain file
     * store all settings in string
     * result map key: intent, action, response, etc
     * result map value: setting details
     * @param path file path
     * @return hashmap of domain file
     */
    public HashMap<String, ArrayList<String>> loadDomainAsString(String path){
        DomainEditFlag currentFlag = DomainEditFlag.NONE;
        DomainEditFlag previousFlag = DomainEditFlag.NONE;
//        DomainSettingFile file = new DomainSettingFile();
        // key: type name (intent, entities, action...)
        // value: string ArrayList of setting content
        HashMap<String, ArrayList<String>> domainMap = new HashMap<>();
        try{
            System.out.println("[YmlReader.loadDomainAsString] load path: [" + path + "]");
//            FileReader reader = new FileReader(SOURCE_PREFIX + path); // testing file path
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            ArrayList<String> tmp = null; // store current setting details
            while((line = bufferedReader.readLine()) != null){
                if(line.startsWith("#")) continue; // ignore annotation in file
                else if(line.startsWith("version:")){
                    // save version data, do nothing for now, suppose every version is '2.0'
                    continue;
                }
                // check edit flag if line starts with specific character
                if(line.matches("^[afiesrv].*"))
                    currentFlag = checkDomainEditFlag(line);
                if(previousFlag != currentFlag){
                    // suppose previous object exist
                    if(tmp != null)
                        domainMap.put(previousFlag.name(), tmp);
                    // change to new stage, initialize instance
                    tmp = new ArrayList<>();
                    previousFlag = currentFlag;
                    continue;
                }
                // store data
                if(currentFlag != DomainEditFlag.NONE){
                    tmp.add(line);
                }
            }
            // store last loaded object
            if(tmp != null) domainMap.put(currentFlag.name(), tmp);
//            System.out.println(domainMap);
        }catch (IOException e){
            e.printStackTrace();
        }
        return domainMap;
    }
    public HashMap<String, HashMap<String, Setting>> loadDomain(ArrayList<String> input) throws IOException{
        DomainEditFlag currentFlag = DomainEditFlag.NONE;
        DomainEditFlag previousFlag = DomainEditFlag.NONE;
        HashMap<String, ArrayList<String>> domainMap = new HashMap<>();
        ArrayList<String> tmp = null; // store current setting details
        for(String line: input){
            if(line.startsWith("#")) continue; // ignore annotation in file
            else if(line.startsWith("version:")){
                // save version data, do nothing for now, suppose every version is '2.0'
                continue;
            }
            // check edit flag if line starts with specific character
            if(line.matches("^[afiesrv].*"))
                currentFlag = checkDomainEditFlag(line);
            if(previousFlag != currentFlag){
                // suppose previous object exist
                if(tmp != null)
                    domainMap.put(previousFlag.name(), tmp);
                // change to new stage, initialize instance
                tmp = new ArrayList<>();
                previousFlag = currentFlag;
                continue;
            }
            // store data
            if(currentFlag != DomainEditFlag.NONE){
                tmp.add(line);
            }
        }
        if(tmp != null) domainMap.put(currentFlag.name(), tmp); // store last loaded object
        return parseDomainMap(domainMap);
    }

    /**
     * parse domain file string map to setting object
     * result map key type name (intent, action, responses, etc)
     * result map value hashmap of details
     * key: name of setting(intent_a for example), value: details of setting(detail of intent_a in this case)
     * @param map domain file hashmap
     */
    public HashMap<String, HashMap<String, Setting>> parseDomainMap(HashMap<String, ArrayList<String>> map){
        HashMap<String, HashMap<String, Setting>> domainFile = new HashMap<>();
//        HashMap<String, Setting> content = new HashMap<>();
//        Setting element = null;
        map.forEach((k, v) -> {
            if(k.contains("ACTION")){
                domainFile.put("actions", parseStringAction(v));
            }else if(k.contains("SESSION")){
                domainFile.put("session_config", parseStringSession(v));
            }else if(k.contains("INTENT")){
                domainFile.put("intents", parseStringIntent(v));
            }else if(k.contains("FORMS")){
                domainFile.put("forms", parseStringForm(v));
            }else if(k.contains("RESPONSES")){
                domainFile.put("responses", parseStringResponse(v));
            }else if(k.contains("SLOTS")){
                domainFile.put("slots", parseStringSlot(v));
            }else if(k.contains("ENTITIES")){
                domainFile.put("entities", parseStringEntities(v));
            }
        });
//        System.out.println(domainFile);
        return domainFile;
    }

    /**
     * parse domain file detail
     * type: intent, action, form, session_config, slot, response, entities
     * @param detail domain file detail
     * @return parsed hashmap
     */
    private HashMap<String, Setting> parseStringAction(ArrayList<String> detail){
        HashMap<String, Setting> actionMap = new HashMap<>();
        for(String token: detail){
            token = token.strip().substring(2);
            actionMap.put(token, null);
        }
        return actionMap;
    }
    private HashMap<String, Setting> parseStringIntent(ArrayList<String> detail){
        HashMap<String, Setting> intentList = new HashMap<>();
        Setting intent = new Setting();
        ArrayList<String> tmp;
        String name = "";
        for(String token: detail){
            if(token.matches("^-.*")){
                // create new intent
                token = token.replace(":", "");
                token = token.substring(2);
                intent = new Setting(token);
                name = token;
//                intentList.put(token, intent);
            }else{
                tmp = intent.getDetails();
                tmp.add(token);
                intent.setDetails(tmp);
//                intentList.put(name, intent);
            }
            intentList.put(name, intent);
        }
        return intentList;
    }
    private HashMap<String, Setting> parseStringSession(ArrayList<String> detail){
        HashMap<String, Setting> sessionConfig = new HashMap<>();
        Setting session = new Setting();
        ArrayList<String> tmp;
        for(String token: detail){
            String[] config = token.split(":");
            session = new Setting(config[0].strip());
            tmp = session.getDetails();
            tmp.add(config[1].strip());
            sessionConfig.put(config[0].strip(), session);
        }
        return sessionConfig;
    }
    private HashMap<String, Setting> parseStringForm(ArrayList<String> detail){
        HashMap<String, Setting> formList = new HashMap<>();
        Setting form = new Setting();
        ArrayList<String> tmp;
        String name = "";
        for(String token: detail){
            if(token.matches("^  [a-zA-Z].*")){
                // create new form
                token = token.replace(":", "");
                token = token.strip();
                name = token;
                form = new Setting(token);
//                formList.put(name, form);
            }else{
                tmp = form.getDetails();
                tmp.add(token);
                form.setDetails(tmp);
//                formList.put(name, form);
            }
            formList.put(name, form);
        }
        return formList;
    }
    private HashMap<String, Setting> parseStringSlot(ArrayList<String> detail){
        return getStringSettingHashMap(detail);
    }
    private HashMap<String, Setting> parseStringResponse(ArrayList<String> detail){
        return getStringSettingHashMap(detail);
    }

    private HashMap<String, Setting> getStringSettingHashMap(ArrayList<String> detail) {
        HashMap<String, Setting> responseList = new HashMap<>();
        Setting response = new Setting();
        ArrayList<String> responseConfig = new ArrayList<>();
        String name = "";
        for(String token: detail){
            if(token.matches("^  [a-zA-Z].*")){
                // create new response
                token = token.replace(":", "");
                token = token.strip();
                name = token;
                response = new Setting(name);
            }else{
                responseConfig = response.getDetails();
                responseConfig.add(token);
                response.setDetails(responseConfig);
            }
            responseList.put(name, response);
        }
        return responseList;
    }

    private HashMap<String, Setting> parseStringEntities(ArrayList<String> detail){
        HashMap<String, Setting> entitiesList = new HashMap<>();
        for(String token: detail){
            token = token.strip().substring(2);
            entitiesList.put(token, null);
        }
        return entitiesList;
    }

    /**
     * load action.py
     * read each line in file
     * @param path file path
     */
    public ActionFile loadAction(String path){
        ActionFile actionFile = new ActionFile();
        HashMap<String, ActionFunc> actionPair = new HashMap<>();
        try{
            System.out.println("[YmlReader.loadAction] load path: [" + path + "]");
//            FileReader fileReader = new FileReader(SOURCE_PREFIX + path); // testing file path
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> config = new ArrayList<>();
            ActionFunc action = null;
            ArrayList<String> actionDetail = new ArrayList<>();
            String line;
            String name;
            while((line = bufferedReader.readLine()) != null){
                if(line.matches("^from.*import.*")){
                    // import statement
                    config.add(line);
                    continue;
                }else if(line.strip().startsWith("#"))
                    continue;
                if(line.matches("^class.*\\(.*\\):")){
                    // create new action
                    actionDetail = new ArrayList<>();
                    actionDetail.add(line);
                    name = line.split("\\(")[0].replace("class", "").strip();
                    action = new ActionFunc(name);
                }else if(line.strip().matches("^return.*")){
                    // end of action
                    actionDetail.add(line);
                    action.setContent(actionDetail);
                    actionPair.put(action.getActionName(), action);
                }else{
                    // action content
                    actionDetail.add(line);
                }
            }
            // store all action to result
            actionFile.setImportConfig(config);
            actionFile.setActionList(actionPair);
//            System.out.println(actionFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        return actionFile;
    }
    public ActionFile loadAction(ArrayList<String> input){
        ActionFile actionFile = new ActionFile();
        HashMap<String, ActionFunc> actionPair = new HashMap<>();
        ArrayList<String> config = new ArrayList<>();
        ActionFunc action = null;
        ArrayList<String> actionDetail = new ArrayList<>();
        String name;
        for(String line: input){
            if(line.matches("^from.*import.*")){
                // import statement
                config.add(line);
                continue;
            }else if(line.strip().startsWith("#"))
                continue;
            if(line.matches("^class.*\\(.*\\):")){
                // create new action
                actionDetail = new ArrayList<>();
                actionDetail.add(line);
                name = line.split("\\(")[0].replace("class", "").strip();
                action = new ActionFunc(name);
            }else if(line.strip().matches("^return.*")){
                // end of action
                actionDetail.add(line);
                action.setContent(actionDetail);
                actionPair.put(action.getActionName(), action);
            }else{
                // action content
                actionDetail.add(line);
            }
        }
        actionFile.setImportConfig(config);
        actionFile.setActionList(actionPair);
        return actionFile;
    }
}

