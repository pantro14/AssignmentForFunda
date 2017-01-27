package com.funda.davidpardo.fundaassignment.makelaarlist;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by davidpardo on 1/26/17.
 */

public class RequestApiThreadPool {

    public static void executeQueueRequestApi(){
        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            Runnable worker = new RequestApiThread("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
