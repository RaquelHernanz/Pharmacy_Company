package PharmacyCompanyJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PharmacyCompanyInterfaces.AdministratorManager;
import PharmacyCompanyPOJOs.Administrator;


public class JDBCAdministratorManager implements AdministratorManager {
	
    private JDBCManager manager;
	
	public JDBCAdministratorManager (JDBCManager m) {
		this.manager = m;
	}
	
	//Funciona
	public void createAdministrator(Administrator a) {
		try {
			String sql = "INSERT INTO administrators (name,surname,phone_number,email)"
					+ "VALUES(?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,a.getName());
			prep.setString(2, a.getSurnmame());
			prep.setInt(3,a.getPhone_number());
			prep.setString(4,a.getEmail());
			
			prep.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Administrator> getListOfAdministrators(){
		
		List<Administrator> administrators = new ArrayList<Administrator>();
		
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM administrators";
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
	
	//Funciona
	public Administrator searchAdministratorById (Integer id) 
	{
		// TODO Auto-generated method stub
		Administrator a = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM administrators WHERE id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			Integer a_id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Integer phone_number = rs.getInt("phone_number");
			String email = rs.getString("email");
			
			a = new Administrator (a_id, name,surname,phone_number,email);
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		return a;
	}
	
	//Funciona
	public Administrator searchAdministratorByNameEmail (String name_a, String surname_a,String email_a) throws Exception
	{
		// TODO Auto-generated method stub
		Administrator a = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM administrators WHERE name = '"+name_a+"' AND surname = '"+surname_a+"' AND email = '"+email_a+"'";
			ResultSet rs = stmt.executeQuery(sql);
			Integer a_id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Integer phone_number = rs.getInt("phone_number");
			String email = rs.getString("email");
			a = new Administrator (a_id, name,surname,phone_number,email);
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
	
		return a;
	}
	
	
	//Funciona
	public Administrator searchAdministratorByEmail (String email) throws Exception
	{
		// TODO Auto-generated method stub
		Administrator a = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM administrators WHERE email ='"+email+"'";
			ResultSet rs = stmt.executeQuery(sql);
			Integer a_id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Integer phone_number = rs.getInt("phone_number");
			String email_a = rs.getString("email");
			a = new Administrator (a_id, name,surname,phone_number,email_a);
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		return a;
	}
	
	//Funciona
    public void deleteAdministratorbyId (Integer id) throws Exception 
	{
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM administrators WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,id);
			
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    
    
	
}
