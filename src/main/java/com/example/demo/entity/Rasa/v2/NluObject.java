package com.example.demo.entity.Rasa.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NluObject {
    // intent(entity)
    @JsonProperty("intent")
    private List<Intent> intent;
    // synonym
    @JsonProperty("synonym")
    private List<Synonym> synonym;
    // regex
    @JsonProperty("regex")
    private List<Regex> regex;
    // lookup
    @JsonProperty("lookup")
    private List<Lookup> lookup;

    public void setIntent(List<Intent> intent) {
        this.intent = intent;
    }

    public void setSynonym(List<Synonym> synonym) {
        this.synonym = synonym;
    }

    public void setRegex(List<Regex> regex) {
        this.regex = regex;
    }

    public void setLookup(List<Lookup> lookup) {
        this.lookup = lookup;
    }

    public List<Intent> getIntent() {
        return intent;
    }

    public List<Synonym> getSynonym() {
        return synonym;
    }

    public List<Regex> getRegex() {
        return regex;
    }

    public List<Lookup> getLookup() {
        return lookup;
    }

    @Override
    public String toString() {
        return "NluObject{" +
                "intent=" + intent +
                ", synonym=" + synonym +
                ", regex=" + regex +
                ", lookup=" + lookup +
                '}';
    }
}
