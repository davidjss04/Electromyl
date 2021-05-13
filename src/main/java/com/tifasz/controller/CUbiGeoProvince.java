package com.tifasz.controller;

import com.tifasz.model.UbiGeoDepartment;
import com.tifasz.model.UbiGeoProvince;

import java.util.List;

public class CUbiGeoProvince extends UbiGeoProvince {

    public CUbiGeoProvince(String idProvince, String nameProvince, UbiGeoDepartment department) {
        super(idProvince, nameProvince, department);
    }

    public static CUbiGeoProvince get(String idDistrict){
        return Query.get(idDistrict);
    }

    public static CUbiGeoProvince get(CUbiGeoDepartment department, String idDistrict){
        return Query.get(department, idDistrict);
    }

    public static List<CUbiGeoProvince> getListOf(CUbiGeoDepartment department){
        return Query.getListOf(department);
    }

    public static CUbiGeoProvince search(){
        return null;
    }

}
