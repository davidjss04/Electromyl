package com.tifasz.model;


import com.tifasz.controller.CBrand;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Brand {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idBrand;
    private String name;

    public Brand() {
        //idBrand in table DB isn't auto increment
        this.idBrand = 0;
        this.name = "";
    }

    public Brand(int idBrand, String name) {
        this.idBrand = idBrand;
        this.name = name;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean save() {
        try {
            if (connectionDB.openConnection()) {
                return false;
            }

            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUBrand(?,?)");
            connectionDB.query.setInt(1, getIdBrand());
            connectionDB.query.setString(2, getName());
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
        return "Brand{" +
                "idBrand=" + idBrand +
                ", name='" + name + '\'' +
                '}' + '\n';
    }

    public static class Query {

        @NotNull
        @org.jetbrains.annotations.Contract
        private static CBrand insertAttributes(@NotNull CBrand brand) throws Exception {
            brand.setIdBrand(connectionDB.result.getInt(1));
            brand.setName(connectionDB.result.getString(2));
            return brand;
        }

        @NotNull
        private static List<CBrand> getCBrands() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<CBrand> brands = new ArrayList<>();
            while (connectionDB.result.next()) {
                CBrand brand = insertAttributes(new CBrand());
                brands.add(brand);
            }
            return brands;
        }

        @Nullable
        @Contract(pure = true)
        public static CBrand get(int idCBrand) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_brand, name FROM brand WHERE id_brand = ?");
                connectionDB.query.setInt(1, idCBrand);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new CBrand());
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
        public static List<CBrand> getList() {

            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_brand, name FROM brand");
                return getCBrands();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<CBrand> search(String values) {
            //Analyzing the importance in this class
            return null;
        }
    }
}
