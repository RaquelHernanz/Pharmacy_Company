package PharmacyCompanyJPA;

import java.security.MessageDigest;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import PharmacyCompanyInterfaces.UserManager;
import PharmacyCompanyPOJOs.Role;
import PharmacyCompanyPOJOs.User;

public class JPAUserManager implements UserManager{

	private EntityManager em;
	
	
	public JPAUserManager() {
		super();
		this.connect();
	}

	@Override
	public User checkPassword(String email, String pass) {
		// TODO Auto-generated method stub
		User u = null;
		
		Query q = em.createNativeQuery("SELECT * from users where email =? and password=?", User.class);
		q.setParameter(1, email);
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] pw = md.digest();
			
			q.setParameter(2, pw);
			
		} catch(Exception e)
		{e.printStackTrace();}
			
		
		try {
			u = (User) q.getSingleResult();
			
		} catch(NoResultException e) {}
		
		return u;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
		em = Persistence.createEntityManagerFactory("pharmacycompany-provider").createEntityManager();
		
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreing_keys = ON").executeUpdate();
		em.getTransaction().commit();
		
		if(this.getRoles().isEmpty()) 
		{
			
			Role client = new Role ("client");
			Role doctor = new Role("doctor");
			Role administrator = new Role ("administrator");
			Role pharmacist = new Role ("pharmacist");
			this.newRole(client);
			this.newRole(doctor);
			this.newRole(administrator);
			this.newRole(pharmacist);
		}
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		Query q=em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public void newRole(Role r) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		em.close();
	}

	@Override
	public Role getRole(Integer id) {
		// TODO Auto-generated method stub
		Query q=em.createNativeQuery("SELECT * FROM roles where id="+id, Role.class);
		Role r= (Role) q.getSingleResult();
		return r;
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		Query q=em.createNativeQuery("SELECT * FROM users where email="+email, User.class);
		User u= (User) q.getSingleResult();
		return u;
	}
	
	public void changePassword(User u, String new_passwd) 
	{
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(new_passwd.getBytes());
		byte[] new_pw = md.digest();
		u.setPassword(new_pw);
		//Implementar el Digest antes del setpassword
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		em.getTransaction().commit();
	}
	
	public void changeEmail(User u, String new_email) 
	{
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		try {
		u.setEmail(new_email);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		em.getTransaction().commit();
	}
	
	public void deleteUser (User u) 
	{
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}
	
	//in order to delete, we have to do remove;
	//in order to insert, we have to do persist; 
	//in order to read, we have to do a native query; 
	//in order to update, we have to do a set
}

