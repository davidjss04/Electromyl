package com.tifasz.model;

import com.tifasz.controller.CUbiGeoDepartment;
import com.tifasz.solution.ConnectionDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbiGeoDepartment {
    static ConnectionDB connectionDB = new ConnectionDB();

    private final String idDepartment;
    private final String nameDepartment;

    public UbiGeoDepartment(String idDepartment, String nameDepartment) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public static class Query{
        public static CUbiGeoDepartment get(String idDepartment) {
            if (connectionDB.openConnection()) {
                return null;
            }

            try {
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_department, name_department FROM department WHERE id_department = ?");
                connectionDB.query.setString(1, idDepartment);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    CUbiGeoDepartment ubiGeoDepartment = new CUbiGeoDepartment(
                            connectionDB.result.getString(1),
                            connectionDB.result.getString(2)
                    );
                    return ubiGeoDepartment;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }

            return null;
        }

        public static List<CUbiGeoDepartment> getList() {
            if (connectionDB.openConnection()) {
                return null;
            }

            try {
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_department, name_department FROM department");
                connectionDB.result = connectionDB.query.executeQuery();
                List<CUbiGeoDepartment> ubiGeoDepartmentList = new ArrayList<>();
                while (connectionDB.result.next()) {
                    CUbiGeoDepartment ubiGeoDepartment = new CUbiGeoDepartment(
                            connectionDB.result.getString(1),
                            connectionDB.result.getString(2)
                    );
                    ubiGeoDepartmentList.add(ubiGeoDepartment);
                }
                return ubiGeoDepartmentList;
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
        return "UbiGeoDepartment{" +
                "idDepartment='" + idDepartment + '\'' +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}' + '\n';
    }

}


