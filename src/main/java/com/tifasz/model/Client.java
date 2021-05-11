package com.tifasz.model;

import com.tifasz.controller.CClient;
import com.tifasz.solution.ConnectionDB;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Client extends People {
    static ConnectionDB connectionDB = new ConnectionDB();

    private byte origin;
    private byte status;
    private byte condition;

    public Client() {
        super();
        this.origin = 0;
        this.status = 0;
        this.condition = 0;
    }

    public Client(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, byte origin, byte status, byte condition) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined);
        this.origin = origin;
        this.status = status;
        this.condition = condition;
    }

    protected static Client newClient(String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex,
                                      Date birthdate, String address, UbiGeoDistrict district, Date dateJoined, byte origin,
                                      byte status, byte condition) {
        Client client = new Client();
        client.setDocumentType(documentType);
        client.setDocumentNumber(documentNumber);
        client.setFullName(fullName);
        client.setNumberPhone(numberPhone);
        client.setEmail(email);
        client.setSex(sex);
        client.setBirthdate(birthdate);
        client.setAddress(address);
        client.setDistrict(district);
        client.setDateJoined(dateJoined);
        client.setOrigin(origin);
        client.setStatus(status);
        client.setCondition(condition);
        return client;
    }

    public byte getOrigin() {
        return origin;
    }

    public void setOrigin(byte origin) {
        this.origin = origin;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getCondition() {
        return condition;
    }

    public void setCondition(byte condition) {
        this.condition = condition;
    }

    protected boolean save() {
        try {
            if (connectionDB.openConnection()) {
                return false;
            }

            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUClient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            extracted(connectionDB);
            connectionDB.query.setByte(13, getOrigin());
            connectionDB.query.setByte(14, getStatus());
            connectionDB.query.setByte(15, getCondition());
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
        return "Client{" +
                "origin=" + origin +
                ", status=" + status +
                ", condition=" + condition +
                "} " + super.toString() + '\n';
    }

    public static class Query {

        @NotNull
        @org.jetbrains.annotations.Contract
        private static CClient insertAttributes(@NotNull CClient client) throws Exception {
            People.insertAttributes(client, connectionDB);
            client.setOrigin(connectionDB.result.getByte(13));
            client.setStatus(connectionDB.result.getByte(14));
            client.setCondition(connectionDB.result.getByte(15));
            return client;
        }

        @NotNull
        private static List<CClient> getCClients() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<CClient> clients = new ArrayList<>();
            while (connectionDB.result.next()) {
                CClient client = insertAttributes(new CClient());
                clients.add(client);
            }
            return clients;
        }

        @Nullable
        @Contract(pure = true)
        public static CClient get(String documentNumber) {
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
                        "\tclient.origin,\n" +
                        "\tclient.status,\n" +
                        "\tclient.`condition`\n" +
                        "FROM people\n" +
                        "INNER JOIN client \n" +
                        "ON people.id_people = client.id_client \n" +
                        "WHERE people.document_number = ?");
                connectionDB.query.setString(1, documentNumber);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new CClient());
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
        public static CClient get(int idCClient) {
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
                        "\tclient.origin,\n" +
                        "\tclient.status,\n" +
                        "\tclient.`condition`\n" +
                        "FROM people\n" +
                        "INNER JOIN client \n" +
                        "ON people.id_people = client.id_client \n" +
                        "WHERE people.id_people = ?");
                connectionDB.query.setInt(1, idCClient);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new CClient());
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
        public static List<CClient> getList(boolean isDelete) {
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
                        "\tclient.origin,\n" +
                        "\tclient.status,\n" +
                        "\tclient.`condition`\n" +
                        "FROM people\n" +
                        "INNER JOIN client \n" +
                        "ON people.id_people = client.id_client\n" +
                        "WHERE people.is_delete = ?");
                connectionDB.query.setBoolean(1, isDelete);
                return getCClients();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<CClient> search(String values) {
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
                        "\tclient.origin,\n" +
                        "\tclient.status,\n" +
                        "\tclient.`condition`\n" +
                        "FROM people\n" +
                        "INNER JOIN client \n" +
                        "ON people.id_people = client.id_client\n" +
                        "WHERE MATCH (people.document_number,\n" +
                        "people.full_name) AGAINST ('" + values + "*' IN BOOLEAN MODE)");
                return getCClients();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }


    }
}
