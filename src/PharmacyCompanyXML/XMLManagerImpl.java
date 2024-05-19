package PharmacyCompanyXML;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyInterfaces.DoctorManager;
import PharmacyCompanyInterfaces.MedicineManager;
import PharmacyCompanyInterfaces.OrderManager;
import PharmacyCompanyInterfaces.PharmacistManager;
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

public class XMLManagerImpl implements XMLManager 
{
	JDBCManager manager;
	AdministratorManager administratormanager;
	PharmacistManager pharmacistmanager;
	ClientManager clientmanager;
	DoctorManager doctormanager;
	OrderManager ordermanager;
	MedicineManager medicinemanager;
	
	public void administrator2Xml(Integer id) {
		Administrator a = null;
		manager = new JDBCManager();
		administratormanager = new JDBCAdministratorManager(manager);
		ordermanager = new JDBCOrderManager(manager);
		List<Order> orders = new LinkedList<Order>(); 
		
		try {
			
			orders = administratormanager.getListOfOrders(); //falta hacer este método en JDBCAdministratorManager
			a = administratormanager.searchAdministratorById(id);
			a.getOrders();
			
			//export the administrator to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Administrator.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Administrator.xml");
			marshaller.marshal(a,file);
			System.out.println(a);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void client2Xml(Integer id) {
		Client c = null;
		manager = new JDBCManager();
		clientmanager = new JDBCClientManager(manager);
		List<Medicine> medicines = new LinkedList<Medicine>();
		
		try {
			
			medicines = clientmanager.getListOfMedicines(); //falta hacer este método en JDBCClientManager
			c = clientmanager.searchClientById(id);
			c.getMedicines();
			
			//export the client to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Client.xml");
			marshaller.marshal(c, file);
			System.out.println(c);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pharmacist2Xml(Integer id) {
		Pharmacist p = null;
		manager = new JDBCManager();
		pharmacistmanager = new JDBCPharmacistManager(manager);
		List<Medicine> medicines = new LinkedList<Medicine>();
		
		try {
			medicines = pharmacistmanager.getListOfMedicines(); //falta
			p = pharmacistmanager.searchPharmacistById(id);
			p.getMedicines_created();
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacist.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Pharmacist.xml");
			marshaller.marshal(p,file);
			System.out.print(p);
			
		}catch(Exception e) {
			
		}
	}
	
	public void doctor2Xml(Integer id) {
		Doctor d = null;
		manager = new JDBCManager();
		doctormanager = new JDBCDoctorManager(manager);
		List<Medicine> medicines = new LinkedList<Medicine>();
		
		try {
			medicines = doctormanager.getListOfMedicines(); //falta
			d = doctormanager.searchDoctorById(id);
			d.getMedicines();
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Doctor.xml");
			marshaller.marshal(d, file);
			System.out.println(d);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Medicine2Xml(Integer code) {
		Medicine m = null;
		manager = new JDBCManager();
		medicinemanager = new JDBCMedicineManager(manager);
		
		try {
			m = medicinemanager.searchMedicineByCode(code);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Medicine.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Medicine.xml");
			marshaller.marshal(m,file);
			System.out.println(m);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Order2Xml(Integer code) {
		Order o = null;
		manager = new JDBCManager();
		ordermanager = new JDBCOrderManager(manager);
		
		try {
			o = ordermanager.searchOrderByCode(code);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File ("Order.xml");
			marshaller.marshal(o,file);
			System.out.println(o);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
