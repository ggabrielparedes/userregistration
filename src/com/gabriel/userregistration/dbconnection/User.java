package com.gabriel.userregistration.dbconnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String email;
    private String name;
    private String password;
    private String username;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
        String SQL = "SELECT nm_email, nm_password FROM \"USERS\" WHERE nm_email = ?";
        boolean response;
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL);
        ) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            response = (resultSet.next() && (resultSet.getString("nm_password").equals(password))) ? true : false;
            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addUser() {
        String SQL = "INSERT INTO \"USERS\" (nm_user, nm_email, nm_password) VALUES (?, ?, ?)";
        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
        ) {
            if(verifyEmail(email)) return false;
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            try {
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao inserir usuário: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verifyEmail(String email) {
        boolean response;
        String SQL = "SELECT nm_email FROM \"USERS\" WHERE nm_email = ?";
        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
        ) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
