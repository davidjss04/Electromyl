package com.tifasz.controller;

import com.tifasz.model.Provider;
import com.tifasz.model.Purchase;

import java.sql.Date;

public class CPurchase extends Purchase {
    public CPurchase() {
    }

    public CPurchase(int idPurchase, Provider provider, Date date, boolean isDelete) {
        super(idPurchase, provider, date, isDelete);
    }

    @Override
    public boolean save() {
        return super.save();
    }

    public static CPurchase newPurchase(){
        return null;
    }

    public static CPurchase get(){
        return null;
    }

    public static CPurchase getList(){
        return null;
    }

    public static CPurchase search(){
        return null;
    }

    private boolean isValidPurchase(){
        return true;
    }
}
