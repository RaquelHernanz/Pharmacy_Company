package PharmacyCompanyJDBC;

import java.sql.Blob;
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
		 String sql = "INSERT INTO medicines (name,price,stock,expirations,instructions,pharmacist_id,image)"
				 + "VALUES(?,?,?,?,?,?,?)";
		 
		 PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		 
		 prep.setInt(1, m.getCode());
		 prep.setFloat(2, m.getPrice());
		 prep.setInt(3, m.getStock());
		 prep.setDate(4, m.getExpirations());
		 prep.setString(5, m.getInstructions());
		 prep.setInt(6,m.getPharmacist().getId());
		 prep.setBlob (7,m.getImage());
			
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
				String name = rs.getString("name");
				Float price = rs.getFloat("price");
				String instructions = rs.getString("instructions");
				Integer stock  = rs.getInt("stock");
				Integer pharmacist_id = rs.getInt("pharmacist_id");
				Date expirations = rs.getDate("expirations");
				Blob image = rs.getBlob("image");
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);

				Medicine m = new Medicine (code,name,price,instructions,stock,expirations,p,image);
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
				String name = rs.getString("name");
				Float price = rs.getFloat("price");
				String instructions = rs.getString("instructions");
				Integer stock  = rs.getInt("stock");
				Date expirations = rs.getDate("expirations");
				Blob image = rs.getBlob("image");
				Pharmacist p = pharmacistmanager.searchPharmacistById(pharmacist_id);

				Medicine m = new Medicine (code,name,price,instructions,stock,expirations,p,image);
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
	
    
}
