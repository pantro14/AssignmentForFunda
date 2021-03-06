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
import com.funda.davidpardo.fundaassignment.util.threading.RequestAtributes;
import com.funda.davidpardo.fundaassignment.util.threading.RequestCallback;
import com.funda.davidpardo.fundaassignment.util.threading.RequestThread;
import com.funda.davidpardo.fundaassignment.util.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MakelaarListFragment extends Fragment implements RequestCallback, RequestAtributes{

    public int taskType;
    private ProgressBar progressBar;
    private RecyclerView itemsRecyclerView;
    private List<FundaObject> makelaarArray;

    private final String TASK = "task";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            taskType = bundle.getInt(TASK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.makelaarlist_layout, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        itemsRecyclerView = (RecyclerView) view.findViewById(R.id.itemsRecyclerView);
        makelaarArray = new ArrayList<>();
        /*
         *  A request to the API is performed in order to know the total amount of Objects
         *  that come the GET method retrieve for each task:
         *  task1: Brokers from Amsterdam, get objects
         *  task2: Brokers from Amsterdam, get objects with a garden
         */
        if(taskType==getResources().getInteger(R.integer.taskOne)){
            String url = urlBase+apiKey+typeParam+cityParam+actualPageParam+"1"+pageSizeParam+"0";
            Thread requestThreadOne = new Thread(new RequestThread(this, url, housesAmmountRequest));
            requestThreadOne.start();
        }else if(taskType==getResources().getInteger(R.integer.taskTwo)){
            String url = urlBase+apiKey+typeParam+cityParam+gardenParam+actualPageParam+"1"+pageSizeParam+"0";
            Thread requestThreadOne = new Thread(new RequestThread(this, url, housesAmmountRequest));
            requestThreadOne.start();
        }

        return view;
    }


    /**
     * Interface method to perform some instructions each time the request service is done
     */
    @Override
    public void callbackTokenThread(int iterations) {
        setExecuteRequestParameters(iterations);
    }

    /**
     * Interface method to perform some instructions each time the request service is done
     */
    @Override
    public void callbackRequestThread(final List<FundaObject> fundaObjectList) {
        if (getActivity() != null && !getActivity().isFinishing()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateMakelaarListUI(fundaObjectList);
                }
            });
        }
    }

    /**
     *  Method for updating the UI fragment components
     */
    private void updateMakelaarListUI(List<FundaObject> makelaarArray) {
        this.makelaarArray = MakelaarCollection.countMakelaarGlobalObjects(this.makelaarArray, makelaarArray);
        List<FundaObject> makelaarArrayTopTen = this.makelaarArray.subList(0, 10);
        MakelaarAdapter itemsAdapter = new MakelaarAdapter(makelaarArrayTopTen);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        itemsRecyclerView.setLayoutManager(layoutManager);
        itemsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration mDividerItemDecoration =
                new DividerItemDecoration(itemsRecyclerView.getContext(),
                        null);
        itemsRecyclerView.addItemDecoration(mDividerItemDecoration);
        itemsRecyclerView.setAdapter(itemsAdapter);
    }

    /**
     *  Method for counting the amount of objects, set the iterations in order to
     *  request the API using FIFO threading,
     */
    private void setExecuteRequestParameters(int iterations){
        progressBar.setMax(iterations);
        for(int index=0; index<iterations; index++){
            try {
                if(taskType==getResources().getInteger(R.integer.taskOne)){
                    String url = urlBase+apiKey+typeParam+cityParam+actualPageParam+String.valueOf(index+1)+pageSizeParam+getResources().getString(R.string.maxPagination);
                    Thread requestThreadOne = new Thread(new RequestThread(this, url, housesListRequest));
                    requestThreadOne.start();
                }else if(taskType==getResources().getInteger(R.integer.taskTwo)){
                    String url = urlBase+apiKey+typeParam+cityParam+gardenParam+actualPageParam+String.valueOf(index+1)+pageSizeParam+getResources().getString(R.string.maxPagination);
                    Thread requestThreadTwo = new Thread(new RequestThread(this, url, housesListRequest));
                    requestThreadTwo.start();
                }
                Thread.sleep(getResources().getInteger(R.integer.threadMilliseconds));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progressBar.setProgress(index+1);
        }
    }
}
