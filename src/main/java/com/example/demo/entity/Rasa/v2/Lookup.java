package com.example.demo.entity.Rasa.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lookup extends Entity {
    @Override
    @JsonProperty("lookup")
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    @JsonProperty("lookup")
    public String getName() {
        return super.getName();
    }
}
