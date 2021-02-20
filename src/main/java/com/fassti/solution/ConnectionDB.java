package com.fassti.solution;

import com.fassti.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionDB {

    public Connection connection;
    public PreparedStatement query;
    public ResultSet result;

    protected static final String DRIVER = "org.mariadb.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mariadb://localhost:3306/electromyl_db_l";
    protected static final String USER = "davidjss";
    protected static final String PASSWORD = "321pedidor";
    public static final String ANSI_RED = "\u001B[31m";
    public static String ANSI_RESET = "\u001B[0m";

    public boolean openConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return true;
        } catch (Exception e) {
            System.out.println(ANSI_RED + "NO ACCESS TO THE DATABASES" + ANSI_RESET);
        }
        return false;
    }

    public boolean closeConnection() {
        try {
            if (connection == null) {
                return false;
            }
            if (!connection.isClosed()) {
                if (result != null) {
                    if (!result.isClosed())
                        result.close();
                }
                query.close();
                connection.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static String toTitle(String text){
        String returnText= "";
        char character;
        boolean startLetter = true;

        for (int i=0; i< text.length() ; i++){

            character = (char) text.charAt(i);

            if(character == ' '){
                returnText = (returnText + character);
                startLetter = true;

            }else {
                if(startLetter == true){
                    if(Character.isLowerCase(character)){

                        character = Character.toUpperCase(character);
                        returnText = (returnText + character);
                    }else {
                        returnText = (returnText + character);
                    }
                    startLetter = false;
                }else {
                    if(Character.isUpperCase(character)){

                        character = Character.toLowerCase(character);
                        returnText = (returnText + character);
                    }else {
                        returnText = (returnText + character);
                    }
                }
            }
        }

        return returnText;
    }
}
