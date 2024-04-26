package pojos_Package;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class Administrator implements Serializable
{
	
	private static final long serialVersionUID = 98763412L;
	/*Recordar tener cuidado con la implementación de INSERT*/
	private Integer id;
	private String name;
	private String surnmame;
	private Integer phone_number;
	private String email;
	private List <Order> orders;
	
	
	
	public Administrator(Integer id, String name, String surnmame, Integer phone_number, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surnmame = surnmame;
		this.phone_number = phone_number;
		this.email = email;
	}


	public Administrator() {
		super();
		this.orders = new LinkedList <Order> ();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurnmame() {
		return surnmame;
	}


	public void setSurnmame(String surnmame) {
		this.surnmame = surnmame;
	}


	public Integer getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, orders, phone_number, surnmame);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(orders, other.orders) && Objects.equals(phone_number, other.phone_number)
				&& Objects.equals(surnmame, other.surnmame);
	}


	@Override
	public String toString() {
		return "Administrator [name=" + name + ", surnmame=" + surnmame + ", phone_number="
				+ phone_number + ", email=" + email +"]";
	}
	
	
	
	
}
