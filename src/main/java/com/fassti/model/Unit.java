package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Unit implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idUnit;
    private String name;
    private String abbreviation;
    private double quality;
    private boolean isDelete;

    public Unit() {
        this.idUnit = 0;
        this.name = "";
        this.abbreviation = "";
        this.quality = 0D;
        this.isDelete = false;

    }

    public Unit(int idUnit, String name, String abbreviation, double quality, boolean isDelete) {
        this.idUnit = idUnit;
        this.name = name;
        this.abbreviation = abbreviation;
        this.quality = quality;
        this.isDelete = isDelete;
    }

    public int getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "idUnit=" + idUnit +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", quality=" + quality +
                ", isDelete=" + isDelete +
                '}';
    }

    public static class Query {
        @NotNull
        @org.jetbrains.annotations.Contract
        private static Unit insertAttributes(@NotNull Unit unit) throws Exception {
            return unit;
        }

        @NotNull
        private static List<Unit> getUnits() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Unit> units = new ArrayList<>();
            while (connectionDB.result.next()) {
                Unit unit = insertAttributes(new Unit());
                units.add(unit);
            }
            return units;
        }

        @Nullable
        @Contract(pure = true)
        public static Unit get(int idUnit) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Unit> getList(boolean isDelete) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Unit> search(String values) {
            return null;
        }
    }
}
