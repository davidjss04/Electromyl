package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;

import java.sql.Date;

public class purchaseDetail implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idPurchaseDetail;
    private Provider provider;
    private Product product;
    private double productPrice;
    private double productQuantity;
    private Date date;

    public purchaseDetail() {
        this.idPurchaseDetail = 0;
        this.provider = new Provider();
        this.product = new Product();
        this.productPrice = 0d;
        this.productQuantity = 0d;
        this.date = null;
    }

    public purchaseDetail(int idPurchaseDetail, Provider provider, Product product, double productPrice, double productQuantity, Date date) {
        this.idPurchaseDetail = idPurchaseDetail;
        this.provider = provider;
        this.product = product;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.date = date;
    }

    public int getIdPurchaseDetail() {
        return idPurchaseDetail;
    }

    public void setIdPurchaseDetail(int idPurchaseDetail) {
        this.idPurchaseDetail = idPurchaseDetail;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean save() {
        return false;
    }

    public static class Query{

    }

    @Override
    public String toString() {
        return "purchaseDetail{" +
                "idPurchaseDetail=" + idPurchaseDetail +
                ", provider=" + provider +
                ", product=" + product +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", date=" + date +
                '}';
    }
}
