package com.example.demo.entity.Rasa.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Intent extends Entity {
    @Override
    @JsonProperty("intent")
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    @JsonProperty("intent")
    public String getName() {
        return super.getName();
    }
}
