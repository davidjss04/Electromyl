package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbiGeoProvince {
    static ConnectionDB connectionDB = new ConnectionDB();

    private final String idProvince;
    private final String nameProvince;
    private final UbiGeoDepartment department;

    private UbiGeoProvince(String idProvince, String nameProvince, UbiGeoDepartment department) {
        this.idProvince = idProvince;
        this.nameProvince = nameProvince;
        this.department = department;
    }

    @Nullable
    public static UbiGeoProvince get(UbiGeoDepartment department, String idProvince) {
        if (connectionDB.openConnection()) {
            return null;
        }

        try {
            connectionDB.query = connectionDB.connection.prepareStatement("SELECT province .id_province,\n" +
                    "\tprovince.name_province\n" +
                    "FROM province\n" +
                    "INNER JOIN department \n" +
                    "ON province.id_department = department.id_department \n" +
                    "WHERE province.id_department = ?\n" +
                    "AND province.id_province = ?");
            connectionDB.query.setString(1, department.getIdDepartment());
            connectionDB.query.setString(2, idProvince);
            connectionDB.result = connectionDB.query.executeQuery();
            if (connectionDB.result.next()) {
                UbiGeoProvince ubiGeoProvince = new UbiGeoProvince(
                        connectionDB.result.getString(1),
                        connectionDB.result.getString(2),
                        department
                );
                return ubiGeoProvince;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }

        return null;
    }

    @Nullable
    public static List<UbiGeoProvince> listOf(UbiGeoDepartment department) {
        if (connectionDB.openConnection()) {
            return null;
        }

        try {
            connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_province , name_province FROM province WHERE id_department = ?");
            connectionDB.query.setString(1, department.getIdDepartment());
            connectionDB.result = connectionDB.query.executeQuery();
            List<UbiGeoProvince> ubiGeoProvinceList = new ArrayList<>();
            while (connectionDB.result.next()) {
                UbiGeoProvince ubiGeoProvince = new UbiGeoProvince(
                        connectionDB.result.getString(1),
                        connectionDB.result.getString(2),
                        department
                );
                ubiGeoProvinceList.add(ubiGeoProvince);
            }
            return ubiGeoProvinceList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }

        return null;
    }

    public String getIdProvince() {
        return idProvince;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public UbiGeoDepartment getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "UbiGeoProvince{" +
                "idProvince='" + idProvince + '\'' +
                ", nameProvince='" + nameProvince + '\'' +
                '}' + '\n';
    }
}
