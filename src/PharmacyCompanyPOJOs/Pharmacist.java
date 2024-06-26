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
@XmlRootElement(name = "pharmacist")
@XmlType(propOrder = {"email","phone_number","medicines_created","orders"})
public class Pharmacist implements Serializable {

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
	@XmlElementWrapper (name = "orders")
	private List<Order> orders;
	@XmlElement(name = "medicine")
	@XmlElementWrapper(name = "medicines")
	private List<Medicine> medicines_created;

	public Pharmacist() {
		super();
		this.orders = new LinkedList<Order>();
		this.medicines_created = new LinkedList<Medicine>();
	}

	public Pharmacist(String name, String surnmame, Integer phone_number, String email) {
		super();
		this.name = name;
		this.surname = surnmame;
		this.phone_number = phone_number;
		this.email = email;
		this.orders = new LinkedList<Order>();
		this.medicines_created = new LinkedList<Medicine>();
	}

	public Pharmacist(Integer id, String name, String surnmame, Integer phone_number, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surnmame;
		this.phone_number = phone_number;
		this.email = email;
		this.orders = new LinkedList<Order>();
		this.medicines_created = new LinkedList<Medicine>();
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
		return "Pharmacist [name=" + name + ", surnmame=" + surname + ", phone_number=" + phone_number + ", email="
				+ email + ", orders=" + orders + ", medicines_created=" + medicines_created + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, medicines_created, name, orders, phone_number, surname);
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
				&& Objects.equals(surname, other.surname);
	}

}
