package com.tifasz.model;

import com.tifasz.solution.ConnectionDB;
import com.tifasz.solution.IModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class User extends People implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private String userCode;
    private String password;
    private Time entryTime;
    private Time exitTime;
    private boolean isSuperUser;
    private boolean isActive;
    private boolean isStaff;

    protected User() {
        super();
        this.userCode = "";
        this.password = "";
        this.entryTime = null;
        this.exitTime = null;
        this.isSuperUser = false;
        this.isActive = false;
        this.isStaff = false;
    }

    protected User(String userCode, String password, Time entryTime, Time exitTime, boolean isSuperUser, boolean isActive, boolean isStaff) {
        this.userCode = userCode;
        this.password = password;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isSuperUser = isSuperUser;
        this.isActive = isActive;
        this.isStaff = isStaff;
    }

    protected User(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, String userCode, String password, Time entryTime, Time exitTime, boolean isSuperUser, boolean isActive, boolean isStaff) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined);
        this.userCode = userCode;
        this.password = password;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isSuperUser = isSuperUser;
        this.isActive = isActive;
        this.isStaff = isStaff;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Time getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Time entryTime) {
        this.entryTime = entryTime;
    }

    public Time getExitTime() {
        return exitTime;
    }

    public void setExitTime(Time exitTime) {
        this.exitTime = exitTime;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public void setSuperUser(boolean superUser) {
        isSuperUser = superUser;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    protected static User newUser(String documentType, String documentNumber, String fullName, String numberPhone, String email,
          byte sex, Date birthdate, String address, UbiGeoDistrict district,Date dateJoined, String userCode,
          String password, Time entryTime, Time exitTime) {
        User user = new User();
        user.setPeopleAttributes( documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, dateJoined);
        user.setUserCode(userCode);
        user.setPassword(password);
        user.setEntryTime(entryTime);
        user.setExitTime(exitTime);
        return user;
    }

    @Override
    public boolean save() {

        try {

            if (connectionDB.openConnection()) {
                return false;
            }

            connectionDB.query = connectionDB.connection.prepareCall("CALL spCUUser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            extracted(connectionDB);
            connectionDB.query.setString(13, getUserCode());
            connectionDB.query.setString(14, getPassword());
            connectionDB.query.setTime(15, getEntryTime());
            connectionDB.query.setTime(16, getExitTime());
            connectionDB.query.setBoolean(17, isSuperUser());
            connectionDB.query.setBoolean(18, isActive());
            connectionDB.query.setBoolean(19, isStaff());
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

    public static class Query {

        @NotNull
        @org.jetbrains.annotations.Contract
        private static User insertAttributes(@NotNull User user) throws Exception {
            People.insertAttributes(user, connectionDB);
            user.setUserCode(connectionDB.result.getString(13));
            user.setPassword(connectionDB.result.getString(14));
            user.setEntryTime(connectionDB.result.getTime(15));
            user.setExitTime(connectionDB.result.getTime(16));
            user.setSuperUser(connectionDB.result.getBoolean(17));
            user.setActive(connectionDB.result.getBoolean(18));
            user.setStaff(connectionDB.result.getBoolean(19));
            return user;
        }

        @NotNull
        private static List<User> getUsers() throws Exception {
            connectionDB.result = connectionDB.query.executeQuery();
            List<User> users = new ArrayList<>();
            while (connectionDB.result.next()) {
                User user = insertAttributes(new User());
                users.add(user);
            }
            return users;
        }

        @Nullable
        @Contract(pure = true)
        public static User get(int idUser) {
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
                        "\tuser.user_code,\n" +
                        "\tuser.password,\n" +
                        "\tuser.entry_time,\n" +
                        "\tuser.exit_time,\n" +
                        "\tuser.is_super_user,\n" +
                        "\tuser.is_active,\n" +
                        "\tuser.is_staff\n" +
                        "FROM people\n" +
                        "INNER JOIN `user`\n" +
                        "ON people.id_people = user.id_user\n" +
                        "WHERE people.id_people = ?");
                connectionDB.query.setInt(1, idUser);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new User());
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
        public static User get(String documentNumber) {
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
                        "\tuser.user_code,\n" +
                        "\tuser.password,\n" +
                        "\tuser.entry_time,\n" +
                        "\tuser.exit_time,\n" +
                        "\tuser.is_super_user,\n" +
                        "\tuser.is_active,\n" +
                        "\tuser.is_staff\n" +
                        "FROM people\n" +
                        "INNER JOIN `user`\n" +
                        "ON people.id_people = user.id_user\n" +
                        "WHERE people.document_number = ?");
                connectionDB.query.setString(1, documentNumber);
                connectionDB.result = connectionDB.query.executeQuery();
                if (connectionDB.result.next()) {
                    return insertAttributes(new User());
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
        public static List<User> getList(boolean isDelete) {
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
                        "\tuser.user_code,\n" +
                        "\tuser.password,\n" +
                        "\tuser.entry_time,\n" +
                        "\tuser.exit_time,\n" +
                        "\tuser.is_super_user,\n" +
                        "\tuser.is_active,\n" +
                        "\tuser.is_staff\n" +
                        "FROM people\n" +
                        "INNER JOIN `user`\n" +
                        "ON people.id_people = user.id_user\n" +
                        "WHERE people.is_delete = ?");
                connectionDB.query.setBoolean(1, isDelete);
                return getUsers();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }

        @Nullable
        @Contract(pure = true)
        public static List<User> search(String values) {
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
                        "\tuser.user_code,\n" +
                        "\tuser.password,\n" +
                        "\tuser.entry_time,\n" +
                        "\tuser.exit_time,\n" +
                        "\tuser.is_super_user,\n" +
                        "\tuser.is_active,\n" +
                        "\tuser.is_staff\n" +
                        "FROM people\n" +
                        "INNER JOIN `user`\n" +
                        "ON people.id_people = user.id_user\n" +
                        "WHERE MATCH (people.document_number,\n" +
                        "people.full_name) AGAINST ('" + values + "*' IN BOOLEAN MODE);");
                return getUsers();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectionDB.closeConnection();
            }
            return null;
        }


    }

    @Override
    public String toString() {
        return "User{" +
                "userCode='" + userCode + '\'' +
                ", password='" + password + '\'' +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", isSuperUser=" + isSuperUser +
                ", isActive=" + isActive +
                ", isStaff=" + isStaff +
                "} " + super.toString() + '\n';
    }
}
