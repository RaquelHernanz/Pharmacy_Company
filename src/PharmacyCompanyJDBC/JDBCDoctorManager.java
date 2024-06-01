package PharmacyCompanyJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PharmacyCompanyInterfaces.DoctorManager;
import PharmacyCompanyPOJOs.Doctor;

public class JDBCDoctorManager implements DoctorManager{
		
		private JDBCManager manager;
		
		public JDBCDoctorManager(JDBCManager m) {
			this.manager=m;
		
		}
		
		@Override
		public void createDoctor (Doctor c) {
			// TODO Auto-generated method stub
			try {
				String sql="INSERT INTO doctors (name,surname,phone_number,email,address)"
						+ "VALUES(?,?,?,?,?)";
				PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				prep.setString(1,c.getName());
				prep.setString(2, c.getSurname());
				prep.setInt(3,c.getPhone_number());
				prep.setString(4,c.getEmail());
				prep.setString(5,c.getAddress());
				prep.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}


		@Override
	public List<Doctor> getListOfDoctors () {
			// TODO Auto-generated method stub
			List<Doctor> doctors = new ArrayList<Doctor>();
	        try {
				
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM doctors";
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					Integer id = rs.getInt("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					Integer phone_number = rs.getInt("phone_number");
					String email = rs.getString("email");
					String address = rs.getString("address");
					Doctor c = new Doctor (id, name,surname,address,phone_number,email);
					doctors.add(c);
				}
				rs.close();
				stmt.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return doctors;
		}
		
		
	public Doctor searchDoctorById (Integer id) 
	{
			// TODO Auto-generated method stub
			Doctor d = null;	
			try 
			{
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM doctors WHERE id=" + id;
			
				ResultSet rs = stmt.executeQuery(sql);
				
				Integer d_id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer phone_number = rs.getInt("phone_number");
				String email = rs.getString("email");
				String address = rs.getString("address");
				d = new Doctor (d_id, name,surname,address,phone_number,email);
			    
			    rs.close();
			    stmt.close();
			    
			}catch(Exception e) {e.printStackTrace();}
			
		return d;
	}
	
	public void deleteDoctorbyId (Integer id) throws Exception 
	{
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM doctors WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,id);
			
			prep.executeUpdate();			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Doctor searchDoctorByEmail (String email_d) throws Exception
	{
			// TODO Auto-generated method stub
			Doctor d = null;	
			try 
			{
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM doctors WHERE email= '"+ email_d+"'";
			
				ResultSet rs = stmt.executeQuery(sql);
				
				Integer d_id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer phone_number = rs.getInt("phone_number");
				String email = rs.getString("email");
				String address = rs.getString("address");
				d = new Doctor (d_id, name,surname,address,phone_number,email);
			    
			    rs.close();
			    stmt.close();
			    
			}catch(Exception e) {e.printStackTrace();}
			
		return d;
	}

	public Doctor searchDoctorByNameEmail (String name_d, String surname_d,String email_d) throws Exception
	{
			// TODO Auto-generated method stub
			Doctor d = null;	
			try 
			{
				Statement stmt = manager.getConnection().createStatement();
				String sql = "SELECT * FROM doctors WHERE name = '"+name_d+"' AND surname = '"+surname_d+"' AND email= '"+ email_d+"'";
			
				ResultSet rs = stmt.executeQuery(sql);
				
				Integer d_id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer phone_number = rs.getInt("phone_number");
				String email = rs.getString("email");
				String address = rs.getString("address");
				d = new Doctor (d_id, name,surname,address,phone_number,email);
			    
			    rs.close();
			    stmt.close();
			    
			}catch(Exception e) {e.printStackTrace();}
			
		return d;
	}
	
	public void updateName (Integer id, String name) throws Exception
    {
		try {
			String sql = "UPDATE doctors SET name = ? WHERE id = ?";
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
			String sql = "UPDATE doctors SET surname = ? WHERE id = ?";
			System.out.println(sql);
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
			String sql = "UPDATE doctors SET phone_number = ? WHERE id = ?";
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
			String sql = "UPDATE doctors SET address = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,address);
			prep.setInt(2,id);
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}		
    }
	
    public void updateEmail(Integer id, String email) throws Exception
    {
    	try {
    		String sql = "UPDATE doctors SET email = ? WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,email);
			prep.setInt(2,id);
			prep.executeUpdate();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    }
}
