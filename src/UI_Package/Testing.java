package UI_Package;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
import UI_Package.MainMenu;

public class Testing {

	//The main purpose of this class is the local testing of the functions of JDBC
	
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
	    		  System.out.println("1. Create Administrator");
	    		  System.out.println("2. Create Client");
	    		  System.out.println("0. Exit");
	    		  //Después de realizar el login user o el sign-up según el usuario se pondrá el menú
	    		  choice = Integer.parseInt(reader.readLine());
	    		  
	    		  switch(choice) 
	    		  {
	    		    case 1 -> //Creación de un administrador
	    		    {
	    		    
	    		    	try {
	    			     MainMenu.createAdministrator();
	    		    	}catch (Exception e)
	    		    	{
	    		    		System.out.println("Didnt work out");
	    		    	}
	    		    }
	    		    case 2 -> //Creación de un cliente
	    		    {
	    			  System.out.println("At least this doesnt fail");   			  
	    		    }
	    		    case 0 -> //Salir de la base de datos
	    		    {   
	    		    	jdbcmanager.disconnect();
	    			    System.out.println("At least this doesnt fail");
	    			    System.exit(0);
	    		    }
	    		  }
	    		  
	    	  }while(choice > -1);
	    	  

		}catch(Exception e) {
	  	  e.printStackTrace();
	  	  
		}


	}

}
