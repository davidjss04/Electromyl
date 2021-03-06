package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;

public class PurchaseItem implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idPurchaseItem;
    private Purchase purchase;
    private Product product;
    private String unit;
    private double productPrice;
    private double productQuantity;
    private double subTotal;

    public PurchaseItem() {
        this.idPurchaseItem = 0;
        this.purchase = new Purchase();
        this.product = new Product();
        this.unit = "";
        this.productPrice = 0d;
        this.productQuantity = 0d;
        this.subTotal = 0d;
    }

    public PurchaseItem(int idPurchaseItem, Purchase purchase, Product product, String unit, double productPrice, double productQuantity, double subTotal) {
        this.idPurchaseItem = idPurchaseItem;
        this.purchase = purchase;
        this.product = product;
        this.unit = unit;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.subTotal = subTotal;
    }

    public int getIdPurchaseItem() {
        return idPurchaseItem;
    }

    public void setIdPurchaseItem(int idPurchaseItem) {
        this.idPurchaseItem = idPurchaseItem;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(double productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "PurchaseItem{" +
                "idPurchaseItem=" + idPurchaseItem +
                ", purchase=" + purchase +
                ", product=" + product +
                ", unit=" + unit +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                '}';
    }

    public static class Query {

    }
}
