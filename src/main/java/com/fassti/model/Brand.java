package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Brand implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idBrand;
    private String name;

    public Brand() {
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

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "idBrand=" + idBrand +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Query {

        @NotNull
        @org.jetbrains.annotations.Contract
        private static Brand insertAttributes(@NotNull Brand brand) throws Exception {
            return brand;
        }

        @NotNull
        private static List<Brand> getBrands() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Brand> brands = new ArrayList<>();
            while (connectionDB.result.next()) {
                Brand brand = insertAttributes(new Brand());
                brands.add(brand);
            }
            return brands;
        }

        @Nullable
        @Contract(pure = true)
        public static Brand get(int idBrand) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Brand> getList(boolean isDelete) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Brand> search(String values) {
            return null;
        }
    }
}
