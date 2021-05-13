package com.tifasz.controller;

import com.tifasz.model.*;

import java.sql.Date;
import java.util.List;

public class CSale extends Sale {

    public CSale() {
    }

    public CSale(int idSale, Ticket ticket, User user, Client client, PaymentMethod paymentMethod, boolean isDelete, Date date, List<SaleItem> detail) {
        super(idSale, ticket, user, client, paymentMethod, isDelete, date, detail);
    }

    @Override
    public boolean save() {
        return super.save();
    }

    public static CSale newSale(){
        return null;
    }

    public static CSale get(){
        return null;
    }

    public static CSale getList(){
        return null;
    }

    public static CSale search(){
        return null;
    }

    private boolean isValidSale(){
        return true;
    }
}
