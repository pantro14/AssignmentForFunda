package com.funda.davidpardo.fundaassignment.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by davidpardo on 1/25/17.
 */

public class FundaObject {

    @SerializedName("MakelaarId")
    public String makelaarId;

    @SerializedName("MakelaarNaam")
    public String makelaarName;

    public int quantity;

    public FundaObject(String makelaarId, String makelaarName, int quantity) {
        this.makelaarId = makelaarId;
        this.makelaarName = makelaarName;
        this.quantity = quantity;
    }

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


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
