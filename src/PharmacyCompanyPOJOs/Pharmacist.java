package PharmacyCompanyPOJOs;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Pharmacist implements Serializable 
{
	
	private static final long serialVersionUID = 98763412L;
	private Integer id;
	private String name;
	private String surnmame;
	private Integer phone_number;
	private String email;
	private List <Order> orders;
	private List <Medicine> medicines_created;
	
	
	public Pharmacist() {
		super();
		this.orders = new LinkedList <Order>();
		this.medicines_created = new LinkedList <Medicine>();
	}
	
	
	
	public Pharmacist(String name, String surnmame, Integer phone_number, String email) {
		super();
		this.name = name;
		this.surnmame = surnmame;
		this.phone_number = phone_number;
		this.email = email;
		this.orders = new LinkedList <Order>();
		this.medicines_created = new LinkedList <Medicine>();
	}
	


	public Pharmacist(Integer id, String name, String surnmame, Integer phone_number, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surnmame = surnmame;
		this.phone_number = phone_number;
		this.email = email;
		this.orders = new LinkedList <Order>();
		this.medicines_created = new LinkedList <Medicine>();
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


	public List<Medicine> getMedicines_created() {
		return medicines_created;
	}


	public void setMedicines_created(List<Medicine> medicines_created) {
		this.medicines_created = medicines_created;
	}


	@Override
	public String toString() {
		return "Pharmacist [name=" + name + ", surnmame=" + surnmame + ", phone_number=" + phone_number + ", email="
				+ email + ", orders=" + orders + ", medicines_created=" + medicines_created + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, id, medicines_created, name, orders, phone_number, surnmame);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pharmacist other = (Pharmacist) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(medicines_created, other.medicines_created) && Objects.equals(name, other.name)
				&& Objects.equals(orders, other.orders) && Objects.equals(phone_number, other.phone_number)
				&& Objects.equals(surnmame, other.surnmame);
	}
	
	
}
