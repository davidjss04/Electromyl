package com.tifasz.model;

import com.tifasz.controller.CCategory;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Product {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idProduct;
    private String productCode;
    private String description;
    private double stock;
    private CCategory category;
    private Brand brand;
    private boolean isDelete;
    private List<Price> prices;

    public Product() {
        this.idProduct = 0;
        this.productCode = "";
        this.stock = 0D;
        this.category = new CCategory();
        this.brand = new Brand();
        this.isDelete = false;
        this.prices = new ArrayList<>();
    }

    public Product(int idProduct, String productCode, String description, double stock, CCategory category, Brand brand, List<Price> prices, boolean idDelete) {
        this.idProduct = idProduct;
        this.productCode = productCode;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.brand = brand;
        this.prices = prices;
        this.isDelete = idDelete;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setCategory(CCategory category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setPrice(Price price) {
        this.getPrices().add(price);
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        this.isDelete = delete;
    }

    public boolean save() {
        try {
            if (connectionDB.openConnection()) {
                return false;
            }

            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUProduct(?,?,?,?,?,?,?)");
            connectionDB.query.setInt(1, getIdProduct());
            connectionDB.query.setString(2, getProductCode());
            connectionDB.query.setString(3, getDescription());
            connectionDB.query.setDouble(4, getStock());
            connectionDB.query.setInt(5, getCategory().getIdCategory());
            connectionDB.query.setInt(6, getBrand().getIdBrand());
            connectionDB.query.setBoolean(7, isDelete());
            connectionDB.result = connectionDB.query.executeQuery();

            if (connectionDB.result.next()) {
                for (Price price : getPrices()) {
                    price.setIdProduct(connectionDB.result.getInt(1));
                    price.save();
                }

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
        return "Product{" +
                "idProduct=" + idProduct +
                ", productCode='" + productCode + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", category=" + category +
                ", brand=" + brand +
                ", prices=" + prices +
                ", isDelete=" + isDelete +
                '}' + '\n';
    }

    public static class Query {

        @NotNull
        @org.jetbrains.annotations.Contract
        private static Product insertAttributes(@NotNull Product product) throws Exception {
            product.setIdProduct(connectionDB.result.getInt(1));
            product.setProductCode(connectionDB.result.getString(2));
            product.setDescription(connectionDB.result.getString(3));
            product.setStock(connectionDB.result.getDouble(4));
            product.setCategory(Category.Query.get(connectionDB.result.getInt(5)));
            product.setBrand(Brand.Query.get(connectionDB.result.getInt(6)));
            product.setDelete(connectionDB.result.getBoolean(7));
            product.setPrices(Price.Query.getList(product.getIdProduct()));
            return product;
        }

        @NotNull
        private static List<Product> getProducts() throws Exception {
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
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_product, product_code, description, stock, id_category, id_brand, is_delete FROM  product where id_product = ?");
                connectionDB.query.setInt(1, idProduct);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new Product());
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;

        }

        @Nullable
        @Contract(pure = true)
        public static List<Product> getList(boolean isDelete) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_product, product_code, description, stock, id_category, id_brand, is_delete FROM  product WHERE is_delete = ?");
                connectionDB.query.setBoolean(1, isDelete);
                return getProducts();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Product> search(String values) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_product, product_code, description, stock, id_category, id_brand, is_delete FROM  product WHERE MATCH (product_code, description) AGAINST ('" + values + "*' IN BOOLEAN MODE)");
                return getProducts();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

    }
}
