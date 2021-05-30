package com.example.demo.entity.Rasa.v2.domain;

import java.util.ArrayList;

public class FormSet {
    private String formName;
    private ArrayList<FormSlotConfig> requiredSlot;

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public void setRequiredSlot(ArrayList<FormSlotConfig> requiredSlot) {
        this.requiredSlot = requiredSlot;
    }

    public String getFormName() {
        return formName;
    }

    public ArrayList<FormSlotConfig> getRequiredSlot() {
        return requiredSlot;
    }

    @Override
    public String toString() {
        return "FormSet{" +
                "formName='" + formName + '\'' +
                ", requiredSlot=" + requiredSlot +
                '}';
    }
}

/*
your_form:
  required_slots:
      slot_name:
      - type: from_entity
        entity: entity_name
        role: role_name
        group: group name
        intent: intent_name
        not_intent: excluded_intent
*/