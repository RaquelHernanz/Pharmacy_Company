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
				String sql="INSERT INTO doctors (id,name,surname,phone_number,email,address)"
						+ "VALUES(?,?,?,?,?,?)";
				PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				prep.setInt(1,c.getId());
				prep.setString(2,c.getName());
				prep.setString(3, c.getSurnmame());
				prep.setInt(4,c.getPhone_number());
				prep.setString(5,c.getEmail());
				prep.setString(6,c.getAddress());
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
					List <String> prescriptions = rs.getString("prescriptions");
					//Preguntar a Katerina.
					Doctor c = new Doctor (id, name,surname,address,phone_number,email,prescriptions);
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
				String prescriptions = rs.getString("prescriptions");
				//Preguntar a Katerina.
				d = new Doctor (d_id, name,surname,address,phone_number,email,prescriptions);
			    
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
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
		
}
