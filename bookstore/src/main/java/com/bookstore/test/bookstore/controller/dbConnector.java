package com.bookstore.test.bookstore.controller;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


//Connect to SQl and create a database
public class dbConnector {
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","root");  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from bookstore");  
		while(rs.next()) 
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
	}catch(Exception e){
		System.out.println(e);
	}  
}
} 
