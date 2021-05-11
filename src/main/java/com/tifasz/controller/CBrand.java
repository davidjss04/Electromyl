package com.tifasz.controller;

import com.tifasz.model.Brand;

import java.util.List;
import static com.tifasz.solution.Validation.*;

public class CBrand extends Brand {
    public CBrand() {
        super();
    }

    public CBrand(int idBrand, String name) {
        super(idBrand, name);
    }

    public static CBrand newBrand(int idBrand, String name){
        CBrand brand = new CBrand();
        brand.setIdBrand(idBrand);
        brand.setName(name);
        return brand;
    }

    @Override
    public boolean save() {
        if(getClass() != null){
            if(isValidBrand()){
                return super.save();
            }
            return false;
        }
        return false;
    }

    private boolean isValidBrand() {
        if(!isValidTitle(getName()))
            return false;

        return true;
    }

    public static CBrand get(int idBrand){
        return Query.get(idBrand);
    }

    public static List<CBrand> getList(){
        return Query.getList();
    }

    public static List<CBrand> search(String values){
        return Query.search(values);
    }
}
