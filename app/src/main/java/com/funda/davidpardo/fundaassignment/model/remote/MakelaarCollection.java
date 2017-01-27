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

    private List<FundaObject> list;

    public MakelaarCollection() {
        super();
    }

    public List<FundaObject> getList() {
        return list;
    }

    public void setList(List<FundaObject> list) {
        this.list = list;
    }

    public List<FundaObject> countMakelaarNumberObjects() {
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
        List<FundaObject> makelaarArray = new ArrayList<>(map.values());
        sortListByAmmount(makelaarArray);
        return makelaarArray;
    }

    public List<FundaObject> countMakelaarGlobalObjects
            (List<FundaObject> globalList, List<FundaObject> nextList) {
        List<FundaObject> list = new ArrayList<FundaObject>();
        list.addAll(globalList);
        list.addAll(nextList);

        Map<String, FundaObject> map = new HashMap<>();
        for (FundaObject fundaObject : list) {
            String key = fundaObject.getMakelaarId();
            if (map.get(key) == null) {
                map.put(key, new FundaObject(fundaObject.getMakelaarId(),
                        fundaObject.getMakelaarName(), fundaObject.getQuantity()));
            }else{
                FundaObject repeated = map.get(key);
                repeated.setQuantity(repeated.getQuantity()+fundaObject.getQuantity());
                map.put(key, repeated);
            }
        }
        List<FundaObject> makelaarArray = new ArrayList<>(map.values());
        sortListByAmmount(makelaarArray);
        return makelaarArray;
    }

    private void sortListByAmmount(List<FundaObject> list){
        Ordering<FundaObject> byQuantity = new Ordering<FundaObject>() {
            @Override
            public int compare(FundaObject aFundaObject, FundaObject otherFundaObject) {
                return Ints.compare(aFundaObject.getQuantity(), otherFundaObject.getQuantity());
            }
        };
        Collections.sort(list,
                byQuantity.nullsFirst().reverse());
    }
}
