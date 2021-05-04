package com.tifasz.model;

import com.tifasz.solution.ConnectionDB;
import com.tifasz.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethod implements IModel {
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

    @Override
    public boolean save() {
        return false;
    }

    public static class Query{

        @NotNull
        private static List<PaymentMethod> getPaymentMethods(){
            List<PaymentMethod> paymentMethods = new ArrayList<>();
            paymentMethods.add(new PaymentMethod(1,"EFECTIVO"));
            paymentMethods.add(new PaymentMethod(2,"CREDITO"));
            return paymentMethods;
        }

        @Nullable
        @Contract(pure = true)
        public static PaymentMethod get(int idPaymentMethod) {
            for (PaymentMethod paymentMethod: getPaymentMethods()) {
                if(paymentMethod.getIdPaymentMethod() == idPaymentMethod){
                    return paymentMethod;
                }
            }
            return null;

        }

        @Contract(pure = true)
        public static List<PaymentMethod> getList(boolean isDelete) {
            return getPaymentMethods();
        }

    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "idPaymentMethod=" + idPaymentMethod +
                ", name='" + name + '\'' +
                '}';
    }
}
