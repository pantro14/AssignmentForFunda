package com.funda.davidpardo.fundaassignment.util.threading;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by davidpardo on 1/26/17.
 */

public class RequestThread implements Runnable {

    private RequestCallback requestCallback;
    private String url;
    private int typeRequest;
    private OkHttpClient client = new OkHttpClient();

    public RequestThread(RequestCallback requestCallback, String url, int typeRequest){
        this.requestCallback = requestCallback;
        this.url = url;
        this.typeRequest = typeRequest;

    }

    @Override
    public void run() {
            requestCallback.callbackThread(serverRequestExecute(), typeRequest);
    }

    public String serverRequestExecute(){
        try {
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch ( IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
