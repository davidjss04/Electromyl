package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SaleItem extends Item implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idSale;

    public SaleItem() {
        super();
        this.idSale = 0;
    }

    public SaleItem(int idSale) {
        this.idSale = idSale;
    }

    public SaleItem(int idItem, Product product, String unit, double productPrice, double productQuantity, double subTotal, int idSale) {
        super(idItem, product, unit, productPrice, productQuantity, subTotal);
        this.idSale = idSale;
    }

    public static SaleItem newItem(Product product,String unit, double productPrice, double productQuality){
        SaleItem saleItem = new SaleItem();
        saleItem.setUnit(unit);
        saleItem.setProduct(product);
        saleItem.setProductPrice(productPrice);
        saleItem.setProductQuantity(productQuality);
        return saleItem;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    @Override
    public boolean save() {
    
        try {
            if(connectionDB.openConnection()){
                return false;
            }
            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUSaleDetail(?,?,?,?,?,?)");
            extracted(connectionDB);
            connectionDB.query.setInt(2,getIdSale());
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

    public static class Query{
        @NotNull
        @org.jetbrains.annotations.Contract
        private static SaleItem insertAttributes(@NotNull SaleItem saleItem) throws Exception {
            Item.insertAttributes(saleItem,connectionDB);
            saleItem.setIdSale(connectionDB.result.getInt(1));
            return saleItem;
        }

        @NotNull
        private static List<SaleItem> getSaleItems() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<SaleItem> saleItems = new ArrayList<>();
            while (connectionDB.result.next()) {
                SaleItem saleItem = insertAttributes(new SaleItem());
                saleItems.add(saleItem);
            }
            return saleItems;
        }

        @Nullable
        @Contract(pure = true)
        public static SaleItem get(int idSaleItem) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                connectionDB.query.setInt(1, idSaleItem);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new SaleItem());
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
        public static List<SaleItem> getList(boolean isDelete) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                connectionDB.query.setBoolean(1, isDelete);
                return getSaleItems();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<SaleItem> search(String values) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                return getSaleItems();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "idSale=" + idSale +
                "} " + super.toString();
    }
}
