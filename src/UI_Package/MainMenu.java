package UI_Package;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
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
    		  System.out.println("2. SignUp User");
    		  System.out.println("0. Exit the aplication");
    		  choice = Integer.parseInt(reader.readLine());
    		  
    		  switch(choice) {
    		  case 1 -> //
    		  {
    			  try 
    			  {
    				  createClient();
    			  }catch (Exception e)
    			  {
    				  /*e.printStackTrace();*/
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 2 ->
    		  {
    			  try 
    			  {
    				  createAdministrator();
    			  }catch (Exception e)
    			  {
    				  /*e.printStackTrace();*/
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 3 ->
    		  {
    			  try 
    			  {
    				  getAlladministrators();
    			  }catch (Exception e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 4 ->
    		  {
    			  try 
    			  {
    				  searchAdministrator();
    			  }catch (Exception e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 5 ->
    		  {
    			  try 
    			  {
    				  searchAdministratorbyId ();
    			  }catch (Exception e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 0 ->  
    		  {   
    			  jdbcmanager.disconnect();
    			  System.out.println("At least this doesnt fail");
    			  System.exit(0);
    		  }
    		  }
    		  
    	  }while(choice != 0);
    	  

	}catch(Exception e) 
      {
		System.out.println("At least this doesnt fail");
  	     e.printStackTrace();
  	  }
	}
	
	private static void login() throws java.sql.SQLException, Exception 
	{
		// TODO Auto-generated method stub
		System.out.println("Introduce your email: ");
		String email = reader.readLine();
		
		System.out.println("Introduce your password: ");
		String passwd = reader.readLine();
		
		User u = usermanager.checkPassword(email, passwd);
		
		if (u == null) 
		{
			System.out.println("Not able to login");
		}
		if(u!=null & u.getRole().getName().equals("client"))
		{
			System.out.println("Login of client successful!");
			
			//We can now call a menu for each type of user
		} 
		else if (u!=null & u.getRole().getName().equals("doctor")) 
		{
			System.out.println("Login of doctor successful!");
			
		}else if (u != null & u.getRole().getName().equals("administrator")) 
		{
			System.out.println("Login of administrator successful!");
			
		}else if (u != null & u.getRole().getName().equals("pharmacist"))
		{
			System.out.println("Login of pharmacist successful!");
		}
		
	}
	
	
	
	private static void signUpUser() {
		// TODO Auto-generated method stub
		try {
			System.out.print("Write an email for your account: ");
			String email = reader.readLine();
			System.out.print("Write a password for your account: ");
			String password = reader.readLine();
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			
			System.out.println("Introduce the role of the user");
			System.out.println("1: client, 2: doctor 3: administrator 4: pharmacist");
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);
			User u = new User(email, pass, r);
			usermanager.newUser(u);
			//Hay que añadir crear cada tipo de usuarios
			System.out.println("Sign-Up and Registration in the system done, now try to login");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/*private static void menuAdministrator (String email) throws Exception
	{
		Administrator a = null;
		Integer adchoice;
		try {
			
			a = administratormanager.searchAdministratorByName();
			if (a == null) 
			{
				System.out.println("Write down your information into the system");
				System.out.println("Remember that your email must be equal to the login");
				createAdministrator();
				//This part is required in order to gurantee the info was kept
		    }
		do {	
		//Comprobar que el adminitrator
		System.out.println("Choose an initial option");
		System.out.println("1. Add new adminitrator to the system");
		System.out.println("2. Add new client to the system");
		System.out.println("3. Add new pharmacist to the system");
		/*System.out.println("4. Add new doctor to the system");
		System.out.println("5. Remove administrator by Email");
		System.out.println("6. Remove client by Email");
		System.out.println("7. Remove pharmacist by Email");
		System.out.println("8. Remove doctor by Email");
		System.out.println("9. ");
		adchoice = Integer.parseInt(reader.readLine());
		switch (adchoice) 
		{
		  case 1->
		  {
			createAdministrator();
		  }
		  case 2 ->
		  {
			createClient();
		  }
		  case 3 ->
		  {
			createPharmacist();  
		  }
		  case 4 ->
		  {
			createDoctor();
		  }
		}
		
		}while (adchoice !=0);
		
		}catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Not found");
		}
	}*/
	
	
	
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
	
	private static void searchAdministrator()throws Exception
	{
		System.out.println("Write the name of the person");
		String name = reader.readLine();
		System.out.println("Write the surname of the person");
		String surname = reader.readLine();
		Administrator a = administratormanager.searchAdministratorByName(name,surname);
		System.out.println(a.toString());
	}
	
	private static void searchAdministratorbyId()throws Exception
	{
		System.out.println("Write the id of the person");
		Integer id = Integer.parseInt(reader.readLine());
		Administrator a = administratormanager.searchAdministratorById(id);
		System.out.println(a.toString());
	}
	
    private static void getAlladministrators() throws Exception{
		
		List<Administrator> administrators = null;
		
		administrators = administratormanager.getListOfAdministrators();
		
		System.out.println(administrators);
		
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
		//Pilar, ya sabes que hay que quitar los souts
	}
	
	private static void getAllClients ()throws Exception 
	{
		List <Client> clients = null;
		clients = clientmanager.getListOfClients();
		System.out.println(clients);
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
	
	private static void AddMedicinetoCatalogue (Integer pharmacist_id) throws Exception 
	{
		//Rellenar los datos de la medicina, incluyendo un stock inicial.
		
	}
	
	private static void ClientBuyMedicine (Integer client_id) throws Exception
	{
		//Tiene que comprar con el nombre de la medicina
		//Poner la cantidad que quieren
		//Le imprimirá la factura con la cantidad de medicinas.
		//Una vez comprado, hay que modificar automáticamente el atributo stock, aunque podemos hacerlo fuera de este método
	}
	
	private static void DoctorBuyMedicine (Integer doctor_id) throws Exception
	{
		
	}
	
	private static void AdmintratorMakeOrder (Integer administrator_id) 
	{
		//El administrator hará una order de reestock automática.
		//El médicamento y el pharmacist deben de coindidir (no puedes perdirle el stock a otro pharmacist).
		//Automáticamente el stock se actualizará a la cantidad que siempre pondremos.
		//También podemos imprimir la factura de los artículos y el precio total basado en un porcentaje del precio original.
	}
	
	//¿Qué métodos de modificación incluimos?
	//Los atributos básicos de cada usuario
	//Los de modifica la médicina, excepto el stock y id.
	//No podemos modificar las comprar
	
	//Métodos para ver listados de datos
	//De todo tipo, medicinas, ordenes, clientes... Otra cosa es quien pueda acceder a todos.
}

	
	
