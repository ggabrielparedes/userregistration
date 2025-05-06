package com.gabriel.userregistration.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL  = "jdbc:postgresql://localhost:5432/Cadastro_Usuarios";
    private static final String USERNAME = "postgres"; //Username
    private static final String PASSWORD = "admin"; //Password
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
