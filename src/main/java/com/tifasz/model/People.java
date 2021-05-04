package com.tifasz.model;

import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.SQLException;

public abstract class People {

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

    public People(String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, Date dateJoined) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.sex = sex;
        this.birthdate = birthdate;
        this.address = address;
        this.district = district;
        this.dateJoined = dateJoined;
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

    public static class Query{

        public static boolean documentExist(String document){
            try {
                if (connectionDB.openConnection()) {
                    return false;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT document_number FROM people WHERE document_number = ?");
                connectionDB.query.setString(1, document);
                connectionDB.result = connectionDB.query.executeQuery();

                if (connectionDB.result.next()) {
                    return true;
                }

                return false;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }

            return false;
        }
    }

    public void setPeopleAttributes(String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, Date dateJoined) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.sex = sex;
        this.birthdate = birthdate;
        this.address = address;
        this.district = district;
        this.dateJoined = dateJoined;
    }

    public void extracted(@NotNull ConnectionDB connectionDB) throws SQLException {
        connectionDB.query.setInt(1, getIdPeople());
        connectionDB.query.setString(2, getDocumentType());
        connectionDB.query.setString(3, getDocumentNumber());
        connectionDB.query.setString(4, getFullName());
        connectionDB.query.setString(5, getNumberPhone());
        connectionDB.query.setString(6, getEmail());
        connectionDB.query.setByte(7, getSex());
        connectionDB.query.setDate(8, getBirthdate());
        connectionDB.query.setString(9, getAddress());
        connectionDB.query.setString(10, getDistrict().getIdDistrict());
        connectionDB.query.setBoolean(11, isDelete());
        connectionDB.query.setDate(12, getDateJoined());
    }


    @NotNull
    @Contract("_ -> param1")
    public static void insertAttributes(@NotNull People people, @NotNull ConnectionDB connectionDB) throws Exception {
        people.setIdPeople(connectionDB.result.getInt(1));
        people.setDocumentType(connectionDB.result.getString(2));
        people.setDocumentNumber(connectionDB.result.getString(3));
        people.setFullName(connectionDB.result.getString(4));
        people.setNumberPhone(connectionDB.result.getString(5));
        people.setEmail(connectionDB.result.getString(6));
        people.setSex(connectionDB.result.getByte(7));
        people.setBirthdate(connectionDB.result.getDate(8));
        people.setAddress(connectionDB.result.getString(9));
        people.setDistrict(UbiGeoDistrict.get(connectionDB.result.getString(10)));
        people.setDelete(connectionDB.result.getBoolean(11));
        people.setDateJoined(connectionDB.result.getDate(12));
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
