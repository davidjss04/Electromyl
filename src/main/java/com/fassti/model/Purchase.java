package com.fassti.model;

import java.sql.Date;
import java.util.List;

public class Purchase {
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
