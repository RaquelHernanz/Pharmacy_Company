package PharmacyCompanyJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class JDBCManager {
	
   private Connection c = null;
   
   public JDBCManager() {
	   
	   try {
		   
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection("jdbc:sqlite:./db/PharmacyCompany.db");
		   c.createStatement().execute("PRAGMA foreign_keys=ON");
		   System.out.print("Database Connection opened.");
		   this.createTables();
		   
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }
	   catch(ClassNotFoundException e) {
		   System.out.print("Libraries not loaded");
		   
	   }
   }
   
   private void createTables() {
		try {
			
			Statement stmt = c.createStatement();
			
			String sql = "CREATE TABLE administrators ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, "
					+ "surname TEXT NOT NULL, "
					+ "email TEXT NOT NULL UNIQUE, "
					+ "phone_number INTEGER);";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE clients ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL, "
					+ "surname TEXT NOT NULL, "
					+ "email TEXT NOT NULL UNIQUE, "
					+ "phone_number INTEGER, "
					+ "address TEXT NOT NULL);";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE pharmacists "
					+"(id INTEGER PRIMARY KEY  AUTOINCREMENT, "
					+" name TEXT NOT NULL, "
					+ "surmane TEXT NOT NULL, "
					+ "phone_number INTEGER, "
					+ "email TEXT NOT NULL);";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE doctors ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL, "
					+ "surname TEXT NOT NULL, "
					+ "email TEXT NOT NULL UNIQUE, "
					+ "phone_number INTEGER, "
					+ "address TEXT NOT NULL);";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE medicines ("
					+ "code INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT UNIQUE,"
					+ "price REAL,"
					+ "instructions TEXT,"
					+ "stock INTEGER,"
					+ "expirations DATE NOU NULL,"
					+ "FOREIGN KEY pharmacist_id INTEGER REFERENCES pharmacists(id) DELETE ON CASCADE,"
					+ "image BLOB,"
					+ "prescribed BOOLEAN NOT NULL);";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE orders ("
					+ "code_o INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "total_price REAL,"
					+ "quantity INTEGER,"
					+ "administrator id INTEGER REFERENCES administrators (id) DELETE ON CASCADE,"
					+ "pharmacist_id INTEGER REFERENCES pharmacists (id) DELETE ON CASCADE,"
					+ "medicine_id INTEGER REFERENCES medicines (code) DELETE ON CASCADE"
					+ ");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE purchase_C ("
					+ "cliend_id INTEGER REFERENCES clients (id) DELETE ON CASCADE,"
					+ "medicine_id INTEGER REFERENCES medicines (code) DELETE ON CASCADE,"
					+ "quantity INTEGER,"
					+ "bill REAL,"
					+ "date DATE,"
					+ "PRIMARY KEY (client_id, medicine_id));";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE purchase_D ("
					+ "medicine_id INTEGER REFERENCES medicines (code) DELETE ON CASCADE,"
					+ "doctor_id INTEGER REFERENCES doctors (id)) DELETE ON CASCADE"
					+ "quantity INTEGER,"
					+ "bill REAL,"
					+ "date DATE,"
					+ "PRIMARY KEY (doctor_id, medicine_id);";
			
			stmt.executeUpdate(sql);
			
			
			
		}catch(SQLException e) {
			if(!e.getMessage().contains("already exist")) 
			{
				e.printStackTrace();
			}			
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
