package com.tifasz.model;

import com.tifasz.controller.CCategory;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Category {
    static ConnectionDB connectionDB = new ConnectionDB();

    private int idCategory;
    private String name;

    public Category() {
        this.idCategory = 0;
        this.name = "OTROS";
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

    protected boolean save() {
        try {
            if (connectionDB.openConnection()) {
                return false;
            }

            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUCategory(?,?,1)");
            connectionDB.query.setInt(1, getIdCategory());
            connectionDB.query.setString(2, getName());
            connectionDB.result = connectionDB.query.executeQuery();

            if (!connectionDB.result.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                '}' + '\n';
    }

    public static class Query {

        @NotNull
        @org.jetbrains.annotations.Contract
        private static CCategory insertAttributes(@NotNull CCategory category) throws Exception {
            category.setIdCategory(connectionDB.result.getInt(1));
            category.setName(connectionDB.result.getString(2));
            return category;
        }

        @NotNull
        private static List<CCategory> getCategories() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<CCategory> categories = new ArrayList<>();
            while (connectionDB.result.next()) {
                CCategory category = insertAttributes(new CCategory());
                categories.add(category);
            }
            return categories;
        }

        @Nullable
        @Contract(pure = true)
        public static CCategory get(int idCategory) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_category, name FROM category WHERE id_category = ?");
                connectionDB.query.setInt(1, idCategory);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new CCategory());
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<CCategory> getList() {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT id_category, name FROM category");
                return getCategories();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        public static boolean existName(String name) {
            try {
                if (connectionDB.openConnection()) {
                    return false;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT name FROM category WHERE name = ?");
                connectionDB.query.setString(1, name);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return false;
        }

        @Nullable
        @Contract(pure = true)
        public static List<CCategory> search(String values) {
            return null;
        }
    }
}

