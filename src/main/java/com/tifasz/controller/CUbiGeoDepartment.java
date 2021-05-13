package com.tifasz.controller;

import com.tifasz.model.UbiGeoDepartment;

import java.util.List;

public class CUbiGeoDepartment extends UbiGeoDepartment {
    public CUbiGeoDepartment(String idDepartment, String nameDepartment) {
        super(idDepartment, nameDepartment);
    }

    public static CUbiGeoDepartment get(String idDepartment){
        return Query.get(idDepartment);
    }

    public static List<CUbiGeoDepartment> getList(){
        return Query.getList();
    }

    public static CUbiGeoDepartment search(){
        return null;
    }
}
