package UI_Package;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyInterfaces.UserManager;
import PharmacyCompanyJDBC.JDBCAdministratorManager;
import PharmacyCompanyJDBC.JDBCClientManager;
import PharmacyCompanyJDBC.JDBCManager;
import PharmacyCompanyJPA.JPAUserManager;
import PharmacyCompanyPOJOs.Role;
import PharmacyCompanyPOJOs.User;

public class MainMenu {

	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  
      jdbcmanager=new JDBCManager();
      administratormanager= new JDBCAdministratorManager(jdbcmanager);
      clientmanager= new JDBCClientManager(jdbcmanager);
      usermanager= new JPAUserManager();
    //no se porque no me funciona esto
      
      try {
    	  int choice;
    	  do {
    		  System.out.println("Choose an option");
    		  System.out.println("1. Login User");
    		  System.out.println("2. Sing-up new user");
    		  //System.out.println("1. Add a new administrator");
    		  System.out.println("0. Exit");
    		  choice = Integer.parseInt(reader.readLine());
    		  
    		  switch(choice) {
    		  case 1:
    			  login();
    			  //createAdministrator();
    		  case 5:
    			  singUpUser();
    		  case 0:
    			  jdbcmanager.disconnect();
    			  System.exit(0);
    		  }
    		  
    	  }while(choice!=0);
    	  
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
		
        if(u!=null && u.getRole().getName().equals("onwner")) {
        	System.out.println("Login of owner successful");
        	//call for administrator submenu;
            AdministratorMenu(email);
        }
		
		
	}

	private static void AdministratorMenu(String email) {
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
		
	}

	private static void singUpUser() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce the email of the user: ");
			String email=reader.readLine();
			System.out.println("Introduce the password: ");
			String password=reader.readLine();
			
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			
			System.out.println("Introduce the role of the user. 1: owner, 2:vet ");
			Integer role=Integer.parseInt(reader.readLine());
			
			Role r = usermanager.getRole(role);
			
			User u= new User(email,pass,r);
			
			usermanager.newUser(u);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
