package com.tifasz.controller;

import com.tifasz.model.Client;
import com.tifasz.model.UbiGeoDistrict;

import java.sql.Date;
import java.util.List;

import static com.tifasz.solution.Validation.*;

public class CClient extends Client {
    public static Client newClient(String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex,
                                   Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, byte origin,
                                   byte status, byte condition) {

        Client client = new Client();
        client.setDocumentType(documentType);
        client.setDocumentNumber(documentNumber);
        client.setFullName(fullName);
        client.setNumberPhone(numberPhone);
        client.setEmail(email);
        client.setSex(sex);
        client.setBirthdate(birthdate);
        client.setAddress(address);
        client.setDistrict(district);
        client.setDateJoined(dateJoined);
        client.setOrigin(origin);
        client.setStatus(status);
        client.setCondition(condition);
        return client;
    }

    public static Client newClient(String documentType, String documentNumber, String firstName, String lastName, String numberPhone, String email,
                                   byte sex, Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, byte origin,
                                   byte status, byte condition) {

        if (!isValidFirstName(firstName) && !isValidLastName(lastName)) {
            return null;
        }

        Client client = new Client();
        client.setDocumentType(documentType);
        client.setDocumentNumber(documentNumber);
        client.setFullName(String.format("%s, %s", filter(lastName), filter(firstName)));
        client.setNumberPhone(numberPhone);
        client.setEmail(email);
        client.setSex(sex);
        client.setBirthdate(birthdate);
        client.setAddress(address);
        client.setDistrict(district);
        client.setDateJoined(dateJoined);
        client.setOrigin(origin);
        client.setStatus(status);
        client.setCondition(condition);
        return client;
    }


    public boolean save(CClient client) {
        if (client != null) {
            if(isValidClient()){
                return client.save();
            }
            return false;
        }
        return false;
    }

    public static CClient get(int op, String documentNumber) {

        if (op == 1)
            if (isValidDocument("DNI", documentNumber)) {
                return CClient.Query.get(documentNumber);
            }

        if (op == 2)
            if (isValidDocument("RUC", documentNumber)) {
                return CClient.Query.get(documentNumber);
            }

        return null;
    }

    public static CClient get(int idCClient) {
        return Query.get(idCClient);
    }

    public static List<CClient> getList(boolean isDelete) {
        return Query.getList(isDelete);
    }

    public static List<CClient> search(String values) {
        return Query.search(values);
    }

    private static boolean isValidCClient(String documentType, String documentNumber, String fullName, String numberPhone, String email, String address) {
        if (documentExist(documentNumber))
            return false;

        if (!isValidDocument(documentType, documentNumber))
            return false;

        if (!isValidFullName(fullName))
            return false;

        if (!isValidPhoneNumber(numberPhone))
            return false;

        if (!isValidEmail(email))
            return false;

        return isValidAddress(address);

    }

    private boolean isValidClient() {
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

        return true;

    }


}
