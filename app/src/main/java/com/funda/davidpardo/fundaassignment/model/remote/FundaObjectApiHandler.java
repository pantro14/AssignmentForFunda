package com.funda.davidpardo.fundaassignment.model.remote;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by davidpardo on 1/25/17.
 */

public class FundaObjectApiHandler extends AsyncTask<String, Void, String> {

    OkHttpClient client = new OkHttpClient();

    public Context context;
    public RecyclerView itemsRecyclerView;


    public FundaObjectApiHandler(Context context, RecyclerView itemsRecyclerView){
        this.context = context;
        this.itemsRecyclerView = itemsRecyclerView;
    }

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

    }
}
