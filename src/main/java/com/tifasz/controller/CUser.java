package com.tifasz.controller;

import com.tifasz.model.UbiGeoDistrict;
import com.tifasz.model.User;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static com.tifasz.solution.Validation.*;

public class CUser extends User {

    public CUser() {
    }

    public CUser(String userCode, String password, Time entryTime, Time exitTime, boolean isSuperUser, boolean isActive, boolean isStaff) {
        super(userCode, password, entryTime, exitTime, isSuperUser, isActive, isStaff);
    }

    public CUser(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, String userCode, String password, Time entryTime, Time exitTime, boolean isSuperUser, boolean isActive, boolean isStaff) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined, userCode, password, entryTime, exitTime, isSuperUser, isActive, isStaff);
    }

    public static CUser newUser(String documentType, String documentNumber, String fullName, String numberPhone, String email,
                                byte sex, Date birthdate, String address, CUbiGeoDistrict district, Date dateJoined, String userCode,
                                String password, Time entryTime, Time exitTime) {

        CUser user = new CUser();
        user.setDocumentType(documentType);
        user.setDocumentNumber(documentNumber);
        user.setFullName(fullName);
        user.setNumberPhone(numberPhone);
        user.setEmail(email);
        user.setSex(sex);
        user.setBirthdate(birthdate);
        user.setAddress(address);
        user.setDistrict(district);
        user.setDateJoined(dateJoined);
        user.setUserCode(userCode);
        user.setPassword(password);
        user.setEntryTime(entryTime);
        user.setExitTime(exitTime);
        return user;
    }

    public static CUser newUser(String documentType, String documentNumber, String firstName, String lastName, String numberPhone, String email,
                                byte sex, Date birthdate, String address, CUbiGeoDistrict district, Date dateJoined, String userCode,
                                String password, Time entryTime, Time exitTime) {

        if (!isValidFirstName(firstName) && !isValidLastName(lastName)) {
            return null;
        }

        CUser user = new CUser();
        user.setDocumentType(documentType);
        user.setDocumentNumber(documentNumber);
        user.setFullName(String.format("%s, %s", filter(lastName), filter(firstName)));
        user.setNumberPhone(numberPhone);
        user.setEmail(email);
        user.setSex(sex);
        user.setBirthdate(birthdate);
        user.setAddress(address);
        user.setDistrict(district);
        user.setDateJoined(dateJoined);
        user.setUserCode(userCode);
        user.setPassword(password);
        user.setEntryTime(entryTime);
        user.setExitTime(exitTime);
        return user;
    }

    public static CUser get(int op, String documentNumber) {

        if (op == 1)
            if (isValidDocument("DNI", documentNumber)) {
                return Query.get(documentNumber);
            }

        if (op == 2)
            if (isValidDocument("RUC", documentNumber)) {
                return Query.get(documentNumber);
            }

        return null;
    }

    public static CUser get(int idUser) {
        return Query.get(idUser);
    }

    public static List<CUser> getList(boolean isDelete) {
        return Query.getList(isDelete);
    }

    public static List<CUser> search(String values) {
        return Query.search(values);
    }

    @Override
    public boolean save() {
        if (isValidUser()) {
            filterUser();
            return super.save();
        }
        return false;
    }

    private void filterUser() {
        this.setFullName(filter(getFullName()));
        this.setNumberPhone(filter(getNumberPhone()));
        this.setEmail(filter(getEmail()));
        this.setSex(getSex());
        this.setBirthdate(getBirthdate());
        this.setAddress(filter(getAddress()));
        this.setDistrict(getDistrict());
        this.setDateJoined(getDateJoined());
        this.setUserCode(getUserCode());
        this.setPassword(getPassword());
        this.setEntryTime(getEntryTime());
        this.setExitTime(getExitTime());
    }

    private boolean isRepeatUser(){
        return false;
    }

    private boolean isValidUser() {

        if (documentExist(getDocumentNumber())){
            System.out.println("1");
            return false;
        }

        if (!isValidDocument(getDocumentType(), getDocumentNumber())){
            System.out.println("2");
            return false;
        }

        if (!isValidFullName(getFullName())){
            System.out.println("3");
            return false;
        }

        if (!isValidPhoneNumber(getNumberPhone())){
            System.out.println("4");
            return false;
        }

        if (!isValidEmail(getEmail())){
            System.out.println("5");
            return false;
        }

        if (!isValidAddress(getAddress())){
            System.out.println("6");
            return false;
        }

        if(!isValidPassword(getPassword())){
            System.out.println("7");
            return false;
        }

        return true;
    }

}
