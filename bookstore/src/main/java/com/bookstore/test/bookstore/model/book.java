package com.bookstore.test.bookstore.model;

import java.beans.Statement;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bookstore.test.bookstore.controller.bookstoreController;
import com.bookstore.test.bookstore.controller.dbConnector;
import java.io.DataInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class book {
	private String title;
	private String author;
	private BigDecimal price;
	public book() {
		
	}
	
	
	public book(String title, String author, BigDecimal price, int quantity, int availability) {
		//super();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(
			    "INSERT INTO BOOKSTORE " + 
			    "VALUES (" + title + "," + author + ", " + price + ", " + quantity + "," + availability + ");
		//return book;
		System.out.println("Book saved");
	}
	
	
	public String getTitle() {
		String query= "SELECT TITLE FROM BOOKSTORE;";
		PreparedStatement stmt = dbConnector.prepareStatement(query);
		con.createStatement().executeUpdate(stmt);
		System.out.println("titles fetched");
		//return title;
	}
	
	
	public void setTitle(String title, String author) {
		//this.title = title;
		String query= "UPDATE BOOKSTORE SET TITLE = " + title + "WHERE AUTHOR = " + author + ";";
		PreparedStatement stmt = dbConnector.prepareStatement(query);
		con.createStatement().executeUpdate(stmt);
		System.out.println("titles saved");
		//return title;
	}
	
	public String getAuthor() {
		//return author;
		String query = "SELECT AUTHOR FROM BOOKSTORE;";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
		    String s = rs.getString("TITLE");
		    float n = rs.getFloat("AUTHOR");
		    System.out.println(s + "   " + n);
		    return n;
		}
	}
	
	
	public void setAuthor(String author, String title) {
		//this.author = author;
		String query= "UPDATE BOOKSTORE SET AUTHOR = " + author + "WHERE TITLE = " + title + ";";
		PreparedStatement stmt = dbConnector.prepareStatement(query);
		con.createStatement().executeUpdate(stmt);
		System.out.println("titles saved");
	}
	
	
	public BigDecimal getPrice() {
		//Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:8080/demo","root","123");
		Statement stmt=cn.createStatement();
		String query = "SELECT PRICE FROM BOOKSTORE";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
		    String s = rs.getString("TITLE");
		    float n = rs.getFloat("PRICE");
		    System.out.println(s + "   " + n);
		    return n;
		}
		return price;
	}
	
	
	public void setPrice(BigDecimal price) {
		//this.price = price;
		String query= "UPDATE BOOKSTORE SET PRICE = " + price + "WHERE TITLE = " + title + ";";
		PreparedStatement stmt = dbConnector.prepareStatement(query);
		con.createStatement().executeUpdate(stmt);
		System.out.println("PRICE saved");
	}
	
	public void updateBook(String title, String author, BigDecimal price, int quantity, int availability) {
		//this sets the book values keeping the title constant
		String query= "UPDATE BOOKSTORE SET AVAILABILITY = " + availability + " QUANTITY = " + quantity + " PRICE = " + price + "AUTHOR = " + author + "WHERE TITLE = " + title + ";";
		PreparedStatement stmt = dbConnector.prepareStatement(query);
		con.createStatement().executeUpdate(stmt);
		System.out.println("titles saved");
		}
	}
	
	public void deletebook(String title, String author) {
		//book.removeif(t -> getTitle().equals(title));
		String query = "DELETE FROM BOOKSTORE WHERE TITLE = " + title +, " AND AUTHOR = " + author + ";"
		PreparedStatement stmt = dbConnector.prepareStatement(query);
		con.createStatement().executeUpdate(stmt);
		System.out.println("Book Deleted");
		
		}
	}
	
	public void setQuantity(String title, int quantity) {
		//this.price = price;
		String query= "UPDATE BOOKSTORE SET QUANTITY = " + quantity + "WHERE TITLE = " + title + ";";
		PreparedStatement stmt = dbConnector.prepareStatement(query);
		con.createStatement().executeUpdate(stmt);
		System.out.println("PRICE saved");
	}
}
