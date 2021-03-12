package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;

public class SaleItem implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idSaleItem;
    private int idSale;
    private Product product;
    private String unit;
    private double productPrice;
    private double productQuality;
    private double subTotal;

    public SaleItem() {
        this.idSaleItem = 0;
        this.idSale = 0;
        this.product = new Product();
        this.unit = "";
        this.productPrice = 0d;
        this.productQuality = 0d;
        this.subTotal = 0d;
    }

    public SaleItem(int idSaleItem, int idSale, Product product, String unit, double productPrice, double productQuality, double subTotal) {
        this.idSaleItem = idSaleItem;
        this.idSale = idSale;
        this.product = product;
        this.unit = unit;
        this.productPrice = productPrice;
        this.productQuality = productQuality;
        this.subTotal = subTotal;
    }

    public int getIdSaleItem() {
        return idSaleItem;
    }

    public void setIdSaleItem(int idSaleItem) {
        this.idSaleItem = idSaleItem;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
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

    public double getProductQuality() {
        return productQuality;
    }

    public void setProductQuality(double productQuality) {
        this.productQuality = productQuality;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public static SaleItem newItem(Product product, double productPrice, double productQuality){
        SaleItem saleItem = new SaleItem();
        saleItem.setProduct(product);
        saleItem.setProductPrice(productPrice);
        saleItem.setProductQuality(productQuality);
        return saleItem;
    }

    @Override
    public boolean save() {

        try {
            if(connectionDB.openConnection()){
                return false;
            }
            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUSaleDetail(?,?,?,?,?,?)");
            connectionDB.query.setInt(1,getIdSaleItem());
            connectionDB.query.setInt(2,getIdSale());
            System.out.println(getIdSale());
            System.out.println(getProduct().getIdProduct());
            connectionDB.query.setInt(3,getProduct().getIdProduct());
            connectionDB.query.setString(4,getUnit());
            connectionDB.query.setDouble(5,getProductPrice());
            connectionDB.query.setDouble(6,getProductQuality());
            connectionDB.result = connectionDB.query.executeQuery();

            if (!connectionDB.result.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
        return false;
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "idSaleItem=" + idSaleItem +
                ", idSale=" + idSale +
                ", product=" + product +
                ", unit='" + unit + '\'' +
                ", productPrice=" + productPrice +
                ", productQuality=" + productQuality +
                ", subTotal=" + subTotal +
                '}';
    }
}
