package PharmacyCompanyJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import PharmacyCompanyInterfaces.OrderManager;
import PharmacyCompanyPOJOs.Administrator;
import PharmacyCompanyPOJOs.Order;
import PharmacyCompanyPOJOs.Pharmacist;



public class JDBCOrderManager implements OrderManager
{
	private JDBCManager manager;
	private JDBCPharmacistManager pharmacistmanager;
	private JDBCAdministratorManager administratormanager;
	
	public JDBCOrderManager (JDBCManager m) 
	{
		this.manager = m;
		this.pharmacistmanager = new JDBCPharmacistManager(manager);
		this.administratormanager = new JDBCAdministratorManager (manager);
	}
	
	public void addOrder (Order o) 
	{
		// TODO Auto-generated method stub
				try {
				 String sql = "INSERT INTO medicines (code,totalprice,quantity,pharmacist_id,administrator_id)"
						 + "VALUES(?,?,?,?,?)";
				 
				 PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				 
				 prep.setInt(1, o.getCode());
				 prep.setFloat(2, o.getTotalprice());
				 prep.setInt(3,o.getQuantity());
				 prep.setInt(4,o.getPharmacist().getId());
				 prep.setInt(5,o.getAdministrator().getId());
				 
				 prep.executeUpdate();
				 
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
	}
	
	public Order searchOrderByCode(Integer code) {
		Order o = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql= "SELECT * FROM orders WHERE code="+code;
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer code_o = rs.getInt("code");
			Float totalprice = rs.getFloat("totalprice");
			Integer quantity = rs.getInt("quantity");
			Integer pharmacist_id = rs.getInt("pharmacist_id");
			Integer administrator_id  = rs.getInt("administrator_id");
			Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
			Administrator a = administratormanager.searchAdministratorById(administrator_id);
			
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
				Integer code = rs.getInt("code");
				Float totalprice = rs.getFloat("total_price");
				Integer quantity  = rs.getInt("quantity");
				Integer pharmacist_id = rs.getInt("pharmacist_id");
				Integer administrator_id = rs.getInt("administrator_id");
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Administrator a = administratormanager.searchAdministratorById(administrator_id);
				
				Order o = new Order (code,totalprice,quantity,p,a);
				orders.add(o);
				
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
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
				Integer code = rs.getInt("code");
				Float totalprice = rs.getFloat("total_price");
				Integer quantity  = rs.getInt("quantity");
				Integer administrator_id = rs.getInt("administrator_id");
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Administrator a = administratormanager.searchAdministratorById(administrator_id);
				
				Order o = new Order (code,totalprice,quantity,p,a);
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
				Integer code = rs.getInt("code");
				Float totalprice = rs.getFloat("total_price");
				Integer quantity  = rs.getInt("quantity");
				Integer pharmacist_id = rs.getInt("pharmacist_id");
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Administrator a = administratormanager.searchAdministratorById(administrator_id);
				
				Order o = new Order (code,totalprice,quantity,p,a);
				orders.add(o);
				
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public void assignMedicinetoOrder (Integer medicine_id,Integer order_id,Float total_price, Integer quantity) throws Exception
	{
		//TODO Auto-generated method stub
		try 
		{
			String sql = "INSERT INTO update_medicines (order_id, medicine_id,total_price,quantity)"
					+ "VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt (1,order_id);
			prep.setInt(2,medicine_id);
			prep.setFloat(3,total_price);
			prep.setInt(4,quantity);
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
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