package com.tifasz.model;

import com.tifasz.controller.CUbiGeoDepartment;
import com.tifasz.controller.CUbiGeoProvince;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbiGeoProvince {
    static ConnectionDB connectionDB = new ConnectionDB();

    private final String idProvince;
    private final String nameProvince;
    private final UbiGeoDepartment department;

    public UbiGeoProvince(String idProvince, String nameProvince, UbiGeoDepartment department) {
        this.idProvince = idProvince;
        this.nameProvince = nameProvince;
        this.department = department;
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

    public static class Query{

        @Nullable
        public static CUbiGeoProvince get(String idProvince) {
            if (connectionDB.openConnection()) {
                return null;
            }

            try {
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_province, name_province, id_department FROM province WHERE id_province = ?");
                connectionDB.query.setString(1, idProvince);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    CUbiGeoProvince ubiGeoProvince = new CUbiGeoProvince(
                            connectionDB.result.getString(1),
                            connectionDB.result.getString(2),
                            CUbiGeoDepartment.get(connectionDB.result.getString(3))
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
        public static CUbiGeoProvince get(UbiGeoDepartment department, String idProvince) {
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
                    CUbiGeoProvince ubiGeoProvince = new CUbiGeoProvince(
                            connectionDB.result.getString(1),
                            connectionDB.result.getString(2),
                            (CUbiGeoDepartment) department
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
        public static List<CUbiGeoProvince> getListOf(UbiGeoDepartment department) {
            if (connectionDB.openConnection()) {
                return null;
            }

            try {
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_province , name_province FROM province WHERE id_department = ?");
                connectionDB.query.setString(1, department.getIdDepartment());
                connectionDB.result = connectionDB.query.executeQuery();
                List<CUbiGeoProvince> ubiGeoProvinceList = new ArrayList<>();
                while (connectionDB.result.next()) {
                    CUbiGeoProvince ubiGeoProvince = new CUbiGeoProvince(
                            connectionDB.result.getString(1),
                            connectionDB.result.getString(2),
                            (CUbiGeoDepartment) department
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

    }

    @Override
    public String toString() {
        return "UbiGeoProvince{" +
                "idProvince='" + idProvince + '\'' +
                ", nameProvince='" + nameProvince + '\'' +
                '}' + '\n';
    }
}
