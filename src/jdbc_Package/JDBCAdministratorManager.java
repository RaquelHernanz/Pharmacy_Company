package jdbc_Package;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import interfaces_Package.AdministratorManager;
import pojos_Package.Administrator;


public class JDBCAdministratorManager implements AdministratorManager {
	
    private JDBCManager manager;
	
	public JDBCAdministratorManager (JDBCManager m) {
		this.manager = m;
	}
	
	public void createAdministrator(Administrator a) {
		try {
			String sql = "INSERT INTO Administrators (id,name,surname,phone_number,email)"
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
	
	public List<Administrator> getListOfAdministrators(){
		
		List<Administrator> administrators = new ArrayList<Administrator>();
		
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
				
				
				Administrator a = new Administrator (id, name,surname,phone_number,email);
				administrators.add(a);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return administrators;
	}
	
	
	
}
