package com.example.demo.Service;

import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.action.ActionFunc;
import com.example.demo.entity.Rasa.v2.domain.Setting;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.nlu.NluObject;
import com.example.demo.entity.Rasa.v2.response.ResponseFile;
import com.example.demo.entity.Rasa.v2.response.ResponseObject;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import com.example.demo.entity.Rasa.v2.story.StoryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IntegrateSetting {
    public NluFile integrateNlu(NluFile source, NluFile addition){
        NluFile result = new NluFile();
        result.setNlu(source.getNlu());
        List<NluObject> resultList = result.getNlu();
        List<NluObject> addList = addition.getNlu();
        for(NluObject target: addList){
            if(result.containsName(target.findName())){
                // this name already exist
                // maybe throw some error
                System.out.println("already exist");
            }else{
                resultList.add(target);
            }
        }
        result.setNlu(resultList);
//        System.out.println(result);
        return result;
    }

    public ActionFile integrateAction(ActionFile source, ActionFile addition){
        ActionFile result = source.copy();
        ArrayList<String> config = result.getImportConfig();
        HashMap<String, ActionFunc> actionList = result.getActionList();
        // merge config
        for(String token: addition.getImportConfig()){
            if(!config.contains(token))
                config.add(token);
        }
        // merge action
        addition.getActionList().forEach((name, func) -> {
            if(!actionList.containsKey(name))
                actionList.put(name, func);
        });
        result.setImportConfig(config);
        result.setActionList(actionList);
        return result;
    }

    public StoryFile integrateStory(StoryFile source, StoryFile addition){
        StoryFile result = source.copy();
        ArrayList<StoryObject> storyList = result.getStoryList();
        for(StoryObject addObj: addition.getStoryList()){
            if(storyList.stream().noneMatch(obj -> obj.getName().equals(addObj.getName()))){
                storyList.add(addObj);
            }
        }
        return result;
    }

    /**
     * merge domain file settings
     * currently have no javaBean for domain file, use HashMap instead
     * contain 7 types of data: session_config, intent, entities, slot, actions, form, responses
     * ignore session_config, this one is only controlled by project manager
     * @param source current project setting
     * @param addition new domain settings wants to add
     * @return merged domain file hashMap
     */
    public HashMap<String, HashMap<String, Setting>> integrateDomain(HashMap<String, HashMap<String, Setting>> source, HashMap<String, HashMap<String, Setting>> addition){
        // copy source map
        HashMap<String, HashMap<String, Setting>> resultMap = new HashMap<>();
//        source.forEach((k, v) -> {resultMap.put(k, v);});
        source.forEach(resultMap::put);
        HashMap<String, Setting> tmp;
        // merge action
        tmp = new HashMap<>();
        source.get("actions").forEach(tmp::put);
//        for(Map.Entry<String, Setting> entry: addition.get("actions").entrySet()){
//            if(!resultMap.get("actions").containsKey(entry.getKey()))
//                tmp.put(entry.getKey(), entry.getValue());
//        }
//        resultMap.put("actions", tmp);
        addition.get("actions").forEach(tmp::put);
        resultMap.put("actions", tmp);
        // merge intent
        tmp = new HashMap<>();
        source.get("intents").forEach(tmp::put);
        addition.get("intents").forEach(tmp::put);
        resultMap.put("intents", tmp);
        // merge slot
        tmp = new HashMap<>();
        source.get("slots").forEach(tmp::put);
        addition.get("slots").forEach(tmp::put);
        resultMap.put("slots", tmp);
        // merge entities
        tmp = new HashMap<>();
        source.get("entities").forEach(tmp::put);
        addition.get("entities").forEach(tmp::put);
        resultMap.put("entities", tmp);
        // merge form
        tmp = new HashMap<>();
        source.get("forms").forEach(tmp::put);
        addition.get("forms").forEach(tmp::put);
        resultMap.put("forms", tmp);
        // merge responses
        tmp = new HashMap<>();
        source.get("responses").forEach(tmp::put);
        addition.get("responses").forEach(tmp::put);
        resultMap.put("responses", tmp);
        return resultMap;
    }

    public void integrateResponse(ResponseFile source, ResponseFile addition){
        ResponseFile result = new ResponseFile();
        result.setResponseList(source.getResponseList());
        ArrayList<ResponseObject> resultList = result.getResponseList();
        ArrayList<ResponseObject> addList = addition.getResponseList();
        for(ResponseObject obj: addList){
            if(result.containsName(obj.getResponseActionName())){
                System.out.println("already exist");
            }else{
                resultList.add(obj);
            }
        }
        result.setResponseList(resultList);
    }

    public void integrateRule(){
    }
}
