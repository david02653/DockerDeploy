package com.example.demo.entity.Rasa.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Synonym extends Entity{
    @Override
    @JsonProperty("synonym")
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    @JsonProperty("synonym")
    public String getName() {
        return super.getName();
    }
}
