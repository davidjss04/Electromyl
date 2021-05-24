package com.tifasz.model;

import com.tifasz.controller.CPrice;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Price {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idPrice;
    private double value;
    private int idProduct;
    private Unit unit;

    public Price() {
        this.idPrice = 0;
        this.value = 0d;
        this.idProduct = 0;
        this.unit = new Unit();
    }

    public Price(int idPrice, double value, int idProduct, Unit unit) {
        this.idPrice = idPrice;
        this.value = value;
        this.idProduct = idProduct;
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

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    protected boolean save() {
        try {
            if (connectionDB.openConnection()) {
                return false;
            }

            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUPrice(?,?,?,?)");
            connectionDB.query.setInt(1, getIdPrice());
            connectionDB.query.setDouble(2, getValue());
            connectionDB.query.setInt(3, getIdProduct());
            connectionDB.query.setInt(4, getUnit().getIdUnit());
            connectionDB.result = connectionDB.query.executeQuery();

            if (!connectionDB.result.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Price{" +
                "idPrice=" + idPrice +
                ", value=" + value +
                ", idProduct=" + idProduct +
                ", unit=" + unit +
                '}' + '\n';
    }

    public static class Query {

        @NotNull
        @org.jetbrains.annotations.Contract
        private static CPrice insertAttributes(@NotNull CPrice price) throws Exception {
            price.setIdPrice(connectionDB.result.getInt(1));
            price.setValue(connectionDB.result.getDouble(2));
            price.setIdProduct(connectionDB.result.getInt(3));
            price.setUnit(Unit.Query.get(connectionDB.result.getInt(4)));
            return price;
        }

        @NotNull
        private static List<CPrice> getCPrices() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<CPrice> prices = new ArrayList<>();
            while (connectionDB.result.next()) {
                CPrice price = insertAttributes(new CPrice());
                prices.add(price);
            }
            return prices;
        }

        @Nullable
        @Contract(pure = true)
        public static CPrice get(int idCPrice) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_price, value, id_product, id_unit FROM price WHERE id_price = ?");
                connectionDB.query.setInt(1, idCPrice);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new CPrice());
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<CPrice> getList(int idProduct) {

            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_price, value, id_product, id_unit FROM price WHERE id_product = ?");
                connectionDB.query.setInt(1, idProduct);

                return getCPrices();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;

        }

        @Nullable
        @Contract(pure = true)
        public static List<CPrice> search(String values) {
            //Analyzing the importance in this class
            return null;
        }

    }
}
