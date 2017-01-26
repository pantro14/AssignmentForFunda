package com.funda.davidpardo.fundaassignment.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by davidpardo on 1/25/17.
 */

public class FundaObjectResponse {

    public List<FundaObject> FundaobjectList;

    @SerializedName("max_id")
    public long maxId;

    @SerializedName("since_id")
    public int sinceId;

    @SerializedName("refresh_url")
    public String refreshUrl;

    @SerializedName("next_page")
    public String nextPage;


}
