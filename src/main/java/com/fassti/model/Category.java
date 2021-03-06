package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Category implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idCategory;
    private String name;

    public Category() {
        this.idCategory = 1;
        this.name = "CATEGORY EXAMPLE JAVA";
    }

    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Query {

        @NotNull
        @org.jetbrains.annotations.Contract
        private static Category insertAttributes(@NotNull Category category) throws Exception {
            return category;
        }

        @NotNull
        private static List<Category> getCategories() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (connectionDB.result.next()) {
                Category category = insertAttributes(new Category());
                categories.add(category);
            }
            return categories;
        }

        @Nullable
        @Contract(pure = true)
        public static Category get(int idCategory) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Category> getList(boolean isDelete) {
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Category> search(String values) {
            return null;
        }

    }
}

