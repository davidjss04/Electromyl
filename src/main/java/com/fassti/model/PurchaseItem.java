package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PurchaseItem extends Item implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idPurchase;

    public PurchaseItem() {
        super();
        this.idPurchase = 0;

    }

    public PurchaseItem(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public PurchaseItem(int idItem, Product product, String unit, double productPrice, double productQuantity, double subTotal, int idPurchase) {
        super(idItem, product, unit, productPrice, productQuantity, subTotal);
        this.idPurchase = idPurchase;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public static PurchaseItem newItem(Product product, String unit, double productPrice, double productQuantity){
        PurchaseItem item = new PurchaseItem();
        item.setProduct(product);
        item.setUnit(unit);
        item.setProductQuantity(productQuantity);
        item.setProductPrice(productPrice);
        return item;
    }

    @Override
    public boolean save() {

        try {
            if(connectionDB.openConnection()){
                return false;
            }
            connectionDB.query = connectionDB.connection.prepareCall("CAll spCUPurchaseDetail(?, ?, ?, ?, ?, ?)");
            extracted(connectionDB);
            connectionDB.query.setInt(2,getIdPurchase());
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

    public static class Query {
        @NotNull
        @org.jetbrains.annotations.Contract
        private static PurchaseItem insertAttributes(@NotNull PurchaseItem purchaseItem) throws Exception {
            Item.insertAttributes(purchaseItem,connectionDB);
            purchaseItem.setIdPurchase(connectionDB.result.getInt(1));
            return purchaseItem;
        }

        @NotNull
        private static List<PurchaseItem> getPurchaseItems() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<PurchaseItem> purchaseItems = new ArrayList<>();
            while (connectionDB.result.next()) {
                PurchaseItem purchaseItem = insertAttributes(new PurchaseItem());
                purchaseItems.add(purchaseItem);
            }
            return purchaseItems;
        }

        @Nullable
        @Contract(pure = true)
        public static PurchaseItem get(int idPurchaseItem) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                connectionDB.query.setInt(1, idPurchaseItem);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new PurchaseItem());
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
        public static List<PurchaseItem> getList(boolean isDelete) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                connectionDB.query.setBoolean(1, isDelete);
                return getPurchaseItems();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<PurchaseItem> search(String values) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                return getPurchaseItems();

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
        return "PurchaseItem{" +
                "idPurchase=" + idPurchase +
                "} " + super.toString();
    }
}
