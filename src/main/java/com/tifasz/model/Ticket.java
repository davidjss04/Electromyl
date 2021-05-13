package com.tifasz.model;

import com.tifasz.controller.CTicket;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Ticket{
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

    protected boolean save() {
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
        private static List<CTicket> getCTickets() {
            List<CTicket> tickets = new ArrayList<>();
            tickets.add(new CTicket(1, "FACTURA"));
            tickets.add(new CTicket(2, "BOLETA"));
            tickets.add(new CTicket(3, "COTIZACIÓN"));
            tickets.add(new CTicket(4, "NOTA DE VENTA"));
            return tickets;
        }

        @Nullable
        @Contract(pure = true)
        public static CTicket get(int idCTicket) {
            for (CTicket ticket : getCTickets()) {
                if (ticket.getIdTicket() == idCTicket) {
                    return ticket;
                }
            }
            return null;
        }

        @Contract(pure = true)
        public static List<CTicket> getList() {
            return getCTickets();
        }

    }
}
