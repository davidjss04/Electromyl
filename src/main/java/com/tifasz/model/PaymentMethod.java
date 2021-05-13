package com.tifasz.model;

import com.tifasz.controller.CPaymentMethod;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethod{
    static ConnectionDB connectionDB = new ConnectionDB();

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


    protected boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "idPaymentMethod=" + idPaymentMethod +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Query {

        @NotNull
        private static List<CPaymentMethod> getCPaymentMethods() {
            List<CPaymentMethod> paymentMethods = new ArrayList<>();
            paymentMethods.add(new CPaymentMethod(1, "EFECTIVO"));
            paymentMethods.add(new CPaymentMethod(2, "CREDITO"));
            return paymentMethods;
        }

        @Nullable
        @Contract(pure = true)
        public static CPaymentMethod get(int idCPaymentMethod) {
            for (CPaymentMethod paymentMethod : getCPaymentMethods()) {
                if (paymentMethod.getIdPaymentMethod() == idCPaymentMethod) {
                    return paymentMethod;
                }
            }
            return null;

        }

        @Contract(pure = true)
        public static List<CPaymentMethod> getList() {
            return getCPaymentMethods();
        }

    }
}
