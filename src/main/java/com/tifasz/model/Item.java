package com.tifasz.model;

import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public abstract class Item {
    private int idItem;
    private Product product;
    private String unit;
    private double productPrice;
    private double productQuantity;
    private double subTotal;

    public Item() {
        this.idItem = 0;
        this.product = new Product();
        this.unit = "";
        this.productPrice = 0d;
        this.productQuantity = 0d;
        this.subTotal = 0d;
    }

    public Item(int idItem, Product product, String unit, double productPrice, double productQuantity, double subTotal) {
        this.idItem = idItem;
        this.product = product;
        this.unit = unit;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.subTotal = subTotal;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
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


    public void extracted(@NotNull ConnectionDB connectionDB) throws SQLException {
        connectionDB.query.setInt(1,getIdItem());
        connectionDB.query.setInt(3,getProduct().getIdProduct());
        connectionDB.query.setString(4,getUnit());
        connectionDB.query.setDouble(5,getProductPrice());
        connectionDB.query.setDouble(6,getProductQuantity());
    }

    @NotNull
    @Contract("_ -> param1")
    public static void insertAttributes(@NotNull Item item, @NotNull ConnectionDB connectionDB) throws Exception {
        item.setIdItem(connectionDB.result.getInt(1));
        item.setProduct(Product.Query.get(connectionDB.result.getInt(2)));
        item.setUnit(connectionDB.result.getString(3));
        item.setProductPrice(connectionDB.result.getDouble(4));
        item.setProductQuantity(connectionDB.result.getDouble(5));
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", product=" + product +
                ", unit='" + unit + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", subTotal=" + subTotal +
                '}';
    }
}
