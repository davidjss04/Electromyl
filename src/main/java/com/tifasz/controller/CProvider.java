package com.tifasz.controller;

import com.tifasz.model.Provider;
import com.tifasz.model.UbiGeoDistrict;

import java.sql.Date;
import java.util.List;

import static com.tifasz.solution.Validation.*;

public class CProvider extends Provider {

    public CProvider() {
    }

    public CProvider(String description) {
        super(description);
    }

    public CProvider(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, String description) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined, description);
    }

    @Override
    protected boolean save() {
        return super.save();
    }

    //Se realizaron dos metodos, con la alternativa para que que en uno se puedo ingresar el nombre completo y en el otro por detalle
    public static CProvider newProvider(String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex,
                                       Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, String description) {

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

    public boolean save(Provider Provider) {

        if (Provider != null) {
            if(isValidProvider()){
                return super.save();
            }
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
        return Query.search(values);
    }

    private boolean isValidProvider() {

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
