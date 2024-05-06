package UI_Package;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyInterfaces.DoctorManager;
import PharmacyCompanyInterfaces.PharmacistManager;
import PharmacyCompanyInterfaces.UserManager;
import PharmacyCompanyJDBC.JDBCAdministratorManager;
import PharmacyCompanyJDBC.JDBCClientManager;
import PharmacyCompanyJDBC.JDBCDoctorManager;
import PharmacyCompanyJDBC.JDBCManager;
import PharmacyCompanyJDBC.JDBCPharmacistManager;
import PharmacyCompanyJPA.JPAUserManager;
import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Client;
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Pharmacist;
import PharmacyCompanyPOJOs.Role;
import PharmacyCompanyPOJOs.User;



public class MainMenu {

	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  
      jdbcmanager = new JDBCManager();
      administratormanager= new JDBCAdministratorManager(jdbcmanager);
      clientmanager = new JDBCClientManager(jdbcmanager);
      doctormanager = new JDBCDoctorManager (jdbcmanager);
      pharmacistmanager = new JDBCPharmacistManager (jdbcmanager);
      usermanager= new JPAUserManager();
      
      try {
    	  int choice;
    	  do {
    		  System.out.println("Choose an initial option");
    		  System.out.println("1. Login User");
    		  System.out.println("2. Sing-up new user");
    		  System.out.println("0. Exit");
    		  //Después de realizar el login user o el sign-up según el usuario se pondrá el menú
    		  choice = Integer.parseInt(reader.readLine());
    		  
    		  switch(choice) {
    		  case 1 -> //
    		  {
    			  try {
    			  login();
    			  }catch (java.sql.SQLException e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    			  
    		  }
    		  case 2 ->
    		  {
    			  try {
    			  singUpUser();
    			  }catch (Exception e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 0 ->  
    		  {   jdbcmanager.disconnect();
    			  System.out.println("At least this doesnt fail");
    			  System.exit(0);
    		  }
    		  }
    		  
    	  }while(choice > -1);
    	  

	}catch(Exception e) 
      {
		System.out.println("At least this doesnt fail");
  	     e.printStackTrace();
  	  }
	}
	
	private static void login() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Email: ");
		String email=reader.readLine();
		System.out.println("Password: ");
		String password=reader.readLine();
		
		User u= usermanager.checkPassword(email, password);
		
        if(u!=null && u.getRole().getName().equals("doctor")&& u.getRole().getName().equals("client")
        		&& u.getRole().getName().equals("pharmacist") && u.getRole().getName().equals("administrator")) {
        	System.out.println("Login of user successful");
        	//Llamar al método para cada usuario
        	
    }else 
    {
    	 System.out.println("Login not successful");
    }
		
	}

	private static void singUpUser() throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce your email of the user: ");
			String email=reader.readLine();
			System.out.println("Introduce your password: ");
			String password=reader.readLine();
			
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			
			System.out.println("Introduce the role of the user. ");
			System.out.println("1:client, 2:doctor 3:administrator 4:pharmacist ");
			Integer role= Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(role);
			User u= new User(email,pass,r);
			usermanager.newUser(u);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public static void createAdministrator () throws Exception
	{
		// TODO Auto-generated method stub
		System.out.println("Introduce your name");
		String name = reader.readLine();
		System.out.println("Introduce your surname");
		String surname = reader.readLine();
		System.out.println("Introduce your email");
		String email = reader.readLine();
		System.out.println("Introduce your phone number");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		Administrator a = new Administrator (name,surname,phonenumber,email);
		System.out.println(a.toString());
		administratormanager.createAdministrator(a);
	}
	
	
	private static void createClient () throws Exception 
	{
		System.out.println("Introduce your name");
		String name = reader.readLine();
		System.out.println("Introduce your surname");
		String surname = reader.readLine();
		System.out.println("Introduce your email");
		String email = reader.readLine();
		System.out.println("Introduce your phone number");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		System.out.println("Introduce your address for deliveries");
		String address = reader.readLine();
		Client c = new Client (name,surname,address,phonenumber,email);
		System.out.println(c.toString());
		clientmanager.createClient(c);
	}
	
	private static void createPharmacist () throws Exception 
	{
		System.out.println("Introduce your name");
		String name = reader.readLine();
		System.out.println("Introduce your surname");
		String surname = reader.readLine();
		System.out.println("Introduce your email");
		String email = reader.readLine();
		System.out.println("Introduce your phone number");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		Pharmacist p = new Pharmacist (name,surname,phonenumber,email);
		System.out.println(p.toString());
		pharmacistmanager.createPharmacist(p);
	}
	
	private static void createDoctor () throws Exception 
	{
		System.out.println("Introduce your name");
		String name = reader.readLine();
		System.out.println("Introduce your surname");
		String surname = reader.readLine();
		System.out.println("Introduce your email");
		String email = reader.readLine();
		System.out.println("Introduce your phone number");
		Integer phonenumber = Integer.parseInt(reader.readLine());
		System.out.println("Introduce your address for deliveries");
		String address = reader.readLine();
		Doctor d = new Doctor (name,surname,address,phonenumber,email);
		System.out.println(d.toString());
		doctormanager.createDoctor(d);
	}
	
}

	
	
