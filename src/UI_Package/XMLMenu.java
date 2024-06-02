package UI_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyInterfaces.DoctorManager;
import PharmacyCompanyInterfaces.MedicineManager;
import PharmacyCompanyInterfaces.OrderManager;
import PharmacyCompanyInterfaces.PharmacistManager;
import PharmacyCompanyInterfaces.XMLManager;
import PharmacyCompanyJDBC.JDBCAdministratorManager;
import PharmacyCompanyJDBC.JDBCClientManager;
import PharmacyCompanyJDBC.JDBCDoctorManager;
import PharmacyCompanyJDBC.JDBCManager;
import PharmacyCompanyJDBC.JDBCMedicineManager;
import PharmacyCompanyJDBC.JDBCOrderManager;
import PharmacyCompanyJDBC.JDBCPharmacistManager;
import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Client;
import PharmacyCompanyPOJOs.Doctor;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Order;
import PharmacyCompanyPOJOs.Pharmacist;
import PharmacyCompanyXML.XMLManagerImpl;

public class XMLMenu {

	
	private static JDBCManager jdbcmanager;
	private static AdministratorManager administratormanager;
	private static ClientManager clientmanager;
	private static PharmacistManager pharmacistmanager;
	private static DoctorManager doctormanager;
	private static MedicineManager medicinemanager;
	private static OrderManager ordermanager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	private static XMLManager xmlmanager;
	
	
	
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
		xmlmanager = new XMLManagerImpl();
		
		
		try {
			int choice;
			
			do {
				
				System.out.println("Choose an option for XML");
				System.out.println("1. EXPORT TO XML AN PHARMACIST");
				System.out.println("2. EXPORT TO XML AN ADMINISTRATORS");
				System.out.println("3. EXPORT TO XML AN CLIENTS");
				System.out.println("4. EXPORT TO XML AN DOCTORS");
				System.out.println("5. EXPORT TO XML AN ORDERS");
				System.out.println("6. EXPORT TO XML AN MEDICINE");
				System.out.println("7. IMPORT FROM XML AN CLIENT");
				System.out.println("8. IMPORT FROM XML AN DOCTOR");
				System.out.println("9. IMPORT FROM XML AN PHARMACIST");
				System.out.println("10. IMPORT FROM XML AN ADMINISTRATOR");
				System.out.println("11. IMPORT FROM XML AN MEDICINE");
				System.out.println("12. IMPORT FROM XML AN ORDER");
				System.out.println("0. EXIT");
								
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					try 
					{
						System.out.println("Introduce the email of the pharmacist: ");
						String email = reader.readLine();
						Pharmacist p = pharmacistmanager.searchPharmacistByEmail(email);
						System.out.println(p.toString());
						xmlmanager.pharmacist2Xml(p.getId());
						xmlmanager.simpleTransform("./xmls/Pharmacist.xml", "./xmls/pharmacist-style.xslt", "./xmls/pharmacist.html");
						
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					
					break;
				case 2:
					try 
					{
						System.out.println("Introduce the email of the administrator: ");
						String email = reader.readLine();
						Administrator a = administratormanager.searchAdministratorByEmail(email);
						System.out.println(a.toString());
						xmlmanager.administrator2Xml(a.getId());
						
						xmlmanager.simpleTransform("./xmls/Administrator.xml","./xmls/administrator-style.xslt","./xmls/administrator.html");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					
					break;
				case 3: 
					try 
					{
						System.out.println("Introduce the email of client: ");
						String email = reader.readLine();
						Client c  = clientmanager.searchClientByEmail(email);
						System.out.println(c.toString());
						xmlmanager.client2Xml(c.getId());
						xmlmanager.simpleTransform("./xmls/Client.xml", "./xmls/client-style.xslt", "./xmls/client.html");
						
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					
					break;
					
				case 4:
					try 
					{
						System.out.println("Introduce the email of doctor: ");
						String email = reader.readLine();
						Doctor d = doctormanager.searchDoctorByEmail(email);
						System.out.println(d.toString());
						xmlmanager.doctor2Xml(d.getId());
						xmlmanager.simpleTransform("./xmls/Doctor.xml", "./xmls/doctor-style.xslt", "./xmls/doctor.html");
						
						
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 5:
					try 
					{
						System.out.println("Introduce the code of the order: ");
						Integer code = Integer.parseInt(reader.readLine());
						Order o = ordermanager.searchOrderByCode(code);
						System.out.println(o.toString());
						xmlmanager.Order2Xml(code);
						xmlmanager.simpleTransform("./xmls/Order.xml", "./xmls/order-style.xslt", "./xmls/order.html");
						
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 6:
					try 
					{
						System.out.println("Introduce the name of the medicine: ");
						String name = reader.readLine();
						Medicine m = medicinemanager.searchMedicineByName(name);
						System.out.println(m.toString());
						xmlmanager.Medicine2Xml(m.getCode());
						xmlmanager.simpleTransform("./xmls/Medicine.xml", "./xmls/medicine-style.xslt", "./xmls/medicine.html");
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 7:
					try 
					{
						//Empty example
						File file = new File ("./xmls/Client-External.xml");
						Client c  = xmlmanager.xmltoClient(file);
						System.out.println(c.toString());
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 8:
					try 
					{
						//Empty example
						File file = new File ("./xmls/Doctor-External.xml");
						Doctor d = xmlmanager.xmltoDoctor(file);
						System.out.println(d.toString());
						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 9:
					try 
					{
						File file = new File ("./xmls/Pharmacist-External.xml");
						Pharmacist p = xmlmanager.xmltoPharmacist(file);
						System.out.println(p.toString());

						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 10:
					try 
					{
						File file = new File ("./xmls/Administrator-External.xml");
						Administrator a = xmlmanager.xmltoAdministrator(file);
						System.out.println(a.toString());

						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 11:
					try 
					{
						//Empty example
						File file = new File ("./xmls/Medicine-External.xml");
						Medicine m = xmlmanager.xmltoMedicine(file);
						System.out.println(m.toString());

						
					}catch (Exception e) 
					{
						System.out.println("An error happened try again");
					}
					break;
				case 12:
					try 
					{
						//Empty example
						File file = new File ("./xmls/Order-External.xml");
						Order o = xmlmanager.xmltoOrder(file);
						System.out.println(o.toString());

						
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
