package com.funda.davidpardo.fundaassignment.util;

import com.funda.davidpardo.fundaassignment.model.remote.FundaObject;
import com.funda.davidpardo.fundaassignment.model.remote.MakelaarCollection;
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
 *
 *  This class is specialized on implement the methods for a custom Json Deserializar
 */

public class CustomJsonDeserializer implements JsonDeserializer<MakelaarCollection> {


    @Override
    public MakelaarCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Type listType = new TypeToken<ArrayList<FundaObject>>(){}.getType();
        MakelaarCollection makelaarCollection = new MakelaarCollection();
        JsonElement content = json.getAsJsonObject().get("Objects");
        makelaarCollection.setList((ArrayList<FundaObject>)new Gson().fromJson(content, listType));
        return makelaarCollection;
    }

}
