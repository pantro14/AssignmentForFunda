package com.funda.davidpardo.fundaassignment.util.threading;

/**
 * Created by davidpardo on 1/27/17.
 */

public interface RequestAtributes {

    public String urlBase = "http://partnerapi.funda.nl/feeds/Aanbod.svc/json";
    public String apiKey = "/005e7c1d6f6c4f9bacac16760286e3cd";
    public String typeParam = "/?type=koop&zo=";
    public String cityParam = "/amsterdam";
    public String gardenParam = "/tuin";
    public String actualPageParam = "/&page=";
    public String pageSizeParam = "&pagesize=";
    public String totalFundaObjects = "TotaalAantalObjecten";
    public int housesAmmountRequest = 0;
    public int housesListRequest = 1;

}
