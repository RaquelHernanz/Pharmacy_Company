package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Order;

public interface OrderManager 
{
	public void addOrder (Order o);
	public List <Order> getListOfOrders ();
	public List <Order> getOrderOfPharmacist (Integer pharmacist_id);
	public List <Order> getOrderOfAdministrator (Integer administrator_id);
	
}

