package jdbc_Package;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces_Package.ClientManager;
import pojos_Package.Client;

public class JDBCClientManager implements ClientManager{
	
	private JDBCManager manager;
	
	public JDBCClientManager(JDBCManager m) {
		this.manager=m;
	}

	@Override
	public void createClient(Client c) {
		// TODO Auto-generated method stub
		try {
			String sql="INSERT INTO Client(id,name,surname,phone_number,email)"
					+ "VALUES(?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,c.getId());
			prep.setString(2,c.getName());
			prep.setString(3, c.getSurnmame());
			prep.setInt(4,c.getPhone_number());
			prep.setString(5,c.getEmail());
			prep.setString (6,c.getAddress());
			/*Añadir el address*/
			
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
			String sql = "SELECT * FROM Client";
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

}
