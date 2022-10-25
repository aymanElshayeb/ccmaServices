package com.infenion.ccmamodel.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseEntity {
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        return gson.toJson(this);
    }
}
