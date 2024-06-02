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
@XmlRootElement(name="doctor")
@XmlType(propOrder = {"email","phone_number","address","medicines"})
public class Doctor implements Serializable
{
	
	private static final long serialVersionUID = 98763412L;
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String surname;
	@XmlElement
	private String address;
	@XmlElement
	private Integer phone_number;
	@XmlElement
	private String email;
	@XmlElement(name="medicine")
	@XmlElementWrapper(name="medicines")
	private List <Medicine> medicines;
	
	public Doctor() {
		super();
		this.medicines =  new LinkedList <Medicine>();
	}

	
	
	@Override
	public String toString() {
		return "Doctor [name=" + name + ", surnmame=" + surname + ", address=" + address
				+ ", phone_number=" + phone_number + ", email=" + email + ", medicines=" + medicines + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, medicines, name, phone_number, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(medicines, other.medicines)
				&& Objects.equals(name, other.name) && Objects.equals(phone_number, other.phone_number)
				&& Objects.equals(surname, other.surname);
	}

	public Doctor(String name, String surname, String address, Integer phone_number, String email) 
	{
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
		this.medicines =  new LinkedList <Medicine>();
	}


	public Doctor(Integer id, String name, String surname, String address, Integer phone_number, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
		this.medicines =  new LinkedList <Medicine>();
	
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



	public String getAddress() {
		return address;
	}



	public void setAddress(String adress) {
		this.address = adress;
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



	public List<Medicine> getMedicines() {
		return medicines;
	}



	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
	
	
	
}
