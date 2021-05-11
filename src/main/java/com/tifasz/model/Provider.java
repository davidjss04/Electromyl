package com.tifasz.model;

import com.tifasz.controller.CProvider;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Provider extends People {
    static ConnectionDB connectionDB = new ConnectionDB();

    private String description;

    public Provider() {
        super();
        this.description = "";
    }

    public Provider(String description) {
        this.description = description;
    }

    public Provider(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, String description) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected boolean save() {
        try {
            if (connectionDB.openConnection()) {
                return false;
            }

            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUProvider(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            extracted(connectionDB);
            connectionDB.query.setString(13, getDescription());
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
        return "Provider{" +
                "description='" + description + '\'' +
                "} " + super.toString() + '\n';
    }

    public static class Query {

        @org.jetbrains.annotations.Contract
        private static CProvider insertAttributes(@NotNull CProvider provider) throws Exception {
            People.insertAttributes(provider, connectionDB);
            provider.setDescription(connectionDB.result.getString(13));
            return provider;
        }

        @NotNull
        private static List<CProvider> getCProviders() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<CProvider> providers = new ArrayList<>();
            while (connectionDB.result.next()) {
                CProvider provider = insertAttributes(new CProvider());
                providers.add(provider);
            }
            return providers;
        }

        @Nullable
        @Contract(pure = true)
        public static CProvider get(String documentNumber) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT people.id_people,\n" +
                        "\tpeople.document_type,\n" +
                        "\tpeople.document_number,\n" +
                        "\tpeople.full_name,\n" +
                        "\tpeople.number_phone,\n" +
                        "\tpeople.email,\n" +
                        "\tpeople.sex,\n" +
                        "\tpeople.birthdate,\n" +
                        "\tpeople.address,\n" +
                        "\tpeople.id_district,\n" +
                        "\tpeople.is_delete,\n" +
                        "\tpeople.date_joined,\n" +
                        "\tprovider.description\n" +
                        "FROM people\n" +
                        "INNER JOIN provider\n" +
                        "ON people.id_people = provider.id_provider\n" +
                        "WHERE people.document_number = ?");
                connectionDB.query.setString(1, documentNumber);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new CProvider());
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
        public static CProvider get(int idCProvider) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT people.id_people,\n" +
                        "\tpeople.document_type,\n" +
                        "\tpeople.document_number,\n" +
                        "\tpeople.full_name,\n" +
                        "\tpeople.number_phone,\n" +
                        "\tpeople.email,\n" +
                        "\tpeople.sex,\n" +
                        "\tpeople.birthdate,\n" +
                        "\tpeople.address,\n" +
                        "\tpeople.id_district,\n" +
                        "\tpeople.is_delete,\n" +
                        "\tpeople.date_joined,\n" +
                        "\tprovider.description\n" +
                        "FROM people\n" +
                        "INNER JOIN provider\n" +
                        "ON people.id_people = provider.id_provider\n" +
                        "WHERE people.id_people = ?");
                connectionDB.query.setInt(1, idCProvider);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new CProvider());
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
        public static List<CProvider> getList(boolean isDelete) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT people.id_people,\n" +
                        "\tpeople.document_type,\n" +
                        "\tpeople.document_number,\n" +
                        "\tpeople.full_name,\n" +
                        "\tpeople.number_phone,\n" +
                        "\tpeople.email,\n" +
                        "\tpeople.sex,\n" +
                        "\tpeople.birthdate,\n" +
                        "\tpeople.address,\n" +
                        "\tpeople.id_district,\n" +
                        "\tpeople.is_delete,\n" +
                        "\tpeople.date_joined,\n" +
                        "\tprovider.description\n" +
                        "FROM people\n" +
                        "INNER JOIN provider\n" +
                        "ON people.id_people = provider.id_provider\n" +
                        "WHERE people.is_delete = ?");
                connectionDB.query.setBoolean(1, isDelete);
                return getCProviders();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<CProvider> search(String values) {
            try {
                if (connectionDB.openConnection()) {
                    return null;
                }

                connectionDB.query = connectionDB.connection.prepareStatement("SELECT people.id_people,\n" +
                        "\tpeople.document_type,\n" +
                        "\tpeople.document_number,\n" +
                        "\tpeople.full_name,\n" +
                        "\tpeople.number_phone,\n" +
                        "\tpeople.email,\n" +
                        "\tpeople.sex,\n" +
                        "\tpeople.birthdate,\n" +
                        "\tpeople.address,\n" +
                        "\tpeople.id_district,\n" +
                        "\tpeople.is_delete,\n" +
                        "\tpeople.date_joined,\n" +
                        "\tprovider.description\n" +
                        "FROM people\n" +
                        "INNER JOIN provider\n" +
                        "ON people.id_people = provider.id_provider\n" +
                        "WHERE MATCH (people.document_number,\n" +
                        "\tpeople.full_name) AGAINST ('" + values + "*' IN BOOLEAN MODE)");
                return getCProviders();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }
    }
}
