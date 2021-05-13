package com.tifasz.controller;

import com.tifasz.model.Price;
import com.tifasz.model.Unit;

public class CPrice extends Price {
    public CPrice() {
    }

    public CPrice(int idPrice, double value, int idProduct, Unit unit) {
        super(idPrice, value, idProduct, unit);
    }

    @Override
    public boolean save() {
        return super.save();
    }

    public static CPrice newPrice(){
        return null;
    }

    public static CPrice get(){
        return null;
    }

    public static CPrice getList(){
        return null;
    }

    public static CPrice search(){
        return null;
    }

    private boolean isValidPrice(){
        return true;
    }
}
