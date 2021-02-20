package com.fassti.model;

import com.fassti.solution.ConnectionDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbiGeoDistrict {
    static ConnectionDB connectionDB = new ConnectionDB();

    private final String idDistrict;
    private final String nameDistrict;
    private final UbiGeoProvince province;

    private UbiGeoDistrict(String idDistrict, String nameDistrict, UbiGeoProvince province) {
        this.idDistrict = idDistrict;
        this.nameDistrict = nameDistrict;
        this.province = province;
    }

    public String getIdDistrict() {
        return idDistrict;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public UbiGeoProvince getProvince() {
        return province;
    }

    public static UbiGeoDistrict get(UbiGeoProvince province, String idDistrict){
        if (!connectionDB.openConnection()) {
            return null;
        }

        try {
            connectionDB.query = connectionDB.connection.prepareStatement("SELECT district.id_district,\n" +
                    "\tdistrict.name_district \n" +
                    "FROM district\n" +
                    "INNER JOIN province \n" +
                    "ON province.id_province = district.id_province \n" +
                    "WHERE district.id_province = ? \n" +
                    "AND district.id_district = ?");
            connectionDB.query.setString(1, province.getIdProvince());
            connectionDB.query.setString(2, idDistrict);
            connectionDB.result = connectionDB.query.executeQuery();
            if (connectionDB.result.next()){
                UbiGeoDistrict ubiGeoDistrict = new UbiGeoDistrict(
                        connectionDB.result.getString(1),
                        connectionDB.result.getString(2),
                        province
                );
                return ubiGeoDistrict;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }

        return null;
    }

    public static List<UbiGeoDistrict> listOf(UbiGeoProvince Province){
        if (!connectionDB.openConnection()) {
            return null;
        }

        try {
            connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_district , name_district FROM district WHERE id_province = ?");
            connectionDB.query.setString(1,Province.getIdProvince());
            connectionDB.result = connectionDB.query.executeQuery();
            List<UbiGeoDistrict> ubiGeoDistrictList = new ArrayList<>();
            while (connectionDB.result.next()){
                UbiGeoDistrict ubiGeoDistrict = new UbiGeoDistrict(
                        connectionDB.result.getString(1),
                        connectionDB.result.getString(2),
                        Province
                );
                ubiGeoDistrictList.add(ubiGeoDistrict);
            }
            return ubiGeoDistrictList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }

        return null;
    }

    @Override
    public String toString() {
        return "UbiGeoDistrict{" +
                "idDistrict='" + idDistrict + '\'' +
                ", nameDistrict='" + nameDistrict + '\'' +
                '}'+ '\n';
    }
}
