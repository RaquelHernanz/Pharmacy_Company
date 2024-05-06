
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
			String sql = "INSERT INTO pharmacists (id,name,surname,phone_number,email)"
					+ "VALUES(?,?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,a.getId());
			prep.setString(2,a.getName());
			prep.setString(3, a.getSurnmame());
			prep.setInt(4,a.getPhone_number());
			prep.setString(5,a.getEmail());
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
}
