package com.funda.davidpardo.fundaassignment.util.threading;

import com.funda.davidpardo.fundaassignment.model.remote.FundaObject;

import java.util.List;

/**
 * Created by davidpardo on 1/27/17.
 */

public interface RequestCallback {
    void callbackTokenThread(int iterations);
    void callbackRequestThread(List<FundaObject> fundaObjectList);
}
