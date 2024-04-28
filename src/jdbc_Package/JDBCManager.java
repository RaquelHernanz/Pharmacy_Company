package jdbc_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
	
   private Connection c = null;
   
   public JDBCManager() {
	   
	   try {
		   
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection("jdbc:sqlite:./db/PharmacyCompany.db");
		   c.createStatement().execute("PRAGMA foreign_keys=ON");
		   
		   System.out.print("Database Connection opened.");
		   
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }
	   catch(ClassNotFoundException e) {
		   System.out.print("Libraries not loaded");
		   
	   }
   }
   
   public Connection getConnection() {
	   return c;
   }
   
   public void disconnect() {
	   try {
		   
		   c.close();
		   
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }
   }
}
