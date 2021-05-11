package com.tifasz.controller;

import com.tifasz.model.UbiGeoDepartment;
import com.tifasz.model.UbiGeoDistrict;
import com.tifasz.model.UbiGeoProvince;

import java.util.List;

public class CUbiGeo {
    public static UbiGeoDepartment getDepartment(String idDepertment) {
        return UbiGeoDepartment.get(idDepertment);
    }

    public static UbiGeoProvince getProvince(String idProvince) {
        return UbiGeoProvince.get(idProvince);
    }

    public static UbiGeoProvince getProvince(UbiGeoDepartment department, String idProvince) {
        return UbiGeoProvince.get(department, idProvince);
    }

    public static UbiGeoDistrict getDistrict(String idDistrict) {
        return UbiGeoDistrict.get(idDistrict);
    }

    public static UbiGeoDistrict getDistrict(UbiGeoProvince province, String idDistrict) {
        return UbiGeoDistrict.get(province, idDistrict);
    }

    //LIst
    public static List<UbiGeoDepartment> getDepartments() {
        return UbiGeoDepartment.list();
    }

    public static List<UbiGeoProvince> getProvincesOf(UbiGeoDepartment department) {
        return UbiGeoProvince.listOf(department);
    }

    public static List<UbiGeoDistrict> getDistrictsOf(UbiGeoProvince province) {
        return UbiGeoDistrict.listOf(province);
    }

}
