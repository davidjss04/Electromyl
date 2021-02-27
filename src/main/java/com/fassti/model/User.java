package com.fassti.model;

import com.fassti.solution.ConnectionDB;
import com.fassti.solution.IModel;

import java.sql.Date;
import java.sql.Time;

public class User extends People implements IModel {
    static ConnectionDB connectionDB = new ConnectionDB();

    private String user_code;
    private String password;
    private Time entry_time;
    private Time exit_time;
    private boolean isSuperUser;
    private boolean isActive;
    private boolean isStaff;

    public User() {
        super();
        this.user_code = "";
        this.password = "";
        this.entry_time = null;
        this.exit_time = null;
        this.isSuperUser = false;
        this.isActive = false;
        this.isStaff = false;
    }

    @Override
    public People newPeople() {
        return null;
    }

    public User(String user_code, String password, Time entry_time, Time exit_time, boolean isSuperUser, boolean isActive, boolean isStaff) {
        this.user_code = user_code;
        this.password = password;
        this.entry_time = entry_time;
        this.exit_time = exit_time;
        this.isSuperUser = isSuperUser;
        this.isActive = isActive;
        this.isStaff = isStaff;
    }

    public User(int idPeople, String documentType, String documentNumber, String fullName, String numberPhone, String email, byte sex, Date birthdate, String address, UbiGeoDistrict district, boolean isDelete, Date dateJoined, String user_code, String password, Time entry_time, Time exit_time, boolean isSuperUser, boolean isActive, boolean isStaff) {
        super(idPeople, documentType, documentNumber, fullName, numberPhone, email, sex, birthdate, address, district, isDelete, dateJoined);
        this.user_code = user_code;
        this.password = password;
        this.entry_time = entry_time;
        this.exit_time = exit_time;
        this.isSuperUser = isSuperUser;
        this.isActive = isActive;
        this.isStaff = isStaff;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Time getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Time entry_time) {
        this.entry_time = entry_time;
    }

    public Time getExit_time() {
        return exit_time;
    }

    public void setExit_time(Time exit_time) {
        this.exit_time = exit_time;
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

    @Override
    public boolean save() {
        return false;
    }

}
