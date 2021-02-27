package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;

import java.sql.Date;

public class Client extends People implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private byte origin;
    private byte status;
    private byte condition;

    public Client(){
        super();
        this.origin = 0;
        this.status = 0;
        this.condition = 0;
    }

    @Override
    public People newPeople() {
        return null;
    }

    public Client(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, byte origin, byte status, byte condition) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined);
        this.origin = origin;
        this.status = status;
        this.condition = condition;
    }

    public byte getOrigin() {
        return origin;
    }

    public void setOrigin(byte origin) {
        this.origin = origin;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getCondition() {
        return condition;
    }

    public void setCondition(byte condition) {
        this.condition = condition;
    }

    @Override
    public boolean save() {
        return false;
    }

    public static class Query {

    }

    @Override
    public String toString() {
        return "Client{" +
                "origin=" + origin +
                ", status=" + status +
                ", condition=" + condition +
                '}';
    }
}
