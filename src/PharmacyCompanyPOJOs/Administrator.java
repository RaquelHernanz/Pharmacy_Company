package PharmacyCompanyPOJOs;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="administrator")
@XmlType(propOrder = {"email","phone_number","orders"})

public class Administrator implements Serializable
{
	
	private static final long serialVersionUID = 98763412L;
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String surname;
	@XmlElement
	private Integer phone_number;
	@XmlElement
	private String email;
	@XmlElement (name = "order")
	@XmlElementWrapper(name = "orders")
	private List <Order> orders;
	
	
	public Administrator(Integer id, String name, String surname, Integer phone_number, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone_number = phone_number;
		this.email = email;
		this.orders = new LinkedList <Order> ();
	}


	public Administrator() {
		super();
		this.orders = new LinkedList <Order> ();
	}
	
	


	public Administrator(String name, String surname, Integer phone_number, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone_number = phone_number;
		this.email = email;
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
		return surname;
	}


	public void setSurnmame(String surnmame) {
		this.surname = surnmame;
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
		return Objects.hash(email, id, name, orders, phone_number, surname);
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
				&& Objects.equals(surname, other.surname);
	}


	@Override
	public String toString() {
		return "Administrator [name=" + name + ", surnmame=" + surname + ", phone_number="
				+ phone_number + ", email=" + email +"]";
	}
	
	
	
	
}
