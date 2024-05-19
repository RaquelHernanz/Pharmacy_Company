package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Order;

public interface OrderManager 
{
	public void addOrder (Order o);
	public List <Order> getListOfOrders ();
	public List <Order> getOrderOfPharmacist (Integer pharmacist_id);
	public Order searchOrderByCode(Integer code);
	public List <Order> getOrderOfAdministrator (Integer administrator_id);
	public void assignMedicinetoOrder (Integer medicine_code, Integer order_code,Float total_price,Integer quantity) throws Exception;
	public void deleteOrderbyCode (Integer order_code) throws Exception;
}

