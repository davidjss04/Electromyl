package com.tifasz.solution;

import com.tifasz.model.People;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static Pattern pattern;
    private static Matcher matcher;

/*    public static void Alert(String header){
        MAlert alert = new MAlert(Alert.AlertType.ERROR,"Error",header);
        alert.showAlert();
    }*/

    public static boolean isValidFullName(String fullName) {
        if (isEmpty(fullName)) {
            return false;
        }
        if (fullName.trim().length() <= 40) {
            pattern = Pattern.compile("^[0-9a-záéíóúñA. -ZÁÉÍÓÚÑ. -]{2,100}$");
            matcher = pattern.matcher(fullName.trim());
            if (!matcher.matches()) {
                return matcher.matches();
            }
            return matcher.matches();
        }
        return false;
    }

    public static boolean isValidFirstName(String firstName) {
        if (isEmpty(firstName)) {
            return false;
        }

        if (firstName.trim().length() <= 40) {
            pattern = Pattern.compile("^([A-ZÁÉÍÓÚa-zñáéíóú. -]{3,100})(\\s[A-ZÁÉÍÓÚa-zñáéíóú. -]{3,100})?(\\s[A-ZÁÉÍÓÚa-zñáéíóú. -]{3,100})?(\\s[A-ZÁÉÍÓÚa-zñáéíóú. -]{3,100})?$");
            matcher = pattern.matcher(firstName.trim());
            if (!matcher.matches()) {
                return matcher.matches();
            }
            return matcher.matches();
        }
        return false;
    }

    public static boolean isValidLastName(String lastName) {
        if (isEmpty(lastName)) {
            //Alert("El campo 'Apellidos' se encuentra vacio.");
            return false;
        }
        if (lastName.trim().length() <= 40) {
            pattern = Pattern.compile("^([A-ZÁÉÍÓÚa-zñáéíóú]{3,100})(\\s[A-ZÁÉÍÓÚa-zñáéíóú]{3,100})?$");
            matcher = pattern.matcher(lastName.trim());
            if (!matcher.matches()) {
                return matcher.matches();
            }
            return matcher.matches();
        }
        return false;
    }

    public static boolean documentExist(String document){
        if(People.Query.documentExist(document)){
            return true;
        }
        return false;
    }

    public static boolean isValidDocument(String documentType, String document) {
        if (isEmpty(document)) {
            return false;
        }

        if (documentType == "DNI") {
            pattern = Pattern.compile("^(?=.{8}$)[0-9]+?$");
            matcher = pattern.matcher(document.trim());

            return matcher.matches();
        }

        if (documentType == "RUC") {
            pattern = Pattern.compile("^(?=.{11}$)[0-9]+?$");
            matcher = pattern.matcher(document.trim());
            return matcher.matches();
        }

        return false;
    }

    public static boolean isValidAddress(String address) {
        if (isEmpty(address)) {
            return false;
        }

        pattern = Pattern.compile("^[0-9a-záéíóúñA. -ZÁÉÍÓÚÑ. -]{2,100}$");
        matcher = pattern.matcher(address.trim());
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }

        pattern = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
        matcher = pattern.matcher(email.trim());
        return matcher.matches();
    }

    public static boolean isValidUsername(String username) {
        if (isEmpty(username)) {
            return false;
        }

        pattern = Pattern.compile("^(?=[a-zA-Z0-9._]{3,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
        matcher = pattern.matcher(username.trim());
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        if (isEmpty(password)) {
            return false;
        }

        pattern = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$");
        matcher = pattern.matcher(password.trim());
        return matcher.matches();
    }

    public static boolean isValidTitle(String title) {
        if (isEmpty(title)){
            return false;
        }
        pattern = Pattern.compile("^[0-9a-záéíóúñA-ZÁÉÍÓÚÑ. ]{1,100}$");
        matcher = pattern.matcher(title.trim());
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if(isEmpty(phoneNumber)){
            return false;
        }
        pattern = Pattern.compile("^[+]?[95]{1}([0-9]){8,12}$");
        matcher = pattern.matcher(phoneNumber.trim());
        return matcher.matches();
    }

    public static boolean isValidBrand(String brand) {
        if(isEmpty(brand)){
            return false;
        }

        pattern = Pattern.compile("^[a-záéíóúñA-ZÁÉÍÓÚ]{1,30}$");
        matcher = pattern.matcher(brand.trim());
        return matcher.matches();
    }

    public static boolean isValidModel(String model) {
        if(isEmpty(model)){
            return false;
        }
        pattern = Pattern.compile("^[0-9a-záéíóúñA-ZÁÉÍÓÚ ]{1,30}$");
        matcher = pattern.matcher(model.trim());
        return matcher.matches();
    }

    public static boolean isValidSeries(String series) {
        if(isEmpty(series)){
            return false;
        }
        pattern = Pattern.compile("^[0-9a-zA-Z -]{1,30}$");
        matcher = pattern.matcher(series.trim());
        return matcher.matches();
    }

    public static boolean isEmpty(String value){
        if (value.equals(null) || value.equals("")) {
            return true;
        }
        return false;
    }

    public static String filter(String values){
        if (values.equals(null) || values.equals("")) {
            return "-";
        }
        return values.trim().toUpperCase();
    }
}
