package com.tifasz.controller;

import com.tifasz.model.Ticket;

import java.util.List;

public class CTicket extends Ticket {
    public CTicket() {
    }

    public CTicket(int idTicket, String name) {
        super(idTicket, name);
    }

    public static CTicket newTicket(String name) {
        CTicket ticket = new CTicket();
        ticket.setName(name);
        return ticket;
    }

    public static CTicket get(int idCategory) {
        return Query.get(idCategory);
    }

    public static List<CTicket> getList() {
        return Query.getList();
    }

    @Override
    public boolean save() {
        return super.save();
    }
}
