package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Product implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idProduct;
    private String productCode;
    private double stock;
    private Category category;
    private Brand brand;
    private List<Price> prices;
    private boolean idDelete;

    public Product() {
        this.idProduct = 0;
        this.productCode = "";
        this.stock = 0D;
        this.category = new Category();
        this.brand = new Brand();
        this.prices = new ArrayList<>();
        this.idDelete = false;
    }

    public Product(int idProduct, String productCode, double stock, Category category, Brand brand, List<Price> prices, boolean idDelete) {
        this.idProduct = idProduct;
        this.productCode = productCode;
        this.stock = stock;
        this.category = category;
        this.brand = brand;
        this.prices = prices;
        this.idDelete = idDelete;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public boolean isIdDelete() {
        return idDelete;
    }

    public void setIdDelete(boolean idDelete) {
        this.idDelete = idDelete;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", productCode='" + productCode + '\'' +
                ", stock=" + stock +
                ", category=" + category +
                ", brand=" + brand +
                ", prices=" + prices +
                ", idDelete=" + idDelete +
                '}';
    }

    public static class Query {
        @NotNull
        @org.jetbrains.annotations.Contract
        private static Product insertAttributes(@NotNull Product product) throws Exception {
            return product;
        }

        @NotNull
        private static List<Product> getCategories() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Product> products = new ArrayList<>();
            while (connectionDB.result.next()) {
                Product product = insertAttributes(new Product());
                products.add(product);
            }
            return products;
        }

        @Nullable
        @Contract(pure = true)
        public static Product get(int idProduct) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Product> getList(boolean isDelete) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Product> search(String values) {
            return null;
        }
    }
}
