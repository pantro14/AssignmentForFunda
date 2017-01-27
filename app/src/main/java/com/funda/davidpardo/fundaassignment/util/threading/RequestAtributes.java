package com.funda.davidpardo.fundaassignment.util.threading;

/**
 * Created by davidpardo on 1/27/17.
 */

public interface RequestAtributes {

    String urlBase = "http://partnerapi.funda.nl/feeds/Aanbod.svc/json";
    String apiKey = "/005e7c1d6f6c4f9bacac16760286e3cd";
    String typeParam = "/?type=koop&zo=";
    String cityParam = "/amsterdam";
    String gardenParam = "/tuin";
    String actualPageParam = "/&page=";
    String pageSizeParam = "&pagesize=";
    String totalFundaObjects = "TotaalAantalObjecten";
    int housesAmmountRequest = 0;
    int housesListRequest = 1;

}
