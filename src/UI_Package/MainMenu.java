package UI_Package;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyInterfaces.DoctorManager;
import PharmacyCompanyInterfaces.MedicineManager;
import PharmacyCompanyInterfaces.OrderManager;
import PharmacyCompanyInterfaces.PharmacistManager;
import PharmacyCompanyInterfaces.UserManager;
import PharmacyCompanyJDBC.JDBCAdministratorManager;
import PharmacyCompanyJDBC.JDBCClientManager;
import PharmacyCompanyJDBC.JDBCDoctorManager;
import PharmacyCompanyJDBC.JDBCManager;
import PharmacyCompanyJDBC.JDBCMedicineManager;
import PharmacyCompanyJDBC.JDBCOrderManager;
import PharmacyCompanyJDBC.JDBCPharmacistManager;
import PharmacyCompanyJPA.JPAUserManager;
import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Client;
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Order;
import PharmacyCompanyPOJOs.Pharmacist;
import PharmacyCompanyPOJOs.Role;
import PharmacyCompanyPOJOs.User;



public class MainMenu {

	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static MedicineManager medicinemanager;
	private static OrderManager ordermanager;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static UserManager usermanager;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  
      jdbcmanager = new JDBCManager();
      administratormanager= new JDBCAdministratorManager(jdbcmanager);
      clientmanager = new JDBCClientManager(jdbcmanager);
      doctormanager = new JDBCDoctorManager (jdbcmanager);
      pharmacistmanager = new JDBCPharmacistManager (jdbcmanager);
      medicinemanager = new JDBCMedicineManager (jdbcmanager);
      ordermanager = new JDBCOrderManager (jdbcmanager);
      usermanager= new JPAUserManager();
      Administrator a = null;
      Client c = null;
      Doctor d = null;
      Pharmacist p = null;
      Order o = null;
      Medicine m = null;
      
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
    				 createPharmacist ();
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
    				  d = doctormanager.searchDoctorByNameEmail("Kevin", "Parker", "kevin@gmail.com");
    				  System.out.println(d.toString());	  
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
    				  System.out.println("Introduce your email: ");
    				  String email = reader.readLine();
    				  a = administratormanager.searchAdministratorByNameEmail("john","smith",email);
    				  System.out.println(a.toString());
    			  }catch (Exception e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 6 ->
    		  {
    			  try 
    			  {
    				  
    				  createPharmacist();
    				 
    			  }catch (Exception e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 7 ->
    		  {
    			  try 
    			  {
    				  System.out.println("Introduce the id of the person: ");
    				  Integer id  = Integer.parseInt(reader.readLine());
    				  p = pharmacistmanager.searchPharmacistById(id);
    				  addMedicinetoCatalogue(p);
    				 
    			  }catch (Exception e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 8 ->
    		  {
    			  try 
    			  {
    				  getAllMedicine();
    				 
    			  }catch (Exception e)
    			  {
    				  e.printStackTrace();
    				  System.out.println("Resolve it");
    			  } 
    		  }
    		  case 9 ->
    		  {
    			  try 
    			  {
    				  System.out.println("Introduce the id of the person: ");
    				  Integer code  = Integer.parseInt(reader.readLine());
    				  medicinemanager.deleteMedicinebyCode(code);
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
	//Se están haciendo pruebas de tipo 
	
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
			Administrator a = administratormanager.searchAdministratorByEmail(email);
			System.out.println(a.toString());
			
		}else if (u != null & u.getRole().getName().equals("pharmacist"))
		{
			System.out.println("Login of pharmacist successful!");
		}
		
	}
	
	
	//Algunos de los métodos pueden omitirse y ponerlos directamnente en los cases
	
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
			System.out.println("Sign-Up and Registration in the system done, now try to login");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
       private static void updatePassword() throws Exception {
		
		System.out.println("Email: ");
		String email = reader.readLine();
				
		System.out.println("Enter current Password");
		String passwd = reader.readLine();
		
		System.out.println("Enter new Password");
		String new_passwd = reader.readLine();
				
		User u = usermanager.checkPassword(email, passwd);
				
		if(u!=null)
		{
			usermanager.changePassword(u, new_passwd);
			//El método de changePassword está incompleto 
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
	
	private static void addMedicinetoCatalogue (Pharmacist pharmacist) throws Exception, SQLException 
	{
		//Rellenar los datos de la medicina, incluyendo un stock inicial.
		
		/*String name, Float price,String instructions, Integer stock, Date expirations, Pharmacist pharmacist,
			Blob image*/
		
		System.out.print("Introduce your name of the medicine:");
		String name = reader.readLine();
		System.out.print("Introduce the price: ");
		Float price = Float.parseFloat(reader.readLine());
		System.out.print("Introduce the instructions:");
		String instructions = reader.readLine();
		System.out.print("Introduce the initial stock:");
		Integer stock = Integer.parseInt(reader.readLine());
		System.out.print("Introduce a date in this structure YYYY-MM-DD:");
		String date_string = reader.readLine();
		Date date = Date.valueOf(date_string);
		System.out.print("Introduce a date in this structure YYYY-MM-DD:");
		Boolean prescribed = Boolean.parseBoolean(reader.readLine());
		System.out.println("Introduce the route of your blob:");
		String blobString = reader.readLine();
		byte [] blobBytes = blobString.getBytes();
		
		Medicine m = new Medicine (name,instructions,price,stock,date,pharmacist,blobBytes,prescribed);
		System.out.println(m.toString());
		medicinemanager.addMedicine(m);
	}
	
     private static void getAllMedicine() throws Exception{
		
		List<Medicine> medicines = null;
		medicines = medicinemanager.getListofMedicines();
		System.out.println(medicines);
	}
	
     
     
	
	private static void ClientBuyMedicine (Integer client_id) throws Exception
	{
		//Tiene que comprar con el nombre de la medicina
		//Poner la cantidad que quieren
		//Le imprimirá la factura con la cantidad de medicinas.
		//Una vez comprado, hay que modificar automáticamente el atributo stock, aunque podemos hacerlo fuera de este método
		medicinemanager.getListofMedicines();
		System.out.print("Which medicine do you want to buy:");
		String name_medicine = reader.readLine();
		Medicine m = medicinemanager.searchMedicineByName(name_medicine);
		System.out.print("How much do you want to buy?:");
		Integer quantity = Integer.parseInt(reader.readLine());
		
		//Hay que garantizar que el stock es suficiente para la compra
		if (quantity > m.getStock() && quantity <0 && quantity == 0)
		{
			System.out.print("You cannot buy anything with that quantity, try again");
		}else 
		{
			System.out.print("Purchase verified");
			Integer medicine_code = m.getCode();
			Float bill_number = quantity*(m.getPrice());
			String Bill = "Medicine:"+name_medicine+" Quantity: "+quantity+" Bill: "+bill_number;
			System.out.print(Bill);
			medicinemanager.assignMedicinetoClient(client_id, medicine_code, bill_number, quantity);
			medicinemanager.updateStock(m.getStock()-quantity, medicine_code);
		}
		
	}
	
	
	
	private static void DoctorBuyMedicine (Integer doctor_id) throws Exception
	{
		medicinemanager.getListofMedicines();
		System.out.print("Which medicine do you want to buy:");
		String name_medicine = reader.readLine();
		Medicine m = medicinemanager.searchMedicineByName(name_medicine);
		System.out.print("How much do you want to buy?:");
		Integer quantity = Integer.parseInt(reader.readLine());
		
		//Hay que garantizar que el stock es suficiente para la compra
		if (quantity > m.getStock() && quantity <0 && quantity == 0)
		{
			System.out.print("You cannot buy anything with that quantity, try again");
		}else 
		{
			System.out.print("Purchase verified");
			Integer medicine_code = m.getCode();
			Float bill_number = quantity*(m.getPrice());
			String Bill = "Medicine:"+name_medicine+" Quantity: "+quantity+" Bill: "+bill_number+"€";
			System.out.print(Bill);
			medicinemanager.assignMedicinetoClient(doctor_id, medicine_code, bill_number, quantity);
		}
	}
	
	private static void PharmacistMakeOrder (Integer pharmacist_id) 
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

	
	
