/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Howard's PC
 */

//STEP 1. Import required packages
import java.sql.*;
import java.util.*;


public class JDBCProject {
     static String USER;
    static String PASS;
    static String DBNAME;
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";  
   static String DB_URL = "jdbc:derby://localhost:1527/";

   //  Database credentials
   public static void main(String[] args) {
   
        Scanner in = new Scanner(System.in);
        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        System.out.print("Database user name: ");
        USER = in.nextLine();
        System.out.print("Database password: ");
        PASS = in.nextLine();
        //Constructing the database URL connection string
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("org.apache.derby.jdbc.ClientDriver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL);
      System.out.println("Connected database successfully...");
      
      boolean exit = true;
      String mainMenu = "Enter the number corresponding to your choice:\n1. List All Album Titles\n2. List All Data For An Album" +
      "\n3. Insert A New Album\n4. Insert New Studio and Update Albums\n5. Remove An Album";
      Scanner scan = new Scanner(System.in);
      while (exit) {
    	  System.out.println(mainMenu);
    	  if (scan.hasNextInt()) {
    		  int choice = scan.nextInt();
    		  scan.nextLine();
    		  switch (choice) {
    		  case 1: {
    			 // LIST ALL ALBUM TITLES
    		      System.out.println("Creating statement...\n");
    		      stmt = conn.createStatement();

    		      String sql = "SELECT album_title FROM recordingAlbums";
    		      ResultSet rs = stmt.executeQuery(sql);
    		      //STEP 5: Extract data from result set
    		      System.out.println("Album Titles:");
    		      while(rs.next()){
    		         //Retrieve by column name
    		         String first = rs.getString("album_title");

    		         //Display values
    		         System.out.println(first);
    		      }
    		      System.out.println();
    		      rs.close();
    		  }//end case 1
    			  break;
    		  case 2: {
    			  //LIST DATA FOR ALBUM
    			  System.out.println("Enter the name of the album to find");
    			  String albumChoice = scan.nextLine();
    			  System.out.println("Creating statement...");
    		      stmt = conn.createStatement();
    			  String sql = "SELECT album_title, group_name, studio_name, date_recorded, album_length, number_of_songs FROM recordingAlbums WHERE album_title='"+albumChoice+"'";
    			  ResultSet rs = stmt.executeQuery(sql);
    			  while(rs.next()) {
    				  String title = rs.getString("album_title");
                                  String groupName = rs.getString("group_name");
    				  String studioName = rs.getString("studio_name");
    				  String date = rs.getString("date_recorded");
    				  String length = rs.getString("album_length");
    				  String songs = rs.getString("number_of_songs");
    				  
    				  System.out.println("\nTitle: "+title+"\nGroup Name: "+groupName+"\nStudio Name: "+studioName+"\nDate Recorded: "+date+"\nAlbum Length: "+length+"\nNumber of songs:"+songs+"\n");
    			  }
    			  
    		  }//end case 2
    			  break;
    		  case 3: {
    			  stmt = conn.createStatement();
    			  System.out.println("Enter the title of the album to add(limit 60 characters)");
    			  String title = scan.nextLine();
                          System.out.println("Enter group name");
    			  String groupName = scan.nextLine();
    			  System.out.println("Enter studio name");
    			  String studioName = scan.nextLine();
    			  System.out.println("Enter the date recorded (Month YYYY)");
    			  String date = scan.nextLine();
    			  System.out.println("Enter the length of the record (minutes:seconds)");
    			  String length = scan.nextLine();
    			  System.out.println("Enter the number of songs");
    			  String songs = scan.nextLine();
    			  System.out.println("Creating statement...");
    			  String sql = "INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs) "  + 
                                  "VALUES ('"+title+"', '"+groupName+"', '"+studioName+"', '"+date+"', '"+length+"', '"+songs+"')";
    			  stmt.executeUpdate(sql);
    		  }//end case 3
    			  break;
    		  case 4: {
    			  //INSERT NEW STUDIO/ UPDATE
    			  stmt = conn.createStatement();
    			  System.out.println("Enter name of new studio");
    			  String name = scan.nextLine();
    			  System.out.println("Enter address of studio");
    			  String address = scan.nextLine();
    			  System.out.println("Enter owner name");
    			  String owner = scan.nextLine();
    			  System.out.println("Enter studio phone");
    			  String phone = scan.nextLine();
    			  System.out.println("Enter name of studio to replace");
    			  String replace = scan.nextLine();
    			  String sql = "INSERT INTO recordingStudios (studio_name, studio_address, studio_owner, studio_phone) " +
    			  		"VALUES ('"+name+"', '"+address+"', '"+owner+"', '"+phone+"')";
    			  stmt.executeUpdate(sql);
    			  String sql2 = "UPDATE recordingAlbums " +  "SET studio_name='"+name+"' WHERE studio_name='"+replace +"'";
    			  stmt.executeUpdate(sql2);
    		  }//end case 4
    			  break;
    		  case 5: {
    			  stmt = conn.createStatement();
    			  //REMOVE
    			  System.out.println("Enter the name of the album to be deleted");
    			  String title = scan.nextLine();
    			  System.out.println("Enter the name of the group");
    			  String group = scan.nextLine();
    			  
    			  String sql = "DELETE FROM recordingAlbums WHERE album_title='"+title +"' AND group_name='"+group+"'";
    			  stmt.executeUpdate(sql);
    		  }//end case 5
    			  break;
         }}
    	 else {
    		  System.out.println("Invalid Input");
    	  }//end else
    	 
      }//end while
       
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}