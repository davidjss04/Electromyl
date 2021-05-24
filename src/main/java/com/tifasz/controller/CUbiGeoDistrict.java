package com.tifasz.controller;

import com.tifasz.model.UbiGeoDistrict;

import java.util.List;

public class CUbiGeoDistrict extends UbiGeoDistrict {
    public CUbiGeoDistrict(String idDistrict, String nameDistrict, CUbiGeoProvince province) {
        super(idDistrict, nameDistrict, province);
    }

    public static CUbiGeoDistrict get(String idDistrict){
        return Query.get(idDistrict);
    }

    public static CUbiGeoDistrict get(CUbiGeoProvince province, String idDistrict){
        return Query.get(province, idDistrict);
    }

    public static List<CUbiGeoDistrict> getListOf(CUbiGeoProvince province){
        return Query.getListOf(province);
    }

    public static CUbiGeoDistrict search(){
        return null;
    }

}
