package com.tifasz.controller;

import com.tifasz.model.Unit;

import java.util.List;

public class CUnit extends Unit {
    public CUnit() {
        super();
    }

    public CUnit(int idUnit, String name, String abbreviation, double quality, boolean isDelete) {
        super(idUnit, name, abbreviation, quality, isDelete);
    }

    public static CUnit newUnit(String name, String abbreviation, double quality) {
        CUnit unit = new CUnit();
        unit.setName(name);
        unit.setAbbreviation(abbreviation);
        unit.setQuality(quality);
        return unit;
    }

    public static CUnit get(int idUnit) {
        return Query.get(idUnit);
    }

    public static List<CUnit> getList(boolean isDelete) {
        return Query.getList(isDelete);
    }

    public static List<CUnit> search(String values) {
        return Query.search(values);
    }

    @Override
    public boolean save() {
        return super.save();
    }
}
