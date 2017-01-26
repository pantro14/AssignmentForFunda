package com.funda.davidpardo.fundaassignment.model.remote;

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

    public void countMakelaarNumberObjects() {
        Map<String, FundaObject> map = new HashMap<>();
        for (FundaObject person : list) {
            String key = person.getMakelaarId();
            if (map.get(key) == null) {
                int occurrences = Collections.frequency(list, person.getMakelaarName());
                person.setQuantity(occurrences);
                map.put(key, new FundaObject(person.getMakelaarId(), person.getMakelaarName()));
            }
        }
    }

    public int countNumberEqual(List<FundaObject> itemList, String makelaarName) {
        int count = 0;
        for (FundaObject i : itemList) {
            if (i.equals(makelaarName)) {
                count++;
            }
        }
        return count;
    }
}
