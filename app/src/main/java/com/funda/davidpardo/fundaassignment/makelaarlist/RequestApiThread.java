package com.funda.davidpardo.fundaassignment.makelaarlist;

/**
 * Created by davidpardo on 1/26/17.
 */

public class RequestApiThread implements Runnable {

    private String command;

    public RequestApiThread(String s){
        this.command=s;
    }

    @Override
    public void run() {
        processCommand();
    }

    private void processCommand() {
        try {
            String url = "http://partnerapi.funda.nl/feeds/Aanbod.svc/json/005e7c1d6f6c4f9bacac16760286e3cd/?type=koop&zo=/amsterdam/tuin/&page=1&pagesize=25";
            //new FundaObjectApiHandler().execute(url);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.command;
    }
}
