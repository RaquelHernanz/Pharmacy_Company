package PharmacyCompanyJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import PharmacyCompanyInterfaces.MedicineManager;
import PharmacyCompanyInterfaces.OrderManager;
import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Order;
import PharmacyCompanyPOJOs.Pharmacist;



public class JDBCOrderManager implements OrderManager
{
	private JDBCManager manager;
	private JDBCPharmacistManager pharmacistmanager;
	private JDBCAdministratorManager administratormanager;
	private JDBCMedicineManager medicinemanager;
	
	public JDBCOrderManager (JDBCManager m) 
	{
		this.manager = m;
		this.pharmacistmanager = new JDBCPharmacistManager(manager);
		this.administratormanager = new JDBCAdministratorManager (manager);
		this.medicinemanager = new JDBCMedicineManager (manager);
	}
	
	public void addOrder (Order o) 
	{
		// TODO Auto-generated method stub
				try {
				 String sql = "INSERT INTO orders (total_price,quantity,pharmacist_id,administrator_id,medicine_id)"
						 + "VALUES(?,?,?,?,?)";
				 
				 PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				 
				 prep.setFloat(1, o.getTotalprice());
				 prep.setInt(2,o.getQuantity());
				 prep.setInt(3,o.getPharmacist().getId());
				 prep.setInt(4,o.getAdministrator().getId());
				 prep.setInt(5,o.getMedicine().getCode());
				 
				 prep.executeUpdate();
				 
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
	}
	

	public Order searchOrderByPrice (Float total_price) 
	{
		
		// TODO Auto-generated method stub
        Order o = null;
		
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM orders WHERE  total_price ="+total_price;
			ResultSet rs = stmt.executeQuery(sql);
			Integer code = rs.getInt("code_o");
			Float totalprice = rs.getFloat("total_price");
			Integer quantity_o  = rs.getInt("quantity");
			Integer pharmacist = rs.getInt("pharmacist_id");
			Integer administrator_id = rs.getInt("administrator_id");
			Integer medicine_id = rs.getInt("medicine_id");
			Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist);
			Administrator a = administratormanager.searchAdministratorById(administrator_id);
			Medicine m = medicinemanager.searchMedicineByCode(medicine_id);
				
			o = new Order (code,totalprice,quantity_o,p,a,m);
				
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	
	public List <Order> getListOfOrders ()
	{
       List<Order> orders = new LinkedList <Order>();
		
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM orders";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer code = rs.getInt("code_o");
				Float totalprice = rs.getFloat("total_price");
				Integer quantity  = rs.getInt("quantity");
				Integer pharmacist_id = rs.getInt("pharmacist_id");
				Integer administrator_id = rs.getInt("administrator_id");
				Integer medicine_id = rs.getInt("medicine_id");
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Administrator a = administratormanager.searchAdministratorById(administrator_id);
				Medicine m = medicinemanager.searchMedicineByCode(medicine_id);
				
				Order o = new Order (code,totalprice,quantity,p,a,m);
				orders.add(o);
				
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public Order searchOrderByCode (Integer code) throws Exception
	{
		Order o = null;
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM orders WHERE code_o ="+code;
			ResultSet rs = stmt.executeQuery(sql);
			
		
				Integer code_o = rs.getInt("code_o");
				Float totalprice = rs.getFloat("total_price");
				Integer quantity  = rs.getInt("quantity");
				Integer administrator_id = rs.getInt("administrator_id");
				Integer pharmacist_id = rs.getInt("pharmacist_id");
				Integer medicine_id = rs.getInt("medicine_id");
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Administrator a = administratormanager.searchAdministratorById(administrator_id);
				Medicine m = medicinemanager.searchMedicineByCode(medicine_id);
				
				o = new Order (code_o,totalprice,quantity,p,a,m);
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return o;
		
	}
	
	
	public List <Order> getOrderOfPharmacist (Integer pharmacist_id)
	{
       List<Order> orders = new LinkedList <Order>();
		
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM orders WHERE pharmacist_id ="+pharmacist_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer code = rs.getInt("code_o");
				Float totalprice = rs.getFloat("total_price");
				Integer quantity  = rs.getInt("quantity");
				Integer administrator_id = rs.getInt("administrator_id");
				Integer medicine_id = rs.getInt("medicine_id");
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Administrator a = administratormanager.searchAdministratorById(administrator_id);
				Medicine m = medicinemanager.searchMedicineByCode(medicine_id);
				Order o = new Order (code,totalprice,quantity,p,a,m);
				orders.add(o);
				
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
		
	}
	
	
	public List <Order> getOrderOfAdministrator (Integer administrator_id)
	{
		
       List<Order> orders = new LinkedList <Order>();
		
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM orders WHERE administrator_id="+administrator_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer code = rs.getInt("code_o");
				Float totalprice = rs.getFloat("total_price");
				Integer quantity  = rs.getInt("quantity");
				Integer pharmacist_id = rs.getInt("pharmacist_id");
				Integer code_medicine = rs.getInt("medicine_id");
				
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Administrator a = administratormanager.searchAdministratorById(administrator_id);
				Medicine m = medicinemanager.searchMedicineByCode(code_medicine);
				Order o = new Order (code,totalprice,quantity,p,a,m);
				orders.add(o);
				
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	
	public void deleteOrderbyCode (Integer order_code) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM orders WHERE code=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,order_code);
			
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}