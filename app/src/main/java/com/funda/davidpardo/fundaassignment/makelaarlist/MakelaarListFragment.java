package com.funda.davidpardo.fundaassignment.makelaarlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.funda.davidpardo.fundaassignment.R;
import com.funda.davidpardo.fundaassignment.model.remote.FundaObject;
import com.funda.davidpardo.fundaassignment.model.remote.MakelaarCollection;
import com.funda.davidpardo.fundaassignment.util.CustomJsonDeserializer;
import com.funda.davidpardo.fundaassignment.util.threading.RequestAtributes;
import com.funda.davidpardo.fundaassignment.util.threading.RequestCallback;
import com.funda.davidpardo.fundaassignment.util.threading.RequestThread;
import com.funda.davidpardo.fundaassignment.util.ui.DividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class MakelaarListFragment extends Fragment implements RequestCallback, RequestAtributes{

    public int taskType;
    ProgressBar progressBar;
    private RecyclerView itemsRecyclerView;
    private List<FundaObject> makelaarArray;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            taskType = bundle.getInt("task");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.makelaarlist_layout, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        itemsRecyclerView = (RecyclerView) view.findViewById(R.id.itemsRecyclerView);
        makelaarArray = new ArrayList<>();
        if(taskType==1){
            String url = urlBase+apiKey+typeParam+cityParam+actualPageParam+"1"+pageSizeParam+"0";
            Thread requestThreadOne = new Thread(new RequestThread(this, url, housesAmmountRequest));
            requestThreadOne.start();
        }else if(taskType==2){
            String url = urlBase+apiKey+typeParam+cityParam+gardenParam+actualPageParam+"1"+pageSizeParam+"0";
            Thread requestThreadOne = new Thread(new RequestThread(this, url, housesAmmountRequest));
            requestThreadOne.start();
        }

        return view;
    }

    @Override
    public void callbackThread(final String response, int requestType) {
        if(requestType == 0){
            setExecuteRequestParameters(response);
        }else if(requestType == 1){
            if (getActivity() != null && !getActivity().isFinishing()){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateMakelaarListUI(response);
                    }
                });
            }
        }
    }

    public void updateMakelaarListUI(String response){
        if (response != null && !response.isEmpty()) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(MakelaarCollection.class, new CustomJsonDeserializer())
                    .create();
            MakelaarCollection makelaarCollection = gson.fromJson(response, MakelaarCollection.class);
            List<FundaObject> makelaarArray =
                    makelaarCollection.countMakelaarNumberObjects();
            this.makelaarArray = makelaarCollection.countMakelaarGlobalObjects(this.makelaarArray, makelaarArray);
            MakelaarAdapter itemsAdapter = new MakelaarAdapter(this.makelaarArray);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            itemsRecyclerView.setLayoutManager(layoutManager);
            itemsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            DividerItemDecoration mDividerItemDecoration =
                    new DividerItemDecoration(itemsRecyclerView.getContext(),
                            null);
            itemsRecyclerView.addItemDecoration(mDividerItemDecoration);
            itemsRecyclerView.setAdapter(itemsAdapter);
        }
    }

    public void setExecuteRequestParameters(String response){
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(response).getAsJsonObject();
        int totalObjects = obj.get(totalFundaObjects).getAsInt();
        int iterations = totalObjects / 25;
        progressBar.setMax(iterations);
        for(int index=0; index<iterations; index++){
            try {
                if(taskType==1){
                    String url = urlBase+apiKey+typeParam+cityParam+actualPageParam+String.valueOf(index+1)+pageSizeParam+"25";
                    Thread requestThreadOne = new Thread(new RequestThread(this, url, housesListRequest));
                    requestThreadOne.start();
                    System.out.println("Task type: "+taskType+", index:"+index);

                }else if(taskType==2){
                    String url = urlBase+apiKey+typeParam+cityParam+gardenParam+actualPageParam+String.valueOf(index+1)+pageSizeParam+"25";
                    Thread requestThreadTwo = new Thread(new RequestThread(this, url, housesListRequest));
                    requestThreadTwo.start();
                    System.out.println("Task type: "+taskType+", index:"+index);
                }
                Thread.sleep(1600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progressBar.setProgress(index+1);
        }
    }
}
