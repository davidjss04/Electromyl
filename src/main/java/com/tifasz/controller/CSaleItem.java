package com.tifasz.controller;

import com.tifasz.model.Product;
import com.tifasz.model.SaleItem;

import java.util.ArrayList;
import java.util.List;

public class CSaleItem extends SaleItem {

    public CSaleItem() {
        super();
    }

    public CSaleItem(int idSale) {
        super(idSale);
    }

    public CSaleItem(int idItem, Product product, String unit, double productPrice, double productQuantity, double subTotal, int idSale) {
        super(idItem, product, unit, productPrice, productQuantity, subTotal, idSale);
    }

    @Override
    public boolean save() {
        return super.save();
    }

    public static CSaleItem newItem(CProduct product, String unit, double productPrice, double productQuality) {
        CSaleItem saleItem = new CSaleItem();
        saleItem.setProduct(product);
        saleItem.setUnit(unit);
        saleItem.setProductPrice(productPrice);
        saleItem.setProductQuantity(productQuality);
        return saleItem;
    }
    public static List<SaleItem> getDetails(List<CSaleItem> saleItems){
        List<SaleItem> saleItemList = new ArrayList<>();
        saleItemList.addAll(saleItems);
        return saleItemList;
    }


}
