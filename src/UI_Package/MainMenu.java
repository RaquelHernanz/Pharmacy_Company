package UI_Package;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
import PharmacyCompanyPOJOs.Role;
import PharmacyCompanyPOJOs.User;
import VetClinicPOJOs.Owner;
import VetClinicPOJOs.Pet;


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
	  
      jdbcmanager=new JDBCManager();
      administratormanager= new JDBCAdministratorManager(jdbcmanager);
      clientmanager = new JDBCClientManager(jdbcmanager);
      doctormanager = new JDBCDoctorManager (jdbcmanager);
      pharmacistmanager = new JDBCPharmacistManager (jdbcmanager);
      usermanager= new JPAUserManager();
    //no sé porque no me funciona esto
      
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
    			  
    			  /*singUpUser();*/
    			  
    		  }
    		  case 0 ->  
    		  {   jdbcmanager.disconnect();
    			  System.out.println("At least this doesnt fail");
    			  System.exit(0);
    		  }
    		  }
    		  
    	  }while(choice > -1);
    	  

	}catch(Exception e) {
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
	
	//Por ahora los métodos como comentarios se quedan así hasta que no podamos solucionar el 
	//inicio de sesión.

	/*private static void AdministratorMenu(String email) {
		// TODO Auto-generated method stub
		try {
	    	  int choice;
	    	  do {
	    		  System.out.println("Choose an option");
	    		  System.out.println("1. Add a new administrator");
	    		  System.out.println("2. Print all administrators in DB");
	    		  System.out.println("0. Exit");
	    		  choice = Integer.parseInt(reader.readLine());
	    		  
	    		  switch(choice) {
	    		  case 1:
	    			  //createAdministrator();
	    			  
	    		  case 2:
	    			  //getAllAdministrators();
	    		  case 0:
	    			  jdbcmanager.disconnect();
	    			  System.exit(0);
	    		  }
	    		  
	    	  }while(choice!=0);
	    	  
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
		
	}*/

	private static void singUpUser() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce your email of the user: ");
			String email=reader.readLine();
			System.out.println("Introduce your password: ");
			String password=reader.readLine();
			
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			
			System.out.println("Introduce the role of the user. 1:client, 2:doctor 3:administrator 4:pharmacist ");
			Integer role=Integer.parseInt(reader.readLine());
			
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
		administratormanager.createAdministrator(a);
	}
	
	/*public Administrator(String name, String surnmame, Integer phone_number, String email)*/
	
	/*private static void createOwner() throws Exception
	{
		System.out.println("Type the name of the owner");
		String name = reader.readLine();
		System.out.println("Type the phone of the owner");
		Integer phone = Integer.parseInt(reader.readLine());
		System.out.println("Type the cardnumber of the owner");
		Integer cardnumber = Integer.parseInt(reader.readLine());
		System.out.println("Type the email of the owner");
		String email = reader.readLine();
		
		Owner o = new Owner(name, email, phone, cardnumber);
		
		ownermanager.createOwner(o);
	}
	 */
	
	
	private static void createClient () throws Exception 
	{
		
	}
	
	private static void createPharmacist () throws Exception 
	{
		
	}
	
	private static void createDoctor () throws Exception 
	{
		
	}
}	
	
	
