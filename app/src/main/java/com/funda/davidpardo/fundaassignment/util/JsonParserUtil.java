package com.funda.davidpardo.fundaassignment.util;

import com.funda.davidpardo.fundaassignment.model.remote.FundaObject;
import com.funda.davidpardo.fundaassignment.model.remote.MakelaarCollection;
import com.funda.davidpardo.fundaassignment.util.threading.RequestAtributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

/**
 * Created by davidpardo on 1/25/17.
 *
 * This Utility class is specialized in parsing the data coming from
 * the Json file to the model elements in the app
 */

public class JsonParserUtil implements RequestAtributes{

    /**
     *  Method for parsing a Json into a List of Objects FundaObject
     */
    public static List<FundaObject> parseJsonMaakelarCollection(String response){
        List<FundaObject> makelaarArray = null;
        if (response != null && !response.isEmpty()) {
            if (response != null && !response.isEmpty()) {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(MakelaarCollection.class, new CustomJsonDeserializer())
                        .create();
                MakelaarCollection makelaarCollection = gson.fromJson(response, MakelaarCollection.class);
                makelaarArray = makelaarCollection.countMakelaarNumberObjects();
            }
        }
        return makelaarArray;
    }

    /**
     *  Method for parsing a Json into a the amount of Objects to download
     */
    public static int parseJsonAmountObjects(String response){
        int iterations = 0;
        if (response != null && !response.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(response).getAsJsonObject();
            int totalObjects = obj.get(totalFundaObjects).getAsInt();
            iterations = totalObjects / 25;
        }
        return iterations;
    }
}
