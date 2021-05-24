package com.tifasz.controller;

import com.tifasz.model.Provider;
import com.tifasz.model.Purchase;
import com.tifasz.model.PurchaseItem;

import java.sql.Date;
import java.util.List;

public class CPurchase extends Purchase {

    public CPurchase() {
        super();
    }

    public CPurchase(int idPurchase, Provider provider, Date date, boolean isDelete, List<PurchaseItem> detail) {
        super(idPurchase, provider, date, isDelete, detail);
    }

    @Override
    public boolean save() {
        if(isValidPurchase())
            return super.save();

        return false;
    }

    public static CPurchase newPurchase(Provider provider, Date date, boolean isDelete, List<PurchaseItem> detail){
        CPurchase purchase = new CPurchase();
        purchase.setProvider(provider);
        purchase.setDate(date);
        purchase.setDelete(isDelete);
        purchase.setDetail(detail);
        return purchase;
    }

    public static CPurchase get(int idPurchase){
        return Query.get(idPurchase);
    }

    public static List<CPurchase> getList(boolean isDelete){
        return Query.getList(isDelete);
    }

    public static List<CPurchase> search(String values){
        return Query.search(values);
    }

    private boolean isValidPurchase(){
        return true;
    }
}
