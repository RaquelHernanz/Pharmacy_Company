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
    		  //System.out.println("1. Add a new administrator");
    		  //Después de realizar el login user o el sign-up según el usuario se pondrá el menú
    		  choice = Integer.parseInt(reader.readLine());
    		  
    		  switch(choice) {
    		  case 1 -> //
    		  {
    			  System.out.println("Option 1");
    			  login();
    			  //createAdministrator();
    		  }
    		  case 2 ->
    		  {
    			  System.out.println("Option 2");
    			  singUpUser();

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
		
        if(u!=null && u.getRole().getName().equals("")) {
        	System.out.println("Login of owner successful");
        	//call for administrator submenu;
            /*AdministratorMenu(email);*/
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

	
/*public class Menu {

	private static JDBCManager jdbcmanager;
	private static OwnerManager ownermanager;
	private static PetManager petmanager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		jdbcmanager = new JDBCManager();
		ownermanager = new JDBCOwnerManager(jdbcmanager); 
		petmanager = new JDBCPetManager(jdbcmanager);
		
		
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add a new owner.");
				System.out.println("2. Print all the owners in DB.");
				System.out.println("3.  Add a new pet in the DB");
				System.out.println("4.  Print all the pets of an owner.");
				System.out.println("0. Exit.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					createOwner();
					break;
				case 2:
					getAllowners();
				case 3:
					createPet();
				case 4:
					printOwnersPets();
				case 0:
					jdbcmanager.disconnect();
					System.exit(0);
					
				}
				
			}while(true);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
	
	private static void createOwner() throws Exception
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
	
	private static void createPet() throws Exception
	{
		System.out.println("Type the name of the pet");
		String name = reader.readLine();
		System.out.println("Type if it's cured or not");
		Boolean cured = Boolean.valueOf(reader.readLine());
		System.out.println("Type the type of animal(dog, cat, bird, hamster)");
		String typeOfAnimal = reader.readLine();
		System.out.println("Type the dob of the pet in formal yyyy/mm/dd");
		String dob_str = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dob = (Date) df.parse(dob_str);
		System.out.println("Type the coat of the pet");
		String coat = reader.readLine();
		System.out.println("Type the owner id of the pet");
		Integer owner_id = Integer.parseInt(reader.readLine());
		
		Owner o = ownermanager.searchOwnerById(owner_id);
		
		Pet p = new Pet(coat,  name,cured, typeOfAnimal, dob, o);
		petmanager.addPet(p);
		
	}
	private static void getAllowners() throws Exception{
		
		List<Owner> owners = null;
		
		owners = ownermanager.getListOfOwners();
		
		System.out.println(owners);
		
	}
	
	private static void printOwnersPets() throws Exception{
		
		List<Pet> pets = null;
		
		System.out.println("Type the id of the owner");
		Integer o_id = Integer.parseInt(reader.readLine());
		
		pets = petmanager.getPetsOfanOwner(o_id);
		
		System.out.println(pets);
		
	}*/	
}
