package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Provider extends People implements IModel {
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

    @NotNull
    public static Provider newProvider() {
        UbiGeoDistrict district = UbiGeoDistrict.get(
                UbiGeoProvince.get(UbiGeoDepartment.get("01"), "0101"), "010101"
        );
        return newProvider("DNI", "77820691", "JESUS HUARICANCHA", "974408723", "HUARICANCHA2000@GMAIL.COM", (byte) 1, Date.valueOf("2010-02-01"), "Barrio Nuevo", district, Date.valueOf("2010-02-01"), "NuLL");
    }

    @NotNull
    public static Provider newProvider(String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, String description) {
        Provider provider = new Provider();
        provider.setDocumentType(documentType);
        provider.setDocumentNumber(documentNumber);
        provider.setFullName(fullName);
        provider.setNumberPhone(numberPhone);
        provider.setEmail(email);
        provider.setSex(sex);
        provider.setBirthdate(birthdate);
        provider.setAddress(address);
        provider.setDistrict(district);
        provider.setDateJoined(dateJoined);
        provider.setDescription(description);

        return provider;
    }

    @Override
    public People newPeople() {
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean save() {
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
        private static Provider insertAttributes(@NotNull Provider provider) throws Exception {
            People.insertAttributes(provider, connectionDB);
            provider.setDescription(connectionDB.result.getString(13));
            return provider;
        }

        @NotNull
        private static List<Provider> getProviders() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Provider> providers = new ArrayList<>();
            while (connectionDB.result.next()) {
                Provider provider = insertAttributes(new Provider());
                providers.add(provider);
            }
            return providers;
        }

        @Nullable
        @Contract(pure = true)
        public static Provider get(int idProvider) {
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
                connectionDB.query.setInt(1, idProvider);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new Provider());
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
        public static List<Provider> getList(boolean isDelete) {
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
                return getProviders();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Provider> search(String values) {
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
                return getProviders();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }
    }

}
