package PharmacyCompanyXML;

import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyInterfaces.DoctorManager;
import PharmacyCompanyInterfaces.MedicineManager;
import PharmacyCompanyInterfaces.OrderManager;
import PharmacyCompanyInterfaces.PharmacistManager;
import PharmacyCompanyJDBC.JDBCManager;

public class XMLManagerImpl implements XMLManager 
{
	JDBCManager manager;
	AdministratorManager administratormanager;
	PharmacistManager pharmacistmanager;
	ClientManager clientmanager;
	DoctorManager doctormanager;
	OrderManager ordermanager;
	MedicineManager medicinemanager;
	
	
	//XML de client, pharmacist, doctor, administrator, medicine, order to xml
	
}
