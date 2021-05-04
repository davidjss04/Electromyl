package com.tifasz.model;

import com.tifasz.solution.ConnectionDB;
import com.tifasz.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Client extends People implements IModel {
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

    @Override
    public boolean save() {
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
        private static Client insertAttributes(@NotNull Client client) throws Exception {
            People.insertAttributes(client, connectionDB);
            client.setOrigin(connectionDB.result.getByte(13));
            client.setStatus(connectionDB.result.getByte(14));
            client.setCondition(connectionDB.result.getByte(15));
            return client;
        }

        @NotNull
        private static List<Client> getClients() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (connectionDB.result.next()) {
                Client client = insertAttributes(new Client());
                clients.add(client);
            }
            return clients;
        }

        @Nullable
        @Contract(pure = true)
        public static Client get(int idClient) {
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
                connectionDB.query.setInt(1, idClient);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new Client());
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
        public static List<Client> getList(boolean isDelete) {
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
                return getClients();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<Client> search(String values) {
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
                return getClients();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }


    }
}
