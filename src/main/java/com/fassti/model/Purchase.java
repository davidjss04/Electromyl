package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Purchase implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idPurchase;
    private Provider provider;
    private Date date;
    private boolean isDelete;
    private List<PurchaseItem> detail;


    public Purchase() {
        this.idPurchase = 0;
        this.provider = new Provider();
        this.date = null;
        this.isDelete = false;
    }

    public Purchase(int idPurchase, Provider provider, Date date, boolean isDelete) {
        this.idPurchase = idPurchase;
        this.provider = provider;
        this.date = date;
        this.isDelete = isDelete;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<PurchaseItem> getDetail() {
        return detail;
    }

    public void setDetail(List<PurchaseItem> detail) {
        this.detail = detail;
    }

    @Override
    public boolean save() {

        try {
            if (connectionDB.openConnection()) {
                return false;
            }
            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUPurchase(?, ?, ?, ?)");
            connectionDB.query.setInt(1, getIdPurchase());
            connectionDB.query.setInt(2, getProvider().getIdPeople());
            connectionDB.query.setDate(3, getDate());
            connectionDB.query.setBoolean(4, isDelete());
            connectionDB.result = connectionDB.query.executeQuery();

            if (connectionDB.result.next()) {
                for (PurchaseItem item : getDetail()) {
                    item.setIdPurchase(connectionDB.result.getInt(1));
                    item.save();
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

    public static class Query{

        @NotNull
        @org.jetbrains.annotations.Contract
        private static Purchase insertAttributes(@NotNull Purchase purchase) throws Exception {
            purchase.setIdPurchase(connectionDB.result.getInt(1));
            purchase.setProvider(Provider.Query.get(2));
            purchase.setDate(connectionDB.result.getDate(3));
            purchase.setDelete(connectionDB.result.getBoolean(4));
            return purchase;
        }

        @NotNull
        private static List<Purchase> getPurchases() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Purchase> purchases = new ArrayList<>();
            while (connectionDB.result.next()) {
                Purchase purchase = insertAttributes(new Purchase());
                purchases.add(purchase);
            }
            return purchases;
        }

        @Nullable
        @Contract(pure = true)
        public static Purchase get(int idPurchase) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                connectionDB.query.setInt(1, idPurchase);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new Purchase());
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
        public static List<Purchase> getList(boolean isDelete) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                connectionDB.query.setBoolean(1, isDelete);
                return getPurchases();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Purchase> search(String values) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                return getPurchases();

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
        return "Purchase{" +
                "idPurchase=" + idPurchase +
                ", provider=" + provider +
                ", date=" + date +
                ", isDelete=" + isDelete +
                '}';
    }
}
