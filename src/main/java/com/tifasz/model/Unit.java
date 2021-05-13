package com.tifasz.model;

import com.tifasz.controller.CUnit;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Unit{
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

    protected boolean save() {
        try {
            if (connectionDB.openConnection()) {
                return false;
            }

            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUUnit(?,?,?,?,?)");
            connectionDB.query.setInt(1, getIdUnit());
            connectionDB.query.setString(2, getName());
            connectionDB.query.setString(3, getAbbreviation());
            connectionDB.query.setDouble(4, getQuality());
            connectionDB.query.setBoolean(5, isDelete());
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
        private static CUnit insertAttributes(@NotNull CUnit unit) throws Exception {
            unit.setIdUnit(connectionDB.result.getInt(1));
            unit.setName(connectionDB.result.getString(2));
            unit.setAbbreviation(connectionDB.result.getString(3));
            unit.setQuality(connectionDB.result.getDouble(4));
            unit.setDelete(connectionDB.result.getBoolean(5));
            return unit;
        }

        @NotNull
        private static List<CUnit> getCUnits() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<CUnit> units = new ArrayList<>();
            while (connectionDB.result.next()) {
                CUnit unit = insertAttributes(new CUnit());
                units.add(unit);
            }
            return units;
        }

        @Nullable
        @Contract(pure = true)
        public static CUnit get(int idCUnit) {

            try {
                if (connectionDB.openConnection()) {
                    return null;
                }
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_unit, name, abbreviation, quantity, is_delete FROM  unit WHERE id_unit = ?");
                connectionDB.query.setInt(1, idCUnit);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new CUnit());
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
        public static List<CUnit> getList(boolean isDelete) {

            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_unit, name, abbreviation, quantity, is_delete FROM  unit WHERE is_delete = ?");
                connectionDB.query.setBoolean(1, isDelete);
                return getCUnits();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<CUnit> search(String values) {
            //Analyzing the importance in this class
            return null;
        }
    }

}
