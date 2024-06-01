package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Order;

public interface OrderManager 
{
	public void addOrder (Order o);
	public List <Order> getListOfOrders ();
	public List <Order> getOrderOfPharmacist (Integer pharmacist_id);
	public List <Order> getOrderOfAdministrator (Integer administrator_id);
	public void deleteOrderbyCode (Integer order_code) throws Exception;
	public Order searchOrderByPrice (Float total_price);
	public Order searchOrderByCode (Integer code) throws Exception;
	
}

