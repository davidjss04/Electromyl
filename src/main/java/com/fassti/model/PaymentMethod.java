package com.fassti.model;

import com.fassti.solution.IModel;

public class PaymentMethod implements IModel {
    private int idPaymentMethod;
    private String name;

    public PaymentMethod() {
        this.idPaymentMethod = 0;
        this.name = "";
    }

    public PaymentMethod(int idPaymentMethod, String name) {
        this.idPaymentMethod = idPaymentMethod;
        this.name = name;
    }

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "idPaymentMethod=" + idPaymentMethod +
                ", name='" + name + '\'' +
                '}';
    }
}
