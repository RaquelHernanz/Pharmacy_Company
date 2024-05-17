package PharmacyCompanyJDBC;

import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import PharmacyCompanyInterfaces.MedicineManager;
import PharmacyCompanyPOJOs.Medicine;
import PharmacyCompanyPOJOs.Pharmacist;



public class JDBCMedicineManager implements MedicineManager 
{
	private JDBCManager manager;
	private JDBCPharmacistManager pharmacistmanager;
	public JDBCMedicineManager (JDBCManager m) 
	{
		this.manager = m;
		this.pharmacistmanager = new JDBCPharmacistManager(manager);
	}
	
	@Override
	public void addMedicine(Medicine m) {
		// TODO Auto-generated method stub
		try {
		 String sql = "INSERT INTO medicines (name_med,price,stock,expirations,intructions,pharmacist_id,image,prescribed)"
				 + "VALUES(?,?,?,?,?,?,?,?)";
		 
		 PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		 
		 prep.setString(1, m.getName());
		 prep.setFloat(2, m.getPrice());
		 prep.setInt(3, m.getStock());
		 prep.setDate(4, m.getExpirations());
		 prep.setString(5, m.getInstructions());
		 prep.setInt(6,m.getPharmacist().getId());
		 prep.setBytes(7,m.getImage());
		 prep.setBoolean(8,m.getPrescribed());
			
		 prep.executeUpdate();
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	public List <Medicine> getListofMedicines ()
	{
       List<Medicine> medicines = new LinkedList <Medicine>();
		
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM medicines";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer code = rs.getInt("code");
				String name = rs.getString("name_med");
				Float price = rs.getFloat("price");
				String instructions = rs.getString("intructions");
				Integer stock  = rs.getInt("stock");
				Integer pharmacist_id = rs.getInt("pharmacist_id");
				Date expirations = rs.getDate("expirations");
				Boolean prescribed = rs.getBoolean("prescribed");
				
				InputStream blobStream = rs.getBinaryStream("image");
				byte [] blobArray= new byte [blobStream.available()];
				blobStream.read(blobArray);
				
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Medicine m = new Medicine (code,name,instructions,price,stock,expirations,p,blobArray,prescribed);
				medicines.add(m);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return medicines;
	}
	
	public List<Medicine> getMedicinesofPharmacist(Integer pharmacist_id)
	{
		List <Medicine> medicines = new ArrayList <Medicine>();
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM medicines WHERE pharmacist_id="+pharmacist_id;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer code = rs.getInt("code");
				String name = rs.getString("name_med");
				Float price = rs.getFloat("price");
				String instructions = rs.getString("intructions");
				Integer stock  = rs.getInt("stock");
				Date expirations = rs.getDate("expirations");
				InputStream blobStream = rs.getBinaryStream("image");
				byte [] blobArray= new byte [blobStream.available()];
				blobStream.read(blobArray);
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
				Boolean prescribed = rs.getBoolean("prescribed");
				Medicine m = new Medicine (code,name,instructions,price,stock,expirations,p,blobArray,prescribed);
				medicines.add(m);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return medicines;
	}
	
	public List <Medicine> getListofMedicinesPurchasedClient (Integer client_id) throws Exception
	{
	     List <Medicine> medicines = new ArrayList <Medicine>();
	     
	     try {
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT medicine_id FROM purchase_C WHERE client_id="+client_id;
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					Integer id_obtained = rs.getInt("medicine_id");
					Medicine m = searchMedicineByCode(id_obtained);
					medicines.add(m);
				}
				
				rs.close();
				stmt.close();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return medicines;
	     
	}
	
	public Medicine searchMedicineByName (String name)throws Exception 
	{
		// TODO Auto-generated method stub
				Medicine m = null;
				
				try {
					Statement stmt = manager.getConnection().createStatement();
					String sql = "SELECT * FROM medicines WHERE name_med='"+name+"'";
					ResultSet rs = stmt.executeQuery(sql);
					Integer code = rs.getInt("code");
					String name_m = rs.getString("name_med");
					Float price = rs.getFloat("price");
					String instructions = rs.getString("intructions");
					Integer stock  = rs.getInt("stock");
					Date expirations = rs.getDate("expirations");
					
					InputStream blobStream = rs.getBinaryStream("image");
					byte [] blobArray= new byte [blobStream.available()];
					blobStream.read(blobArray);
					
					Integer pharmacist_id = rs.getInt("pharmacist_id");
					Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
					
					Boolean prescribed = rs.getBoolean("prescribed");
					m = new Medicine (code,name_m,instructions,price,stock,expirations,p,blobArray,prescribed);
				    rs.close();
				    stmt.close();
				    
				}catch(Exception e) {e.printStackTrace();}
				
			return m;
	}
	
	
	public Medicine searchMedicineByCode (Integer code)throws Exception 
	{
		// TODO Auto-generated method stub
				Medicine m = null;
				
				try {
					Statement stmt = manager.getConnection().createStatement();
					String sql = "SELECT * FROM medicines WHERE code="+code+"";
					ResultSet rs = stmt.executeQuery(sql);
					Integer code_m = rs.getInt("code");
					String name_m = rs.getString("name_med");
					Float price = rs.getFloat("price");
					String instructions = rs.getString("intructions");
					Integer stock  = rs.getInt("stock");
					Date expirations = rs.getDate("expirations");
					
					InputStream blobStream = rs.getBinaryStream("image");
					byte [] blobArray= new byte [blobStream.available()];
					blobStream.read(blobArray);
					
					Integer pharmacist_id = rs.getInt("pharmacist_id");
					Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);
					
					Boolean prescribed = rs.getBoolean("prescribed");
					m = new Medicine (code_m,name_m,instructions,price,stock,expirations,p,blobArray,prescribed);
				    rs.close();
				    stmt.close();
				    
				}catch(Exception e) {e.printStackTrace();}
				
			return m;
	}
	
	
	
	public void assignMedicinetoClient (Integer client_id,Integer code, Float bill,Integer quantity) throws Exception
	{
		//TODO Auto-generated method stub
		try 
		{
			String sql = "INSERT INTO purchase_C (client_id, medicine_id,bill,quantity)"
					+ "VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt (1,client_id);
			prep.setInt(2,code);
			prep.setFloat(3,bill);
			prep.setInt(4,quantity);
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void assignMedicinetoDoctor (Integer doctor_id, Integer code, Float bill,Integer quantity) throws Exception
	{
		//TODO Auto-generated method stub
				try 
				{
					String sql = "INSERT INTO purchase_C (client_id, medicine_id,bill,quantity)"
							+ "VALUES (?,?,?,?)";
					PreparedStatement prep = manager.getConnection().prepareStatement(sql);
					prep.setInt (1,doctor_id);
					prep.setInt(2,code);
					prep.setFloat(3,bill);
					prep.setInt(4,quantity);
					prep.executeUpdate();			
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
	}
	
	public void deleteMedicinebyCode (Integer medicine_code) throws Exception
	{
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM medicines WHERE code=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,medicine_code);
			
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//Método necesario para realizar la actualización del stock al hacer órdenes
	public void updateStock (Integer quantity, Integer medicine_code) throws Exception
	{
		try {
			String sql = "UPDATE medicines SET stock = ? WHERE code = ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, quantity);
			prep.setInt(2,medicine_code);
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}		
	}
	
	public void updateName (String name, Integer medicine_code) throws Exception 
	{
		try 
		{
			String sql = "UPDATE medicines SET name_med = ? WHERE code = ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,name);
			prep.setInt(2,medicine_code);
			prep.executeUpdate();
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
    
}
