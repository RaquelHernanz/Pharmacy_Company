package PharmacyCompanyXML;

import java.io.File;

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
		
		try {
			
			a = administratormanager.searchAdministratorById(id);
			
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
		
		try {
			
			c = clientmanager.searchClientById(id);
			
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
	
	//XML , pharmacist, doctor, medicine, order to xml
	
}
