package com.stenalpjolly.tfcv.data;

import com.google.gson.GsonBuilder;
import java.util.HashSet;
import java.util.Set;
import com.google.gson.Gson;

public class EntityData {

    private String name;
    private Set<EntityData> children;

    public EntityData(String name) {
        this.name = name;
        this.children = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void add(EntityData children) {
        this.children.add(children);
    }

    public void setChildren(Set<String> children) {
        for (String child : children) {
            EntityData entityData = new EntityData(child);
            this.children.add(entityData);
        }
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this).replaceAll("\"","\\\"");
    }
}
