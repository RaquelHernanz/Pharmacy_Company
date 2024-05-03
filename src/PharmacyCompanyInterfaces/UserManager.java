package PharmacyCompanyInterfaces;

import java.util.List;

import PharmacyCompanyPOJOs.Role;
import PharmacyCompanyPOJOs.User;

public interface UserManager {

	public void connect();
	public User checkPassword(String email, String pass);
	public List<Role> getRoles();
	public void newRole(Role r);
	public void newUser(User u);
	public void disconnect();
	public Role getRole(Integer id);
	public User getUser(String email);
}
