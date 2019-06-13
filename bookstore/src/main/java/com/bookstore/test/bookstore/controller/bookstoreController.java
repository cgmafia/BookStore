package com.bookstore.test.bookstore.controller;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.bookstore.test.bookstore.model.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import com.opencsv.CSVReader;


@Controller
public class bookstoreController {
	
	// R3ading the link and encoding data via CSV reader
	@RequestMapping(value="/reader", method = RequestMethod.GET)
	private @ResponseBody String[] dataReader() {
				
		final String url = "https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt";
	    RestTemplate restTemplate = new RestTemplate();
	    String str = restTemplate.getForObject(url, String.class);
	    System.out.println(str);
	    String readline = str;
	    //return str;
	    String SAMPLE_CSV_FILE_PATH = url;
	    public static void Booky(String[] args) throws IOException {
	        try (
	            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
	            CSVReader csvReader = new CSVReader(reader);
	        ) {
	            // Reading Records One by One in a String array
	            String[] nextRecord;
	            while ((nextRecord = csvReader.readNext()) != null) {
	            	//test the record here offline
	            	//send each element of array to a field of database
	            	try
		                { 
		                    Connection conn = DriverManager.getConnection(url,user,pass); 
		                    Statement st = conn.createStatement(); 
		                    Scanner first = new Scanner(System.in);
		                    System.out.println(first.nextLine());
		                    String SQL = "INSERT INTO test VALUES (" + str[0] + "," + str[1] + ", " + str[2] + ", " + str[3] ")";
		                    st.executeUpdate(SQL);
		                      conn.close(); 
		                } catch (Exception e) { 
		                    System.err.println("Got an exception! "); 
		                    System.err.println(e.getMessage()); 
		                } 
		            }
		        }
		    }
		}
		
	
	
	@RequestMapping(value="/bookstore", method = RequestMethod.GET)
	public @ResponseBody List<book> getBooks() {
		try
        {
			Connection conn = DriverManager.getConnection(url,user,pass); 
            Statement stmt = conn.createStatement(); 
            stmt.execute("SELECT * FROM BOOKSTORE");
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {  
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }		
	}
	}
	
	//Adds a book
	@RequestMapping(value="/addbook/{title}/{author}/{price}/{quantity}/{availability}", method = RequestMethod.POST)
	public void addBook(@Valid @RequestBody book book, @RequestBody book quantity) {
		Statement stmt = con.createStatement();
		stmt.executeUpdate(
			    "INSERT INTO BOOKSTORE " + 
			    "VALUES (" + title + "," + author + ", " + price + ", " + quantity + "," + availability + ");
		//return book;
		System.out.println("Book saved");
	}
	
	
	//Deletes the particular book
	@RequestMapping(value="deletebook/{title}/{author}", method = RequestMethod.DELETE)
	public void deletebook(@PathVariable String title, @PathVariable String author) {
		try
        {
			Connection conn = DriverManager.getConnection(url,user,pass); 
            Statement stmt = conn.createStatement(); 
            stmt.execute("DELETE FROM BOOKSTORE WHERE NAME =", + title +, " AND AUTHOR = ", + author + ")";
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {  
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }		
	}
	}

	
	// buy the book
	@RequestMapping(value="/buy/{title}", method = RequestMethod.GET)
	public @ResponseBody book buyBooks(@PathVariable String title) {
		//this function tried to buy the book. The book can be only be bought after checking the status if its 0,1,2
		//this also refects in the count of books left
		try
        {
			Connection conn = DriverManager.getConnection(url,user,pass); 
            Statement stmt = conn.createStatement(); 
            String qryAvailability = "SELECT " + title + ", CASE WHEN availability = '0' then 'OK' WHEN availability = '1' then 'NOT_IN_STOCK' ELSE 'DOES_NOT_EXIST' END FROM BOOKSTORE"
            ResultSet stmt = sta.executeQuery(qryAvailability);
            while (stmt.next())
            {
            	String answer = stmt.getString("title")
            	
            	if (answer == "OK") {
            		System.out.println("OK");
            	} else if (answer == "DOES_NOT_EXIST") {
            		System.out.println("Does not exist");
            	} else {
            		System.out.println("Not in Stock");
            	} 
            	}
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {  
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }		
        }
		//return purchaseBook;
	} 
	
	
	
	
	@RequestMapping(value="/searchByTitle/{title}", method = RequestMethod.GET)
	public @ResponseBody book searchBooks(@PathVariable String title) {
		try
        {
			Connection conn = DriverManager.getConnection(url,user,pass); 
            Statement stmt = conn.createStatement(); 
            stmt.execute("SELECT * FROM BOOKSTORE WHERE title =" + title + );
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {  
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }		
        }
	}
	
	
	@RequestMapping(value="/searchByAuthor/{author}", method = RequestMethod.GET)
	public @ResponseBody book searchpBooks(@PathVariable String author) {
		try
        {
			Connection conn = DriverManager.getConnection(url,user,pass); 
            Statement stmt = conn.createStatement(); 
            stmt.execute("SELECT * FROM BOOKSTORE WHERE author =" + author + );
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {  
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }		
        }
}

