package com.example.demo.entity.Rasa.v2.domain;

public class EntitySet {
    private String entityName;

    public EntitySet(){}
    public EntitySet(String name){
        setEntityName(name);
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    @Override
    public String toString() {
        return "EntitySet{" +
                "entityName='" + entityName + '\'' +
                '}';
    }
}
