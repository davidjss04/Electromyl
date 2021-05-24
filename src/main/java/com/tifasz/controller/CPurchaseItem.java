package com.tifasz.controller;

import com.tifasz.model.Product;
import com.tifasz.model.PurchaseItem;

import java.util.ArrayList;
import java.util.List;

public class CPurchaseItem extends PurchaseItem {
    public CPurchaseItem() {
        super();
    }

    public CPurchaseItem(int idPurchase) {
        super(idPurchase);
    }

    public CPurchaseItem(int idItem, Product product, String unit, double productPrice, double productQuantity, double subTotal, int idPurchase) {
        super(idItem, product, unit, productPrice, productQuantity, subTotal, idPurchase);
    }

    @Override
    public boolean save() {
        return super.save();
    }

    public static CPurchaseItem newItem(Product product, String unit, double productPrice, double productQuantity) {
        CPurchaseItem item = new CPurchaseItem();
        item.setProduct(product);
        item.setUnit(unit);
        item.setProductQuantity(productQuantity);
        item.setProductPrice(productPrice);
        return item;
    }

    public static List<PurchaseItem> getDetails(List<CPurchaseItem> purchaseItems){
        List<PurchaseItem> purchaseItemList = new ArrayList<>();
        purchaseItemList.addAll(purchaseItems);
        return purchaseItemList;
    }

}
