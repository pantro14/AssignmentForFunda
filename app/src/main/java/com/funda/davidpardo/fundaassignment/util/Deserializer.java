package com.funda.davidpardo.fundaassignment.util;

import com.funda.davidpardo.fundaassignment.model.remote.FundaObject;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by davidpardo on 1/26/17.
 */

public class Deserializer implements JsonDeserializer<FundaObject> {
    @Override
    public FundaObject deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonElement content = jsonElement.getAsJsonObject().get("Objects");
        return new Gson().fromJson(content, FundaObject.class);
    }
}
