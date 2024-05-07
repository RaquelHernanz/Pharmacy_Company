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


