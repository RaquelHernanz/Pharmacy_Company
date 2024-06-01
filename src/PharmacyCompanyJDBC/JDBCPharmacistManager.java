
package PharmacyCompanyJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PharmacyCompanyInterfaces.PharmacistManager;
import PharmacyCompanyPOJOs.Pharmacist;

public class JDBCPharmacistManager implements PharmacistManager
{
    private JDBCManager manager;
	
	public JDBCPharmacistManager (JDBCManager m) {
		this.manager = m;
	}
	
	public void createPharmacist (Pharmacist a) {
		try {
			String sql = "INSERT INTO pharmacists (name,surname,phone_number,email)"
					+ "VALUES(?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,a.getName());
			prep.setString(2, a.getSurname());
			prep.setInt(3,a.getPhone_number());
			prep.setString(4,a.getEmail());
			prep.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List <Pharmacist> getListOfPharmacist(){
		
		List<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
		
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM pharmacists";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer phone_number = rs.getInt("phone_number");
				String email = rs.getString("email");
				
				
				Pharmacist a = new Pharmacist (id, name,surname,phone_number,email);
				pharmacists.add(a);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pharmacists;
	}
	
	
	public Pharmacist searchPharmacistById (Integer id) 
	{
	// TODO Auto-generated method stub
			Pharmacist p = null;
			try 
			{
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM pharmacists WHERE id=" + id;
			
				ResultSet rs = stmt.executeQuery(sql);
				
				Integer p_id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer phone_number = rs.getInt("phone_number");
				String email = rs.getString("email");
				
				p = new Pharmacist (p_id, name,surname,phone_number,email);
			    
			    rs.close();
			    stmt.close();
			    
			}catch(Exception e) {e.printStackTrace();}
				
		return p;
			
	}
	
	public void deletePharmacistbyId (Integer id) throws Exception 
	{
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM pharmacists WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,id);
			
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Pharmacist searchPharmacistByEmail (String email_p) throws Exception
	{
	// TODO Auto-generated method stub
			Pharmacist p = null;
			try 
			{
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM pharmacists WHERE email= '"+email_p+"'";
				ResultSet rs = stmt.executeQuery(sql);
				
				Integer p_id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer phone_number = rs.getInt("phone_number");
				String email = rs.getString("email");
				
				p = new Pharmacist (p_id, name,surname,phone_number,email);
			    
			    rs.close();
			    stmt.close();
			    
			}catch(Exception e) {e.printStackTrace();}
				
		return p;
			
	}
	
	public Pharmacist searchPharmacistByNameEmail (String name_p, String surname_p, String email_p) throws Exception
	{
	// TODO Auto-generated method stub
			Pharmacist p = null;
			try 
			{
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM pharmacists WHERE email= '" +email_p+"' AND name = '"+name_p+"' AND surname = '"+surname_p+"'";
			
				ResultSet rs = stmt.executeQuery(sql);
				
				Integer p_id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer phone_number = rs.getInt("phone_number");
				String email = rs.getString("email");
				
				p = new Pharmacist (p_id, name,surname,phone_number,email);
			    
			    rs.close();
			    stmt.close();
			    
			}catch(Exception e) {e.printStackTrace();}
				
		return p;
			
	}
	
	public void updateName (Integer id, String name) throws Exception
    {
		try {
			String sql = "UPDATE pharmacists SET name = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,name);
			prep.setInt(2,id);
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}		
    }
    
    public void updateSurname (Integer id, String surname) throws Exception 
    {
    	try {
			String sql = "UPDATE pharmacists SET surname = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,surname);
			prep.setInt(2,id);
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}		
    }
    
    public void updatePhoneNumber (Integer id, Integer phone_number) 
    {
    	try {
			String sql = "UPDATE pharmacists SET name = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,phone_number);
			prep.setInt(2,id);
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}		
    }
    
}
