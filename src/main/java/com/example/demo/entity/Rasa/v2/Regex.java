package com.example.demo.entity.Rasa.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Regex extends Entity {
    @Override
    @JsonProperty("regex")
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    @JsonProperty("regex")
    public String getName() {
        return super.getName();
    }
}
