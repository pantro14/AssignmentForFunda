package com.funda.davidpardo.fundaassignment.model.remote;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidpardo on 26/01/2017.
 */

public class MakelaarCollection {

    private Map<String, String> makelaarSorted;
    private List<FundaObject> list;

    public MakelaarCollection() {
        super();
    }

    public MakelaarCollection(List<FundaObject> list) {
        this.list = list;
    }

    public List<FundaObject> getList() {
        return list;
    }

    public void setList(List<FundaObject> list) {
        this.list = list;
    }

    public ArrayList<FundaObject> countMakelaarNumberObjects() {
        Map<String, FundaObject> map = new HashMap<>();
        for (FundaObject fundaObject : list) {
            String key = fundaObject.getMakelaarId();
            if (map.get(key) == null) {
                int occurrences = Collections.frequency(Lists.transform(list, new Function<FundaObject, String>() {
                    @Override
                    public String apply(FundaObject fundaObject) {
                        return fundaObject.getMakelaarId();
                    }
                }), key);
                fundaObject.setQuantity(occurrences);
                map.put(key, new FundaObject(fundaObject.getMakelaarId(),
                        fundaObject.getMakelaarName(), fundaObject.getQuantity()));
            }
        }
        ArrayList<FundaObject> makelaarArray = new ArrayList<>(map.values());
        Ordering<FundaObject> byQuantity = new Ordering<FundaObject>() {
            @Override
            public int compare(FundaObject aFundaObject, FundaObject otherFundaObject) {
                return Ints.compare(aFundaObject.getQuantity(), otherFundaObject.getQuantity());
            }
        };
        Collections.sort(makelaarArray,
                byQuantity.nullsFirst().reverse());
        return makelaarArray;
    }
}
