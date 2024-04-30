
package jdbc_Package;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pojos_Package.Pharmacist;

public class JDBCPharmacistManager 
{
private JDBCManager manager;
	
	public JDBCPharmacistManager (JDBCManager m) {
		this.manager = m;
	}
	
	public void createPharmacistManager (Pharmacist a) {
		try {
			String sql = "INSERT INTO Pharmacists (id,name,surname,phone_number,email)"
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
	
	public List<Pharmacist> getListOfAdministrators(){
		
		List<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
		
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Administrator";
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

}
