package com.funda.davidpardo.fundaassignment;

import com.funda.davidpardo.fundaassignment.util.threading.RequestThread;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FundaAssignmentUnitTest {

    @Test
    public void requesFundaApiTest() throws Exception {
        String url = "http://partnerapi.funda.nl/feeds/Aanbod.svc/json/005e7c1d6f6c4f9bacac16760286e3cd/?type=koop&zo=/amsterdam/&page=0&pagesize=0";
        RequestThread requestThread = new RequestThread(url);
        String response = requestThread.serverRequestExecute();
        assertNotEquals(response, null);
    }
}