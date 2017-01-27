package com.funda.davidpardo.fundaassignment.util.threading;

/**
 * Created by davidpardo on 1/27/17.
 */

public interface RequestCallback {
    void callbackThread(String response, int requestType);
}
