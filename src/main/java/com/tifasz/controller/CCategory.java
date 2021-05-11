package com.tifasz.controller;

import com.tifasz.model.Category;

import java.util.List;

import static com.tifasz.solution.Validation.isValidTitle;

public class CCategory extends Category {

    public CCategory() {
    }

    public CCategory(int idCategory, String name) {
        super(idCategory, name);
    }

    public static CCategory newCategory(String name) {
        CCategory ccategory = new CCategory();
        ccategory.setName(name);
        return ccategory;
    }

    public static CCategory get(int idCategory) {
        return Query.get(idCategory);
    }

    public static List<CCategory> getList() {
        return Query.getList();
    }

    public static List<CCategory> search(String values) {
        return Query.search(values);
    }

    @Override
    public boolean save() {
        if (this.getClass() != null) {
            if (isValidCategory()) {
                return super.save();
            }
            return false;
        }
        return false;
    }

    private boolean isValidCategory() {
        if (nameRepeat(getName()))
            return false;

        return isValidTitle(getName());
    }

    private boolean nameRepeat(String name) {
        return Query.existName(name);
    }

}
