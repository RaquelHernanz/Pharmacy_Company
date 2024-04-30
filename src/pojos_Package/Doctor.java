package pojos_Package;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Doctor implements Serializable
{
	
	private static final long serialVersionUID = 98763412L;
	private List <String> prescriptions;
	private Integer id;
	private String name;
	private String surnmame;
	private String address;
	private Integer phone_number;
	private String email;
	private List <Medicine> medicines;
	
	public Doctor() {
		super();
		this.prescriptions = new LinkedList <String>();
		this.medicines =  new LinkedList <Medicine>();
		//Provisionalmente se quedará como LinkedList
	}
	
	
	public Doctor(String name, String surnmame, String address, Integer phone_number, String email) {
		super();
		this.name = name;
		this.surnmame = surnmame;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
		this.prescriptions = new LinkedList <String>();
		this.medicines =  new LinkedList <Medicine>();
	}
	
	

	public Doctor(Integer id, String name, String surnmame, String address, Integer phone_number, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surnmame = surnmame;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
		this.prescriptions = new LinkedList <String>();
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



	public String getSurnmame() {
		return surnmame;
	}



	public void setSurnmame(String surnmame) {
		this.surnmame = surnmame;
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



	public List<String> getPrescriptions() {
		return prescriptions;
	}


	public void setPrescriptions(List<String> prescriptions) {
		this.prescriptions = prescriptions;
	}



	@Override
	public String toString() {
		return "Doctor [name=" + name + ", surnmame=" + surnmame + ", adress=" + address + ", phone_number="
				+ phone_number + ", email=" + email + ", medicines=" + medicines + ", prescriptions=" + prescriptions
				+ "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, medicines, name, phone_number, prescriptions, surnmame);
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
				&& Objects.equals(prescriptions, other.prescriptions) && Objects.equals(surnmame, other.surnmame);
	}


	

	

	
	
	
	
	
}
