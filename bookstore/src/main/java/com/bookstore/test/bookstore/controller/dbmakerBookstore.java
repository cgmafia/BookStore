package com.bookstore.test.bookstore.controller;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//This runs once in the beginning
public class dbmakerBookstore {
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String dbAddress = "jdbc:mysql://localhost:3306/";
    private String userPass = "?user=root&password=";
    private String dbName = "BOOKSTORE";
    private String userName = "root";
    private String password = "";

    private PreparedStatement preStatement;
    private Statement statement;
    private ResultSet result;
    private Connection con;

    public DbStuff() {
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (SQLException e) {
            createDatabase();
            createTableCub1();
        }
    }

    private void createDatabase() {
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + userPass);
            Statement stmt = con.createStatement();
            int myResult = stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableCub1() {
        String myTableName = "CREATE TABLE BookInventory (" 
            + "idNo INT(64) NOT NULL AUTO_INCREMENT,"  
            + "title VARCHAR(255)," 
            + "author VARCHAR(255)," 
            + "price INT(9999),"  
            + "quantity INT(9999),"
        	+ "availability INT(1)";
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
            statement = con.createStatement();
            //The next line has the issue
            statement.executeUpdate(myTableName);
            System.out.println("Table Created");
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
        catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }
        
        String initDB = 'SELECT distinct title, author, price, quantity, availability FROM BookInventory b';
        con.createStatement().executeUpdate(initDB);
    }
    }
