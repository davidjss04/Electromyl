package com.tifasz.model;

import com.tifasz.solution.ConnectionDB;
import com.tifasz.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Ticket implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idTicket;
    private String name;

    public Ticket() {
        this.idTicket = 0;
        this.name = "";
    }

    public Ticket(int idTicket, String name) {
        this.idTicket = idTicket;
        this.name = name;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Query {

        @NotNull
        private static List<Ticket> getTickets() {
            List<Ticket> tickets = new ArrayList<>();
            tickets.add(new Ticket(1, "FACTURA"));
            tickets.add(new Ticket(2, "BOLETA"));
            tickets.add(new Ticket(3, "COTIZACIÓN"));
            tickets.add(new Ticket(4, "NOTA DE VENTA"));
            return tickets;
        }

        @Nullable
        @Contract(pure = true)
        public static Ticket get(int idTicket) {
            for (Ticket ticket : getTickets()) {
                if (ticket.getIdTicket() == idTicket) {
                    return ticket;
                }
            }
            return null;
        }

        @Contract(pure = true)
        public static List<Ticket> getList() {
            return getTickets();
        }

    }
}
