package com.funda.davidpardo.fundaassignment.util;

import com.funda.davidpardo.fundaassignment.model.remote.FundaObject;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by davidpardo on 1/26/17.
 */

public class Deserializer<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        JsonElement content = je.getAsJsonObject().get("Objects");
        Type listType = new TypeToken<ArrayList<FundaObject>>(){}.getType();
        return new Gson().fromJson(content, listType);

    }
    /*@Override
    public FundaObject deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonElement content = jsonElement.getAsJsonObject().get("Objects");
        return new Gson().fromJson(content, FundaObject.class);
    }*/
}
