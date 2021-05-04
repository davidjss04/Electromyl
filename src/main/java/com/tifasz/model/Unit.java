package com.tifasz.model;

import com.tifasz.solution.ConnectionDB;
import com.tifasz.solution.IModel;
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
        private static Unit insertAttributes(@NotNull Unit unit) throws Exception {
            unit.setIdUnit(connectionDB.result.getInt(1));
            unit.setName(connectionDB.result.getString(2));
            unit.setAbbreviation(connectionDB.result.getString(3));
            unit.setQuality(connectionDB.result.getDouble(4));
            unit.setDelete(connectionDB.result.getBoolean(5));
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

            try {
                if (connectionDB.openConnection()) {
                    return null;
                }
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_unit, name, abbreviation, quantity, is_delete FROM  unit WHERE id_unit = ?");
                connectionDB.query.setInt(1, idUnit);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new Unit());
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
        public static List<Unit> getList(boolean isDelete) {

            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_unit, name, abbreviation, quantity, is_delete FROM  unit WHERE is_delete = ?");
                connectionDB.query.setBoolean(1, isDelete);
                return getUnits();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Unit> search(String values) {
            //Analyzing the importance in this class
            return null;
        }
    }

}
