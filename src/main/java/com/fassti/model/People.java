package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;

import java.sql.Date;

public class People{
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idPeople;
    private String documentType;
    private String documentNumber;
    private String fullName;
    private String numberPhone;
    private String email;
    private byte sex;
    private Date birthdate;
    private String address;
    private UbiGeoDistrict district;
    private boolean isDelete;
    private Date dateJoined;

    public People() {
        this.idPeople = 0;
        this.documentType = "";
        this.documentNumber = "";
        this.fullName = "";
        this.numberPhone = "";
        this.email = "";
        this.sex = 0;
        this.birthdate = null;
        this.address = "";
        this.district = null;
        this.isDelete = false;
        this.dateJoined = null;
    }

    public People(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined) {
        this.idPeople = idPeople;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.sex = sex;
        this.birthdate = birthdate;
        this.address = address;
        this.district = district;
        this.isDelete = isDelete;
        this.dateJoined = dateJoined;
    }

    public static ConnectionDB getConnectionDB() {
        return connectionDB;
    }

    public static void setConnectionDB(ConnectionDB connectionDB) {
        People.connectionDB = connectionDB;
    }

    public int getIdPeople() {
        return idPeople;
    }

    public void setIdPeople(int idPeople) {
        this.idPeople = idPeople;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UbiGeoDistrict getDistrict() {
        return district;
    }

    public void setDistrict(UbiGeoDistrict district) {
        this.district = district;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public String toString() {
        return "People{" +
                "idPeople=" + idPeople +
                ", documentType='" + documentType + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", birthdate=" + birthdate +
                ", address='" + address + '\'' +
                ", district=" + district +
                ", isDelete=" + isDelete +
                ", dateJoined=" + dateJoined +
                '}';
    }
}
