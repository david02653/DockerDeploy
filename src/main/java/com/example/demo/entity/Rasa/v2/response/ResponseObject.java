package com.example.demo.entity.Rasa.v2.response;

import java.util.ArrayList;

public class ResponseObject {
    private String responseActionName;
    private ArrayList<String> responseContent;

    public ResponseObject(){}
    public ResponseObject(String name){
        setResponseActionName(name);
    }

    public void setResponseActionName(String responseActionName) {
        this.responseActionName = responseActionName;
    }

    public void setResponseContent(ArrayList<String> responseContent) {
        this.responseContent = responseContent;
    }

    public String getResponseActionName() {
        return responseActionName;
    }

    public ArrayList<String> getResponseContent() {
        return responseContent;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "responseActionName='" + responseActionName + '\'' +
                ", responseContent=" + responseContent +
                '}';
    }
}
