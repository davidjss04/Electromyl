package com.fassti.model;

import com.fassti.solution.IModel;

public class SaleItem implements IModel {

    private final Sale sale;
    private int idSaleItem;
    private Product product;
    private String unit;
    private double productPrice;
    private double productQuality;
    private double subTotal;

    public SaleItem() {
        this.idSaleItem = 0;
        this.sale = new Sale();
        this.product = new Product();
        this.unit = "";
        this.productPrice = 0d;
        this.productQuality = 0d;
        this.subTotal = 0d;
    }

    public SaleItem(int idSaleDetail, Sale sale, Product product, double productPrice, double productQuality) {
        this.idSaleItem = idSaleDetail;
        this.sale = sale;
        this.product = product;
        this.productPrice = productPrice;
        this.productQuality = productQuality;
    }

    public double getSubTotal() {

        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getIdSaleItem() {
        return idSaleItem;
    }

    public void setIdSaleItem(int idSaleItem) {
        this.idSaleItem = idSaleItem;
    }

    public Sale getSale() {
        return sale;
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

    public double getProductQuality() {
        return productQuality;
    }

    public void setProductQuality(double productQuality) {
        this.productQuality = productQuality;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                ", idSaleItem=" + idSaleItem +
                ", product=" + product +
                ", productPrice=" + productPrice +
                ", productQuality=" + productQuality +
                '}';
    }
}
