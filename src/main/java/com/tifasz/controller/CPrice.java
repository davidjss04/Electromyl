package com.tifasz.controller;

import com.tifasz.model.Price;

import java.util.List;

import static com.tifasz.solution.Validation.isEmpty;
import static com.tifasz.solution.Validation.isValidValuesPrice;

public class CPrice extends Price {
    public CPrice() {
    }

    public CPrice(int idPrice, double value, int idProduct, CUnit unit) {
        super(idPrice, value, idProduct, unit);
    }

    @Override
    public boolean save() {
        if(this.getIdProduct() != 0)
            return super.save();

        return false;
    }

    public static CPrice newPrice(double value, CUnit unit){

        if (!isValidValuesPrice(value)){
            return null;
        }

        CPrice price = new CPrice();
        price.setValue(value);
        price.setUnit(unit);
        return price;
    }

    public static CPrice newPrice(double value, CUnit unit, int idProduct){

        if (!isValidValuesPrice(value)){
            return null;
        }

        CPrice price = new CPrice();
        price.setValue(value);
        price.setUnit(unit);
        price.setIdProduct(idProduct);
        return price;
    }

    public static CPrice get(int idPrice){
        return Query.get(idPrice);
    }

    public static List<CPrice> getList(int idProduct){
        return Query.getList(idProduct);
    }

    public static List<CPrice> search(String values){
        if (isEmpty(values)){
            return null;
        }
        return Query.search(values);
    }

}
