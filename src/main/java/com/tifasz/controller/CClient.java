package com.tifasz.controller;

import com.tifasz.model.Client;

import java.sql.Date;
import java.util.List;

import static com.tifasz.solution.Validation.*;

public class CClient extends Client {
    public static CClient newClient(String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex,
                                   Date birthdate, String address, CUbiGeoDistrict district, Date dateJoined, byte origin,
                                   byte status, byte condition) {

        if(isRepeatClient(documentNumber)){
            //Se puede retornar un objeto con parametros vacios...
            return null;
        }

        CClient client = new CClient();
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
                                   byte sex, Date birthdate, String address, CUbiGeoDistrict district, Date dateJoined, byte origin,
                                   byte status, byte condition) {

        if(isRepeatClient(documentNumber)){
            return null;
        }

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


    public boolean save() {
        if(isValidClient()){
            filterClient();
            return super.save();
        }
        return false;
    }

    private void filterClient() {
        this.setFullName(filter(getFullName()));
        this.setNumberPhone(filter(getNumberPhone()));
        this.setEmail(filter(getEmail()));
        this.setSex(getSex());
        this.setBirthdate(getBirthdate());
        this.setAddress(filter(getAddress()));
        this.setDistrict(getDistrict());
        this.setDateJoined(getDateJoined());
        this.setOrigin(getOrigin());
        this.setStatus(getStatus());
        this.setCondition(getCondition());
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
        if (isEmpty(values))
            return null;

        return Query.search(values);
    }

    private static boolean isRepeatClient(String documentNumber){
        if (documentExist(documentNumber)){
            System.out.println("1");
            return true;
        }

        return false;
    }

    private boolean isValidClient() {

        if (!isValidDocument(getDocumentType(), getDocumentNumber())){
            System.out.println("2");
            return true;
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

        return true;
    }


}
