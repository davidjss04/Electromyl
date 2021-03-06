package com.fassti.model;

public class Ticket {
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
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", name='" + name + '\'' +
                '}';
    }
}
