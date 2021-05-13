package com.tifasz.controller;

import com.tifasz.model.PaymentMethod;

import java.util.List;

public class CPaymentMethod extends PaymentMethod {

    public CPaymentMethod() {
    }

    public CPaymentMethod(int idPaymentMethod, String name) {
        super(idPaymentMethod, name);
    }

    public static CPaymentMethod newPaymentMethod(String name) {
        CPaymentMethod paymentMethod = new CPaymentMethod();
        paymentMethod.setName(name);
        return paymentMethod;
    }

    public static CPaymentMethod get(int idPaymentMethod) {
        return Query.get(idPaymentMethod);
    }

    public static List<CPaymentMethod> getList() {
        return Query.getList();
    }

    @Override
    public boolean save() {
        return super.save();
    }

}
