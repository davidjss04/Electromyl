package com.tifasz.model;

import com.tifasz.solution.ConnectionDB;
import com.tifasz.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Sale implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

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

        try {
            if (connectionDB.openConnection()) {
                return false;
            }
            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUSale(?, ?, ?, ?, ?, ?, ?)");
            connectionDB.query.setInt(1, getIdSale());
            connectionDB.query.setInt(2, getTicket().getIdTicket());
            connectionDB.query.setInt(3, getUser().getIdPeople());
            connectionDB.query.setInt(4, getClient().getIdPeople());
            connectionDB.query.setInt(5, getPaymentMethod().getIdPaymentMethod());
            connectionDB.query.setDate(6, getDate());
            connectionDB.query.setBoolean(7, isDelete());
            connectionDB.result = connectionDB.query.executeQuery();

            if (connectionDB.result.next()) {
                for (SaleItem item : getDetail()) {
                    item.setIdSale(connectionDB.result.getInt(1));
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

    @Override
    public String toString() {
        return "Sale{" +
                "idSale=" + idSale +
                ", ticket=" + ticket +
                ", user=" + user +
                ", client=" + client +
                ", paymentMethod=" + paymentMethod +
                ", isDelete=" + isDelete +
                ", date=" + date +
                ", detail=" + detail +
                '}';
    }

    public static class Query {
        @NotNull
        @org.jetbrains.annotations.Contract
        private static Sale insertAttributes(@NotNull Sale sale) throws Exception {
            sale.setIdSale(connectionDB.result.getInt(1));
            sale.setTicket(Ticket.Query.get(connectionDB.result.getInt(2)));
            sale.setUser(User.Query.get(connectionDB.result.getInt(3)));
            sale.setClient(Client.Query.get(connectionDB.result.getInt(4)));
            sale.setPaymentMethod(PaymentMethod.Query.get(connectionDB.result.getInt(5)));
            sale.setDate(connectionDB.result.getDate(6));
            sale.setDelete(connectionDB.result.getBoolean(7));
            return sale;
        }

        @NotNull
        private static List<Sale> getSales() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Sale> sales = new ArrayList<>();
            while (connectionDB.result.next()) {
                Sale sale = insertAttributes(new Sale());
                sales.add(sale);
            }
            return sales;
        }

        @Nullable
        @Contract(pure = true)
        public static Sale get(int idSale) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                connectionDB.query.setInt(1, idSale);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new Sale());
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
        public static List<Sale> getList(boolean isDelete) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                connectionDB.query.setBoolean(1, isDelete);
                return getSales();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Sale> search(String values) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("");
                return getSales();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }
    }
}
