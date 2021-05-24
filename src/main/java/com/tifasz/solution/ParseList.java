package com.tifasz.solution;

import com.tifasz.controller.CPrice;
import com.tifasz.model.Price;

import java.util.ArrayList;
import java.util.List;

public class ParseList extends ArrayList {


    public static List<Price> turnList(List<CPrice> cPriceList){
        List<Price> priceList = new ArrayList<>();
        priceList.addAll(cPriceList);
        return priceList;
    }

    public static List<Object> adaptList(List<Object> objectList){
        for (Object object: objectList ) {
            return null;
        }
        return null;
    }

}
