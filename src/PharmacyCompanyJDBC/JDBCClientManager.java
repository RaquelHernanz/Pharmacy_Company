package PharmacyCompanyJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PharmacyCompanyInterfaces.ClientManager;
import PharmacyCompanyPOJOs.Client;

public class JDBCClientManager implements ClientManager{
	
	private JDBCManager manager;
	
	public JDBCClientManager(JDBCManager m) {
		this.manager=m;
	}

	@Override
	public void createClient(Client c) {
		// TODO Auto-generated method stub
		try {
			String sql="INSERT INTO clients (name, surname, phone_number, email, address)"
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,c.getName());
			prep.setString(2, c.getSurnmame());
			prep.setInt(3,c.getPhone_number());
			prep.setString(4,c.getEmail());
			prep.setString (5,c.getAddress());
			
			prep.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<Client> getListOfClients() {
		// TODO Auto-generated method stub
		List<Client> clients = new ArrayList<Client>();
        try {
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM clients";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer phone_number = rs.getInt("phone_number");
				String email = rs.getString("email");
				String address = rs.getString("address");
				
				
				Client c = new Client (id, name,surname,phone_number,email,address);
				clients.add(c);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clients;
	}
	
	public Client searchClientById (Integer id) 
	{
		// TODO Auto-generated method stub
				Client c = null;
				
				
				try 
				{
					Statement stmt = manager.getConnection().createStatement();
					String sql = "SELECT * FROM clients WHERE id=" + id;
				
					ResultSet rs = stmt.executeQuery(sql);
					
					Integer c_id = rs.getInt("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					Integer phone_number = rs.getInt("phone_number");
					String email = rs.getString("email");
					String address = rs.getString("address");
					
					
					c = new Client (c_id, name,surname,phone_number,email,address);
				    
				    rs.close();
				    stmt.close();
				    
				}catch(Exception e) {e.printStackTrace();}
				
				
		return c;
	}
	
	public void deleteClientbyId (Integer id) throws Exception 
	{
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM clients WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,id);
			
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Client searchClientByNameEmail (String name_c, String surname_c, String email_c) throws Exception
	{
		// TODO Auto-generated method stub
				Client c = null;
				
				try 
				{
					Statement stmt = manager.getConnection().createStatement();
					String sql = "SELECT * FROM clients WHERE name= '"+name_c+"' AND surname = '"+surname_c+"' AND email = '"+email_c+"'";
				
					ResultSet rs = stmt.executeQuery(sql);
					Integer c_id = rs.getInt("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					Integer phone_number = rs.getInt("phone_number");
					String email = rs.getString("email");
					String address = rs.getString("address");
					
					
					c = new Client (c_id, name,surname,phone_number,email,address);
				    
				    rs.close();
				    stmt.close();
				    
				}catch(Exception e) {e.printStackTrace();}
					
		return c;
 }
	
	public Client searchClientByEmail (String email_c) throws Exception
	{
		// TODO Auto-generated method stub
				Client c = null;
				
				try 
				{
					Statement stmt = manager.getConnection().createStatement();
					String sql = "SELECT * FROM clients WHERE email = '"+email_c+"'";
				
					ResultSet rs = stmt.executeQuery(sql);
					Integer c_id = rs.getInt("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					Integer phone_number = rs.getInt("phone_number");
					String email = rs.getString("email");
					String address = rs.getString("address");
					
					
					c = new Client (c_id, name,surname,phone_number,email,address);
				    
				    rs.close();
				    stmt.close();
				    
				}catch(Exception e) {e.printStackTrace();}
					
		return c;
 }
	
	public void updateName (Integer id, String name) throws Exception
    {
		try {
			String sql = "UPDATE clients SET name = ? WHERE id = ?";
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
			String sql = "UPDATE clients SET surname = ? WHERE id = ?";
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
			String sql = "UPDATE clients SET phone_number = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,phone_number);
			prep.setInt(2,id);
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}		
    }
    
    public void updateAddress (Integer id,String address) throws Exception 
    {
    	try {
			String sql = "UPDATE clients SET address = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,address);
			prep.setInt(2,id);
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}		
    }
	
    public void updateEmail(Integer id, String email) throws Exception {
    	try {
    		String sql = "UPDATE clients SET email=? WHERE id=?";
    		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
    		
    		prep.setString(1, email);
    		prep.setInt(2, id);
    		
    		prep.executeUpdate();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    
}

