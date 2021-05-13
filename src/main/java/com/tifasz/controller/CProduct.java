package com.tifasz.controller;

import com.tifasz.model.Brand;
import com.tifasz.model.Price;
import com.tifasz.model.Product;

import java.util.List;

public class CProduct extends Product {
    public CProduct() {
    }

    public CProduct(int idProduct, String productCode, String description, double stock, CCategory category, Brand brand, List<Price> prices, boolean idDelete) {
        super(idProduct, productCode, description, stock, category, brand, prices, idDelete);
    }

    @Override
    public boolean save() {
        return super.save();
    }

    public static CProduct newProduct(){
        return null;
    }

    public static CProduct get(){
        return null;
    }

    public static CProduct getList(){
        return null;
    }

    public static CProduct search(){
        return null;
    }

    private boolean isValidProduct(){
        return true;
    }
}
