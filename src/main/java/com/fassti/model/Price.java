package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Price implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idPrice;
    private double value;
    private Unit unit;

    public Price() {
        this.idPrice = 0;
        this.value = 0d;
        this.unit = new Unit();

    }

    public Price(int idPrice, double value, Unit unit) {
        this.idPrice = idPrice;
        this.value = value;
        this.unit = unit;
    }

    public int getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(int idPrice) {
        this.idPrice = idPrice;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }

    public static class Query {
        @NotNull
        @org.jetbrains.annotations.Contract
        private static Price insertAttributes(@NotNull Price price) throws Exception {
            return price;
        }

        @NotNull
        private static List<Price> getPrices() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Price> prices = new ArrayList<>();
            while (connectionDB.result.next()) {
                Price price = insertAttributes(new Price());
                prices.add(price);
            }
            return prices;
        }

        @Nullable
        @Contract(pure = true)
        public static Price get(int idPrice) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Price> getList(boolean isDelete) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Price> search(String values) {
            return null;
        }
    }
}
