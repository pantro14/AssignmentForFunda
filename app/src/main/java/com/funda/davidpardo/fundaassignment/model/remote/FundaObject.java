package com.funda.davidpardo.fundaassignment.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by davidpardo on 1/25/17.
 */

public class FundaObject {

    @SerializedName("Id")
    public String objectId;

    @SerializedName("MakelaarId")
    public String makelaarId;

    @SerializedName("MakelaarNaam")
    public String makelaarName;

    public String getMakelaarId() {
        return makelaarId;
    }

    public void setMakelaarId(String makelaarId) {
        this.makelaarId = makelaarId;
    }

    public String getMakelaarName() {
        return makelaarName;
    }

    public void setMakelaarName(String makelaarName) {
        this.makelaarName = makelaarName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
