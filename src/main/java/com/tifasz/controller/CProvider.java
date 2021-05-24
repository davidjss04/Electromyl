package com.tifasz.controller;

import com.tifasz.model.Provider;
import com.tifasz.model.UbiGeoDistrict;

import java.sql.Date;
import java.util.List;

import static com.tifasz.solution.Validation.*;

public class CProvider extends Provider {

    public CProvider() {
        super();
    }

    public CProvider(String description) {
        super(description);
    }

    public CProvider(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, String description) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined, description);
    }

    //Se realizaron dos metodos, con la alternativa para que que en uno se puedo ingresar el nombre completo y en el otro por detalle
    public static CProvider newProvider(String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex,
                                       Date birthdate, String address, CUbiGeoDistrict district, Date dateJoined, String description) {

        if (isRepeatProvider(documentNumber)) {
            return null;
        }

        CProvider provider = new CProvider();
        provider.setDocumentType(documentType);
        provider.setDocumentNumber(documentNumber);
        provider.setFullName(fullName);
        provider.setNumberPhone(numberPhone);
        provider.setEmail(email);
        provider.setSex(sex);
        provider.setBirthdate(birthdate);
        provider.setAddress(address);
        provider.setDistrict(district);
        provider.setDateJoined(dateJoined);
        provider.setDescription(description);
        return provider;
    }
    public static CProvider newProvider(String documentType, String documentNumber, String firstName, String lastName, String numberPhone, String email, byte sex,
                                        Date birthdate, String address, CUbiGeoDistrict district, Date dateJoined, String description) {


        if (isRepeatProvider(documentNumber)) {
            return null;
        }

        if (!isValidFirstName(firstName) && !isValidLastName(lastName)) {
            return null;
        }

        CProvider provider = new CProvider();
        provider.setDocumentType(documentType);
        provider.setDocumentNumber(documentNumber);
        provider.setFullName(String.format("%s, %s", filter(lastName),filter(firstName)));
        provider.setNumberPhone(numberPhone);
        provider.setEmail(email);
        provider.setSex(sex);
        provider.setBirthdate(birthdate);
        provider.setAddress(address);
        provider.setDistrict(district);
        provider.setDateJoined(dateJoined);
        provider.setDescription(description);
        return provider;
    }

    @Override
    public boolean save() {
        if (isValidProvider()){
            filterProvider();
            return super.save();
        }
        return false;
    }

    public static CProvider get(int op, String documentNumber) {

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

    public static CProvider get(int idProvider) {
        return Query.get(idProvider);
    }

    public static List<CProvider> getList(boolean isDelete) {
        return Query.getList(isDelete);
    }

    public static List<CProvider> search(String values) {
        if (isEmpty(values)) {
            return null;
        }
        return Query.search(values);
    }


    private void filterProvider() {
        this.setFullName(filter(getFullName()));
        this.setNumberPhone(filter(getNumberPhone()));
        this.setEmail(filter(getEmail()));
        this.setSex(getSex());
        this.setBirthdate(getBirthdate());
        this.setAddress(filter(getAddress()));
        this.setDistrict(getDistrict());
        this.setDateJoined(getDateJoined());
        this.setDescription(filter(getDescription()));
    }

    private static boolean isRepeatProvider(String documentNumber){
        if (documentExist(documentNumber)){
            System.out.println("1");
            return true;
        }

        return false;
    }

    private boolean isValidProvider() {

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
