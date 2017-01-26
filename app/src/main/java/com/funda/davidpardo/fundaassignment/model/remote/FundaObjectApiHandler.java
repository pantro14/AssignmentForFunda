package com.funda.davidpardo.fundaassignment.model.remote;

import android.os.AsyncTask;

import com.funda.davidpardo.fundaassignment.util.CustomJsonDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by davidpardo on 1/25/17.
 */

public class FundaObjectApiHandler extends AsyncTask<String, Void, String> {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);
        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String responseString) {
        super.onPostExecute(responseString);

        try {
            if (responseString != null && !responseString.isEmpty()) {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(MakelaarCollection.class, new CustomJsonDeserializer())
                        .create();
                MakelaarCollection makelaarCollection = gson.fromJson(responseString, MakelaarCollection.class);
                makelaarCollection.countMakelaarNumberObjects();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
