package com.tifasz.model;

import com.tifasz.controller.CUbiGeoDistrict;
import com.tifasz.controller.CUbiGeoProvince;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbiGeoDistrict {
    static ConnectionDB connectionDB = new ConnectionDB();

    private final String idDistrict;
    private final String nameDistrict;
    private final UbiGeoProvince province;

    public UbiGeoDistrict(String idDistrict, String nameDistrict, UbiGeoProvince province) {
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

    public static class Query{

        @Nullable
        @Contract(pure = true)
        public static CUbiGeoDistrict get(String idDistrict) {
            if (connectionDB.openConnection()) {
                return null;
            }

            try {
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_district, name_district, id_province from district WHERE id_district = ?");
                connectionDB.query.setString(1, idDistrict);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    CUbiGeoDistrict ubiGeoDistrict = new CUbiGeoDistrict(
                            connectionDB.result.getString(1),
                            connectionDB.result.getString(2),
                            CUbiGeoProvince.get(connectionDB.result.getString(3))

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

        @Nullable
        public static CUbiGeoDistrict get(UbiGeoProvince province, String idDistrict) {
            if (connectionDB.openConnection()) {
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
                if (connectionDB.result.next()) {
                    CUbiGeoDistrict ubiGeoDistrict = new CUbiGeoDistrict(
                            connectionDB.result.getString(1),
                            connectionDB.result.getString(2),
                            (CUbiGeoProvince) province
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

        @Nullable
        public static List<CUbiGeoDistrict> getListOf(UbiGeoProvince province) {
            if (connectionDB.openConnection()) {
                return null;
            }

            try {
                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_district , name_district FROM district WHERE id_province = ?");
                connectionDB.query.setString(1, province.getIdProvince());
                connectionDB.result = connectionDB.query.executeQuery();
                List<CUbiGeoDistrict> ubiGeoDistrictList = new ArrayList<>();
                while (connectionDB.result.next()) {
                    CUbiGeoDistrict ubiGeoDistrict = new CUbiGeoDistrict(
                            connectionDB.result.getString(1),
                            connectionDB.result.getString(2),
                            (CUbiGeoProvince) province
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
    }

    @Override
    public String toString() {
        return "UbiGeoDistrict{" +
                "idDistrict='" + idDistrict + '\'' +
                ", nameDistrict='" + nameDistrict + '\'' +
                '}'+'\n';
    }
}
