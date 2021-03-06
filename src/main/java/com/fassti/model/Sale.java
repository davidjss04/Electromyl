package com.fassti.model;

import com.fassti.solution.IModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Sale implements IModel {

    private int idSale;
    private Ticket ticket;
    private User user;
    private Client client;
    private PaymentMethod paymentMethod;
    private boolean isDelete;
    private Date date;
    private List<SaleItem> detail;

    public Sale() {
        this.idSale = 0;
        this.ticket = new Ticket();
        this.client = new Client();
        this.user = new User();
        this.detail = new ArrayList<>();
        this.paymentMethod = new PaymentMethod();
        this.isDelete = false;
        this.date = null;
    }

    public Sale(int idSale, Ticket ticket, User user, Client client, PaymentMethod paymentMethod, boolean isDelete, Date date, List<SaleItem> detail) {
        this.idSale = idSale;
        this.ticket = ticket;
        this.user = user;
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.isDelete = isDelete;
        this.date = date;
        this.detail = detail;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<SaleItem> getDetail() {
        return detail;
    }

    public void setDetail(List<SaleItem> detail) {
        this.detail = detail;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idSale=" + idSale +
                ", client=" + client +
                ", user=" + user +
                ", detail=" + detail +
                ", paymentMethod=" + paymentMethod +
                ", isDelete=" + isDelete +
                ", date=" + date +
                '}' + '\n';
    }
}
