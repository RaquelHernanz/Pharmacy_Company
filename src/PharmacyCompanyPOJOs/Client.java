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
@XmlRootElement(name="Client")
@XmlType(propOrder = {"name","surname","email","phone_number","Medicines"})
public class Client implements Serializable
{
	private static final long serialVersionUID = 98763412L;
	
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String surname;
	@XmlTransient
	private String address;
	@XmlElement
	private Integer phone_number;
	@XmlAttribute
	private String email;
	@XmlElement(name="Medicine")
	@XmlElementWrapper(name="Medicines")
	private List <Medicine> medicines;
	
	
	public Client() 
	{
		super();
		this.medicines = new LinkedList <Medicine> ();
	}
	
	
	public Client(Integer id, String name, String surnmame, Integer phone_number, String email, String address) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surnmame;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
		this.medicines = new LinkedList <Medicine> ();
	
	}


	public Client(String name, String surnmame, String address, Integer phone_number, String email) {
		super();
		this.name = name;
		this.surname = surnmame;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
		this.medicines = new LinkedList <Medicine> ();
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
		Client other = (Client) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(medicines, other.medicines)
				&& Objects.equals(name, other.name) && Objects.equals(phone_number, other.phone_number)
				&& Objects.equals(surname, other.surname);
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
	public void setSurname(String surnmame) {
		this.surname = surnmame;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "Client [Name =" + name + ", Surname =" + surname + ", Address =" + address
				+ ", Phone Number =" + phone_number + ", Email =" + email + "]";
	}
	


}
