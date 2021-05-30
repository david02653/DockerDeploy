package com.example.demo.entity.Rasa.v2.response;

import java.util.ArrayList;

public class ResponseFile {
    private ArrayList<ResponseObject> responseList;

    public void setResponseList(ArrayList<ResponseObject> responseList) {
        this.responseList = responseList;
    }

    public ArrayList<ResponseObject> getResponseList() {
        return responseList;
    }

    @Override
    public String toString() {
        return "ResponseFile{" +
                "responseList=" + responseList +
                '}';
    }

    public boolean containsName(String name){
        return responseList.stream().anyMatch(responseObject -> responseObject.getResponseActionName().equals(name));
    }
}
