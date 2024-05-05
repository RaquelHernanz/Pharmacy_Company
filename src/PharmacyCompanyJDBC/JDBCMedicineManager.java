package PharmacyCompanyJDBC;

import java.sql.PreparedStatement;
import java.util.List;

import PharmacyCompanyPOJOs.Medicine;



public class JDBCMedicineManager implements MedicineManager 
{
	private JDBCManager manager;
	public JDBCMedicineManager (JDBCManager m) 
	{
		this.manager = m;
	}
	
	@Override
	public void addMedicine(Medicine m) {
		// TODO Auto-generated method stub
		try {
		 String sql = "INSERT INTO medicines (name,price,stock,expirations,pharmacist_id,image)"
				 + "VALUES(?,?,?,?,?,?)";
		 
		 PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		 
		 prep.setInt(1, m.getCode());
		 prep.setFloat(2, m.getPrice());
		 prep.setInt(3, m.getStock());
		 prep.setDate(4, m.getExpirations());
		 prep.setString(5, m.);
		 prep.setInt(6, m.getOwner().getId());
			
		 prep.executeUpdate();
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/*sql = "CREATE TABLE medicines ("
					+ "code INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT UNIQUE,"
					+ "price NUMERIC (10,2),"
					+ "stock INTEGER,"
					+ "expirations DATE NOU NULL,"
					+ "pharmacist_id INTEGER REFERENCES Pharmacists(id),"
					+ "image BLOB);";*/
	
    public List<Medicine> getMedicinesofPharmacist(Integer pharmacist_id)
    {
    	
    }
    
    public List <Medicine> getMedicinesPurchasedC (Integer client_id)
    {
    	
    }
    
    public List <Medicine> getMedicinesPurchasedD (Integer doctor_id)
    {
    	
    }
}
