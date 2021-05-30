package com.example.demo.entity.Rasa.v2.domain;

public class FormSlotConfig {
    private String slotName;
    // from_entity, from_text, from_intent, from_trigger_intent
    // didn't find 'from_intent' and 'from_trigger_intent' example
    // currently ignore 'from_intent' and 'from_trigger_intent' anyway
    private String slotType;
    private String sourceEntity;

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public void setSourceEntity(String sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    public String getSlotName() {
        return slotName;
    }

    public String getSlotType() {
        return slotType;
    }

    public String getSourceEntity() {
        return sourceEntity;
    }

    @Override
    public String toString() {
        return "FormSlotConfig{" +
                "slotName='" + slotName + '\'' +
                ", slotType='" + slotType + '\'' +
                ", sourceEntity='" + sourceEntity + '\'' +
                '}';
    }
}

/*
your_form:
  required_slots:
      slot_name:
      - type: from_entity
        entity: entity_name
        role: role_name              <- ignore this
        group: group name            <- ignore this
        intent: intent_name          <- ignore this
        not_intent: excluded_intent  <- ignore this
*/