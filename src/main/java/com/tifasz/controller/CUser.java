package com.tifasz.controller;

import com.tifasz.model.UbiGeoDistrict;
import com.tifasz.model.User;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static com.tifasz.solution.Validation.*;

public class CUser extends User {
    //Se realizaron dos metodos, con la alternativa para que que en uno se puedo ingresar el nombre completo y en el otro por detalle
    public static User newUser(String documentType, String documentNumber, String fullName, String numberPhone, String email,
                               byte sex, Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, String userCode,
                               String password, Time entryTime, Time exitTime){

        if (isValidUser(documentType, documentNumber, fullName, numberPhone, email, address, password)) {
            return User.newUser(documentType, documentNumber, fullName, numberPhone, email,
                    sex, birthdate, address, district, dateJoined, userCode, password, entryTime, exitTime);
        }

        return null;
    }

    public static User newUser(String documentType,  String documentNumber, String firstName, String lastName, String numberPhone, String email,
                               byte sex, Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, String userCode,
                               String password, Time entryTime, Time exitTime){

        if (isValidUser(documentType, documentNumber, firstName, lastName, numberPhone, email, address, password)) {
            return User.newUser(documentType, documentNumber,String.format("%s, %s",firstName,lastName)+ firstName, numberPhone, email,
                    sex, birthdate, address, district, dateJoined, userCode, password, entryTime, exitTime);
        }

        return null;
    }

    public static User get(int op, String documentNumber){

        if(op == 1)
            if(isValidDocument("DNI",documentNumber)){
                return User.Query.get(documentNumber);
            }

        if(op == 2)
            if(isValidDocument("RUC",documentNumber)){
                return User.Query.get(documentNumber);
            }

        return null;
    }

    public static User get(int idUser){
        return User.Query.get(idUser);
    }

    public static List<User> getList(boolean isDelete){
        return User.Query.getList(isDelete);
    }

    public static List<User> search(String values){
        return User.Query.search(values);
    }

    private static boolean isValidUser(String documentType, String documentNumber, String fullName, String numberPhone, String email, String address, String password){

        if (!isValidDocument(documentType,documentNumber))
            return false;

        if (!isValidFullName(fullName))
            return false;

        if (!isValidPhoneNumber(numberPhone))
            return false;

        if (!isValidEmail(email))
            return false;

        if (!isValidAddress(address))
            return false;

        if (!isValidPassword(password))
            return false;

        return true;

    }

    private static boolean isValidUser(String documentType, String documentNumber, String firstName, String lastName, String numberPhone, String email, String address, String password) {

        if (!isValidDocument(documentType, documentNumber))
            return false;

        if (!isValidFirstName(firstName))
            return false;

        if (!isValidLastName(lastName))
            return false;

        if (!isValidPhoneNumber(numberPhone))
            return false;

        if (!isValidEmail(email))
            return false;

        if (!isValidAddress(address))
            return false;

        if (!isValidPassword(password))
            return false;

        return true;

    }

}
