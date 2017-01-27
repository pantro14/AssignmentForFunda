package com.funda.davidpardo.fundaassignment.util.threading;

import com.funda.davidpardo.fundaassignment.model.remote.FundaObject;
import com.funda.davidpardo.fundaassignment.util.JsonParserUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by davidpardo on 1/26/17.
 *
 * This class is in charge of execute the request to the server
 */

public class RequestThread implements Runnable, RequestAtributes {

    private RequestCallback requestCallback;
    private String url;
    private int typeRequest;
    private OkHttpClient client = new OkHttpClient();

    public RequestThread(String url){
        this.url = url;
    }

    public RequestThread(RequestCallback requestCallback, String url, int typeRequest){
        this.requestCallback = requestCallback;
        this.url = url;
        this.typeRequest = typeRequest;
    }

    @Override
    public void run() {
        if(typeRequest == housesAmmountRequest){
            /*
             * If the Thread is used to get the number of Object, this is executed
            */
            String response = serverRequestExecute();
            int iterations = JsonParserUtil.parseJsonAmountObjects(response);
            requestCallback.callbackTokenThread(iterations);
        }else if(typeRequest == housesListRequest){
            /*
             * If the Thread is used to get the list of Objects
            */
            String response = serverRequestExecute();
            List<FundaObject> fundaObjects = JsonParserUtil.parseJsonMaakelarCollection(response);
            requestCallback.callbackRequestThread(fundaObjects);
        }

    }

    /**
     *  Method for requesting the server using an
     *  http get method
     */
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
