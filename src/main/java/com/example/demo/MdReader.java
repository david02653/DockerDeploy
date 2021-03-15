package com.example.demo;

import com.example.demo.entity.Nlu;
import com.example.demo.entity.NluObject;
import com.example.demo.entity.Stories;
import com.example.demo.entity.Story;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MdReader {

    public Nlu readNlu(String name){

        Nlu nlu = new Nlu();
        ArrayList<NluObject> list = new ArrayList<>();
        try{
            FileReader reader = new FileReader(name);
            BufferedReader buff = new BufferedReader(reader);
            String line;
            String[] token;
            NluObject nObj = null;
            ArrayList<String> element = null;
            while((line = buff.readLine())!= null){
                if(line.startsWith("##")){
                    // nlu object header found
                    nObj = new NluObject();
                    element = new ArrayList<>();
                    line = line.substring(3);
                    token = line.split(":");
                    nObj.setType(token[0]);
                    nObj.setName(token[1]);
                }else if(line.startsWith("-")){
                    if(element != null) element.add(line.substring(2));
                }else{
                    if(nObj != null) nObj.setContent(element);
                    list.add(nObj);
                }
            }
            nlu.setElements(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return nlu;
    }

    public Stories readStories(String name){

        Stories stories = new Stories();
        ArrayList<Story> list = new ArrayList<>();
        try{
            FileReader reader = new FileReader(name);
            BufferedReader buff = new BufferedReader(reader);
            String line;
            while((line = buff.readLine()) != null){
                if(line.startsWith("##")){
                    // new story found
                }else if(line.startsWith("*")){
                    // story intent found
                }else if(line.startsWith("  - ")){
                    // intent action
                }else{
                    // add element
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
