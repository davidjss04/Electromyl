package com.tifasz.controller;

import com.tifasz.model.Brand;
import com.tifasz.model.Price;
import com.tifasz.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.tifasz.solution.Validation.filter;
import static com.tifasz.solution.Validation.isValidTitle;

public class CProduct extends Product {
    public CProduct() {
        super();
    }

    public CProduct(int idProduct, String productCode, String description, double stock, CCategory category, Brand brand, List<Price> prices, boolean idDelete) {
        super(idProduct, productCode, description, stock, category, brand, prices, idDelete);
    }
    
    @Override
    public boolean save() {
        if (isValidProduct()) {
            filterProduct();
            return super.save();
        }
        return false;
    }

    public static CProduct newProduct(){
        return null;
    }

    public static CProduct get(int idProduct){
        return Query.get(idProduct);
    }

    public static List<CProduct> getList(boolean isDelete){
        return Query.getList(isDelete);
    }

    public static List<CProduct> search(String values){
        return Query.search(values);
    }

    public static List<Price> getPrices(List<CPrice> prices){
        List<Price> priceList = new ArrayList<>();
        priceList.addAll(prices);
        return priceList;
    }
    
    public void filterProduct(){
        this.setIdProduct(getIdProduct());
        this.setProductCode(getProductCode());
        this.setDescription(filter(getDescription()));
        this.setStock(getStock());
        this.setCategory(getCategory());
        this.setBrand(getBrand());
        this.setPrices(getPrices());
        this.setDelete(isDelete());
    }

    private boolean isValidProduct(){
        if (!isValidTitle(getDescription()))
            return false;

/*        if (isValidCode(getProductCode())){
            return false;
        }*/

        return true;
    }

}
