package com.fassti.model;

import com.fassti.solution.IModel;

import java.sql.Date;

public class Provider extends People implements IModel {
    private String description;

    public Provider(){
        super();
        this.description = "";
    }

    public Provider(String description) {
        this.description = description;
    }

    public Provider(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, String description) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined);
        this.description = description;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
