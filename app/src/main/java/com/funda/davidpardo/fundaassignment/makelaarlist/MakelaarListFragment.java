package com.funda.davidpardo.fundaassignment.makelaarlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.funda.davidpardo.fundaassignment.R;
import com.funda.davidpardo.fundaassignment.model.remote.FundaObjectApiHandler;

public class MakelaarListFragment extends Fragment{

    public int taskType;
    ProgressBar progressBar;
    private RecyclerView itemsRecyclerView;
    //private MakelaarAdapter itemsAdapter;


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
        // 101 pages, 25 items
        if(taskType==1){
            //Houses in Amsterdam, Total: 2521, Pages: 101
            for(int i=0; i<101; i++){

            }
            String url = "http://partnerapi.funda.nl/feeds/Aanbod.svc/json/005e7c1d6f6c4f9bacac16760286e3cd/?type=koop&zo=/amsterdam/&page=1&pagesize=25";
            new FundaObjectApiHandler(getContext(), itemsRecyclerView).execute(url);
        }else if(taskType==2){
            //Houses in Amsterdam with Garden, Total: 600, pages: 24
            String url = "http://partnerapi.funda.nl/feeds/Aanbod.svc/json/005e7c1d6f6c4f9bacac16760286e3cd/?type=koop&zo=/amsterdam/tuin/&page=1&pagesize=25";
            new FundaObjectApiHandler(getContext(), itemsRecyclerView).execute(url);
        }

        //someFunction();
        return view;
    }

    public void someFunction() {
        new Thread(new Runnable() {
            public void run(){
                RequestApiThreadPool.executeQueueRequestApi();
            }
        }).start();
    }
}
