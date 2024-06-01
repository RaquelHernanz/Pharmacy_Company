package UI_Package;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import PharmacyCompanyPOJOs.Pharmacist;

public class XMLMenu {

	
	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static MedicineManager medicinemanager;
	private static OrderManager ordermanager;
	private static UserManager usermanager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		jdbcmanager = new JDBCManager();
		administratormanager = new JDBCAdministratorManager(jdbcmanager);
		clientmanager = new JDBCClientManager(jdbcmanager);
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		pharmacistmanager = new JDBCPharmacistManager(jdbcmanager);
		medicinemanager = new JDBCMedicineManager(jdbcmanager);
		ordermanager = new JDBCOrderManager(jdbcmanager);
		usermanager = new JPAUserManager();
		
		
		try {
			int choice;
			
			do {
				
				System.out.println("Choose an option for XML");
				System.out.println("1. EXPORT TO XML AN PHARMACIST");
				System.out.println("2. EXPORT TO XML AN ADMINISTRATORS");
				System.out.println("3. EXPORT TO XML AN CLIENTS");
				System.out.println("4. EXPORT TO XML AN DOCTORS");
				System.out.println("5. EXPORT TO XML AN ORDERS");
				System.out.println("6. EXPORT TO XML AN MEDICINES");
				System.out.println("7. IMPORT FROM XML AN CLIENTS");
				System.out.println("8. IMPORT FROM XML AN DOCTORS");
				System.out.println("9. IMPORT FROM XML AN PHARMACISTS");
				System.out.println("10. IMPORT FROM XML AN ADMINISTRATORS");
				System.out.println("11. IMPORT FROM XML AN MEDICINES");
				System.out.println("12. IMPORT FROM XML AN ORDERS");
				System.out.println("0. EXIT");
								
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					try 
					{
						System.out.println("Introduce the email of the pharmacist: ");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					
					break;
				case 2:
					try 
					{
						System.out.println("Introduce the email of the administrator: ");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					
					break;
				case 3: 
					try 
					{
						System.out.println("Introduce the email of the client: ");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					
					break;
					
				case 4:
					try 
					{
						System.out.println("Introduce the email of the doctor: ");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 5:
					try 
					{
						System.out.println("Introduce the code of the order: ");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 6:
					try 
					{
						System.out.println("Introduce the name of the medicine: ");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 7:
					try 
					{
						System.out.println("An error happened try again");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 8:
					try 
					{
						System.out.println("An error happened try again");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 9:
					try 
					{
						System.out.println("An error happened try again");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 10:
					try 
					{
						System.out.println("An error happened try again");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 11:
					try 
					{
						System.out.println("An error happened try again");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 12:
					try 
					{
						System.out.println("An error happened try again");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 0:
					System.out.println("Exiting application.");
					jdbcmanager.disconnect();
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}

}
