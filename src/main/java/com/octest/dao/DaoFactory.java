package com.octest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:mysql://localhost:3306/javaee", "root", "kiboo1100MYSQL");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération du Dao

    public UserDao getUserDao() {
        return new UserDaoImpl(this);
    }
    public YearDao getYearDao() {
        return new YearDaoImpl(this);
    }
    public TransactionDao getTransactionDao() {
        return new TransactionDaoImpl(this);
    }
    public PersonnelDao getPersonnelDao() {
        return new PersonnelDaoImpl(this);
    }
    public OutilsDao getoutilDao() {
        return new OutilsDaoImpl(this);
    }
}