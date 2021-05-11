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
                                byte sex, Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, String userCode,
                                String password, Time entryTime, Time exitTime) {

        CUser user = new CUser();
        user.setPeopleAttributes(documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, dateJoined);
        user.setUserCode(userCode);
        user.setPassword(password);
        user.setEntryTime(entryTime);
        user.setExitTime(exitTime);
        return user;
    }

    public static CUser newUser(String documentType, String documentNumber, String firstName, String lastName, String numberPhone, String email,
                                byte sex, Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, String userCode,
                                String password, Time entryTime, Time exitTime) {

        if (!isValidFirstName(firstName) && !isValidLastName(lastName)) {
            return null;
        }

        CUser user = new CUser();
        user.setPeopleAttributes(documentType, documentNumber, String.format("%s, %s", filter(lastName), filter(firstName)), numberPhone, email, sex, birthdate, address, district, dateJoined);
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

    public static User get(int idUser) {
        return User.Query.get(idUser);
    }

    public static List<CUser> getList(boolean isDelete) {
        return Query.getList(isDelete);
    }

    public static List<CUser> search(String values) {
        return Query.search(values);
    }

    @Override
    protected boolean save() {
        if (getClass() != null) {
            if (isValidUser()) {
                return super.save();
            }
            return false;
        }
        return false;
    }

    private boolean isValidUser() {

        if (documentExist(getDocumentNumber()))
            return false;

        if (!isValidDocument(getDocumentType(), getDocumentNumber()))
            return false;

        if (!isValidFullName(getFullName()))
            return false;

        if (!isValidPhoneNumber(getNumberPhone()))
            return false;

        if (!isValidEmail(getEmail()))
            return false;

        if (!isValidAddress(getAddress()))
            return false;

        if(!isValidPassword(getPassword()))
            return false;

        return true;
    }

}
