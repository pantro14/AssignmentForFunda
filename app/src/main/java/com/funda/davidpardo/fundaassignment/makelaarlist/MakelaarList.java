package com.funda.davidpardo.fundaassignment.makelaarlist;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.funda.davidpardo.fundaassignment.R;

public class MakelaarList extends Fragment{

    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.makelaarlist_layout, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setProgress(50);
        return view;
    }
}
