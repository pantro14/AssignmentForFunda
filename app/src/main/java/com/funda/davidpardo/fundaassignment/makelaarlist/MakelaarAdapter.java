package com.funda.davidpardo.fundaassignment.makelaarlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funda.davidpardo.fundaassignment.R;
import com.funda.davidpardo.fundaassignment.model.remote.FundaObject;

import java.util.List;

/**
 * Created by davidpardo on 1/27/17.
 */

public class MakelaarAdapter extends RecyclerView.Adapter<MakelaarAdapter.ViewHolder> {

    private List<FundaObject> makelaarArray;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView makelarId, makelaarName, houseQuantity;

        public ViewHolder(View view) {
            super(view);
            makelarId = (TextView) view.findViewById(R.id.makelarId);
            makelaarName = (TextView) view.findViewById(R.id.makelaarName);
            houseQuantity = (TextView) view.findViewById(R.id.houseQuantity);
        }
    }

    public MakelaarAdapter(List<FundaObject> makelaarArray) {
        this.makelaarArray = makelaarArray;
    }

    @Override
    public int getItemCount() {
        return makelaarArray.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FundaObject fundaObject = makelaarArray.get(position);
        holder.makelarId.setText(fundaObject.getMakelaarId());
        holder.makelaarName.setText(fundaObject.getMakelaarName());
        holder.houseQuantity.setText(String.valueOf(fundaObject.getQuantity()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.makelaar, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
}
