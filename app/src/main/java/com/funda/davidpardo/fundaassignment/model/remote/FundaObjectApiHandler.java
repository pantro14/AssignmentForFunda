package com.funda.davidpardo.fundaassignment.model.remote;

import android.os.AsyncTask;

import com.funda.davidpardo.fundaassignment.util.Deserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
            if (responseString != null && responseString.isEmpty() != true) {
                Gson gson =
                        new GsonBuilder()
                                .registerTypeAdapter(FundaObject.class, new Deserializer())
                                .create();
                FundaObject fundaObject = gson.fromJson(responseString, FundaObject.class);
                String name = fundaObject.getMakelaarName();

                /*Gson gson =
                        new GsonBuilder()
                                .registerTypeAdapter(FundaObject.class, new Deserializer())
                                .create();
                FundaObject fundaObject = gson.fromJson(responseString, FundaObject.class);
                String name = fundaObject.getMakelaarName();*/


                /*Gson gson =
                        new GsonBuilder()
                                .registerTypeAdapter(FundaObject.class, new Deserializer<FundaObject>())
                                .create();

                Type listType = new TypeToken<ArrayList<FundaObject>>(){}.getType();
                List<FundaObject> yourClassList = gson.fromJson(responseString, listType);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
